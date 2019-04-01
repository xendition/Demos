package com.michael.demo.jdk.annotation.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 缓存切面
 *
 * @author Michael
 * @see MyCache
 */
@Component
@Aspect
public class MyCacheAspect {

    @Around("@annotation(MyCache)")
    public Object doMycache(ProceedingJoinPoint joinPoint) throws Throwable {

        // 0. 使用反射:从注解里面读取KEY的生成规则(EL表达式形式的key，比如 'michael_' + #id)
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(
                signature.getName(), signature.getMethod().getParameterTypes()
        );

        MyCache mycache = method.getAnnotation(MyCache.class);
        String keyEL = mycache.key();

        // 1. 根据key的规则，通过SpringEL表达式进行解析
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(keyEL);

        // 获取所有参数名
        String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
        // 获取所有参数值
        Object[] args = joinPoint.getArgs();

        // 设置解析上下文(有哪些占位符，以及每种占位符的值)
        EvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        // 解析成最终的缓存KEY值
        String resultKey = expression.getValue(context).toString();

        // 2. 根据 key 值判定缓存中是否存在结果数据，如果有，直接返回

        // 3. 当缓存中不存在数据时，调用业务方法获取执行结果，并根据 key 值缓存结果


        return null;
    }

}
