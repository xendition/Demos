package com.michael.demo.jdk.thread.lock.distributed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 基于 Zookeeper 实现的分布式锁
 *
 * <p>利用临时顺序节点来实现分布式锁
 * <p>获取锁：取号排队(创建自己的临时顺序节点)，然后判断自己是否最小号，如果是，获取锁。 如果不是则注册前一节点的watcher，阻塞自己。
 * <p>释放锁：删除自己创建的临时顺序节点
 *
 * @author Michael
 */
public class ZookeeperDistributedLock implements Lock {

    /** 目录分隔符 */
    private final String DIRECTORY_SEPARATOR = "/";

    /** 锁路径 */
    private String lockPath;

    /** ZK客户端 */
    private ZkClient zkClient;

    /** 当前顺序节点路径 */
    private String currentPath;

    /** 上一个顺序节点路径 */
    private String beforePath;

    public ZookeeperDistributedLock(String lockPath) {
        this.lockPath = lockPath;

        this.zkClient = new ZkClient("127.0.0.1:2181");
        // 默认使用 SerializableSerializer 这个序列化器 也可以自定义
        // this.zkClient.setZkSerializer(new SerializableSerializer());

        if (!this.zkClient.exists(lockPath)) {
            try {
                this.zkClient.createPersistent(lockPath);
            } catch (Exception e) {}
        }
    }

    @Override
    public boolean tryLock() {

        // 只创建一次，之后排队
        if (this.currentPath == null) {
            this.currentPath = this.zkClient.createEphemeralSequential(this.lockPath + this.DIRECTORY_SEPARATOR, "aaa");
        }
        // 获取所有的子节点
        List<String> children = this.zkClient.getChildren(this.lockPath);

        // 判断当前节点是否是最小的节点
        if (this.currentPath.equals(this.lockPath + this.DIRECTORY_SEPARATOR + children.get(0))) {
            return true;
        } else {
            // 取前一个节点并得到节点的索引号
            int beforeIndex = children.indexOf(this.currentPath.substring(this.lockPath.length() + 1)) - 1;
            this.beforePath = this.lockPath + this.DIRECTORY_SEPARATOR + children.get(beforeIndex);
        }
        return false;
    }

    private void printChildren(List<String> children) {

        for (String child : children) {
            System.out.print(child + "\t");
        }
        System.out.println();
    }

    @Override
    public void lock() {
        if (!this.tryLock()) {
            // 阻塞等待
            this.waitForLock();
            // 不被阻塞时再次尝试获取锁
            this.lock();
        }
    }

    private void waitForLock() {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        IZkDataListener dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(Thread.currentThread().getName() + " -> 监听到节点被删除");
                countDownLatch.countDown();
            }
        };

        // 监控前一个节点事件
        this.zkClient.subscribeDataChanges(this.beforePath, dataListener);

        // 让当前线程阻塞
        if (this.zkClient.exists(this.beforePath)) {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 当前线程醒来之后取消 watcher
        this.zkClient.unsubscribeDataChanges(this.beforePath, dataListener);
    }

    @Override
    public void unlock() {
        // 删除节点
        this.zkClient.delete(this.currentPath);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }


}
