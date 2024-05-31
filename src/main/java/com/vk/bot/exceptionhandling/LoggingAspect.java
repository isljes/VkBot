package com.vk.bot.exceptionhandling;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(com.vk.bot.*))")
    public void anyMethod(){};
    @Pointcut("within(com.vk.bot.service.*)")
    public void anyMethodFromService(){};

    @Around("anyMethodFromService()")
    public Object logMethodCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        log.debug("Call method {}()", methodName);

        Object result = thisJoinPoint.proceed();

        log.debug("Method {}() run", methodName);
        return result;
    }


    @AfterThrowing(
            pointcut = "anyMethod()",
            throwing = "ex"
    )
    public void afterThrowingAdvice(JoinPoint thisJoinPoint, Exception ex) {
        String methodName = thisJoinPoint.getSignature().getName();
        log.error("Method {}() throws: ", methodName, ex);
    }
}
