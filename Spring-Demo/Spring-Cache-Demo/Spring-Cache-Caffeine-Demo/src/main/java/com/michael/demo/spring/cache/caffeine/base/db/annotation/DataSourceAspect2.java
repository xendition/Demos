package com.michael.demo.spring.cache.caffeine.base.db.annotation;

import com.michael.demo.spring.cache.caffeine.base.db.DataSourceHolder;
import com.michael.demo.spring.cache.caffeine.base.db.DataSourceType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * DataSource 解析类
 *
 * @author Michael
 * @date 2018/6/11.
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect2 {

    public static final Logger logger = LoggerFactory.getLogger(DataSourceAspect2.class);

    @Around("@annotation(DataSource)")
    public Object doSql(ProceedingJoinPoint joinPoint) throws Throwable {

        DataSourceType dataSourceType = DataSourceType.MASTER;
        Class className = joinPoint.getTarget().getClass();

        String methodName = joinPoint.getSignature().getName();
        Class[] argClass = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        Method method = className.getMethod(methodName, argClass);

        // 方法注解优先级最高
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource dataSource = method.getAnnotation(DataSource.class);
            dataSourceType = dataSource.value();
        } else if (className.isAnnotationPresent(DataSource.class)) {
            DataSource dataSource = (DataSource) className.getAnnotation(DataSource.class);
            dataSourceType = dataSource.value();
        }

        DataSourceHolder.setDataSource(dataSourceType);

        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            logger.error("doSql", e);
            return null;
        } finally {
            DataSourceHolder.clearDataSource();
        }
    }
}
