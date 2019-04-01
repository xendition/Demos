package com.michael.demo.redis.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael
 */
@Controller
@RequestMapping("test")
public class TestService {

    private static final Logger log = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private RedissonService redissonService;
    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping(value = "/test")
    @ResponseBody
    public void test(String recordId) {

        RLock lock = this.redissonService.getRLock(recordId);
        try {
            boolean bs = lock.tryLock(5, 6, TimeUnit.SECONDS);
            if (bs) {
                // 业务代码
                log.info("进入业务代码: " + recordId);

                // redissonClient.createBatch().
                Iterator<String> iterator = this.redissonClient.getKeys().getKeys().iterator();

                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }

                lock.unlock();
            } else {
                Thread.sleep(300);
            }
        } catch (Exception e) {
            log.error("", e);
            lock.unlock();
        }
    }

}
