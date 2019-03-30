package com.michael.demo.spring.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

/**
 * 启动类
 *
 * @author Michael
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("");
        logger.info("$$$$$ 启动成功! $$$$$");
        logger.info("");
    }

    /**
     * <pre>
     *
     * 网传可以解决 RequestContextHolder.getRequestAttributes() 空指针的问题(未验证是否存在)
     *
     * https://blog.csdn.net/qq_38846242/article/details/83382969
     *
     * </pre>
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
