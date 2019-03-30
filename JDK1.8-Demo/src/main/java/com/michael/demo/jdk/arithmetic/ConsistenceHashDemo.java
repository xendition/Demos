package com.michael.demo.jdk.arithmetic;

import java.util.*;

/**
 * 一致性hash算法简单实现 —— 核心原理
 *
 * <pre>
 *  一致性hash算法实现需要的组成部分
 *  物理节点
 *  虚拟节点
 *  hash算法
 *      —— 不能使用JDK自己的hashCode,因为不够散列
 *      —— 其它的hash算法有 CRC32_HASH、FNV1_32_HASH、KETAMA_HASH(memcache推荐的hash算法)
 *  将虚拟节点放到hash环上
 *  数据找到对应的虚拟节点
 * </pre>
 *
 * @author Michael
 */
public class ConsistenceHashDemo {

    /** 真实的物理节点 */
    private List<String> realNodes = new ArrayList<>();

    /** 物理节点与虚拟节点的关系，存储虚拟节点的hash值 */
    private Map<String, List<Integer>> real2VirtualMap = new HashMap<>();

    /** 排序存储结构 使用红黑树 key 是虚拟节点的hash值 value是物理节点 */
    private SortedMap<Integer, String> sortedMap = new TreeMap<>();

    /** 每个真实节点对应虚拟节点的数量 */
    private int virtualNums = 100;

    public ConsistenceHashDemo(int virtualNums) {
        this.virtualNums = virtualNums;
    }

    public ConsistenceHashDemo() {}

    /**
     * 增加一台真实物理节点
     *
     * @param realNode 物理节点信息
     */
    public void addServer(String realNode) {
        this.realNodes.add(realNode);

        String vnode = null;

        int autoIncrement = 0;
        int existingVirtualNodeCount = 0;
        List<Integer> vnodes = new ArrayList<>();
        this.real2VirtualMap.put(realNode, vnodes);

        // 创建虚拟节点，并放置到环上 —— 为了防止hash碰撞，使用while，确保虚拟节点数量足够
        while (existingVirtualNodeCount < this.virtualNums) {
            autoIncrement++;
            // 生成hash值对应到物理节点
            vnode = realNode + "$$" + autoIncrement;
            int hashValue = this.getHash(vnode);
            if (!this.sortedMap.containsKey(hashValue)) {
                vnodes.add(hashValue);
                this.sortedMap.put(hashValue, realNode);
                existingVirtualNodeCount++;
            }
        }
    }

    /**
     * 计算hash值 - FNV1_32_HASH 算法实现
     */
    private int getHash(String key) {

        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }

        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0) {
            hash = Math.abs(hash);
        }

        return hash;
    }

    /**
     * 获取数据的存放节点
     *
     * @param key 存放的数据key值
     *
     * @return realNode
     */
    public String getServer(String key) {
        // 计算hash值
        int hash = this.getHash(key);
        // 得到大于该hash值的所有虚拟节点map
        SortedMap<Integer, String> subMap = this.sortedMap.tailMap(hash);
        // 取第一个key
        Integer vhash = subMap.firstKey();
        // 返回对应的物理节点服务器信息
        return subMap.get(vhash);
    }

    /**
     * 移除一台真实物理节点
     *
     * @param realNode 物理节点信息
     */
    public void removeServer(String realNode) {

        // 移除对应虚拟节点的数据
        List<Integer> vnodes = this.real2VirtualMap.get(realNode);
        if (vnodes != null) {
            for (Integer hash : vnodes) {
                this.sortedMap.remove(hash);
            }
        }
        // 移除真实节点与虚拟节点的对应关系
        this.real2VirtualMap.remove(realNode);
        // 移除真实节点信息
        this.realNodes.remove(realNode);
    }

    public static void main(String[] args) {

        ConsistenceHashDemo demo = new ConsistenceHashDemo(1000);

        demo.addServer("192.168.127.1");
        demo.addServer("192.168.127.2");
        demo.addServer("192.168.127.3");
        demo.addServer("192.168.127.4");
        demo.addServer("192.168.127.5");
        demo.addServer("192.168.127.6");
        demo.addServer("192.168.127.7");

        for (int i = 0; i < 100; i++) {
            System.out.println("a" + i + "对应的服务器 -> " + demo.getServer("a" + i));
        }
    }
}
