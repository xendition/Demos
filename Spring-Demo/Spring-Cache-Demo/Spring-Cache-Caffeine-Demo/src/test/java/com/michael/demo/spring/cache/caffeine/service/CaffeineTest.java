package com.michael.demo.spring.cache.caffeine.service;

import com.michael.demo.spring.cache.caffeine.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Caffeine 压测
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource({
        "classpath:conf/db.properties"
})
public class CaffeineTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {

        String id = "54e5393f546011e99b830242ac110003";

        int count = Runtime.getRuntime().availableProcessors() * 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, () -> System.out.println("所有线程均到达栅栏位置，开始并发"));

        for (int i = 0; i < count; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + " 准备好了");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " 开始执行" + this.userService.getUser(id));

                // User user = this.userService.getUser(id);
                // System.out.println(user);
            }).start();
        }
    }
}
