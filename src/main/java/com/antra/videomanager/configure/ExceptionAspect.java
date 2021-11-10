package com.antra.videomanager.configure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class ExceptionAspect {

    Logger logger = LogManager.getLogger(ExceptionAspect.class);

//    @Before("execution(* com.antra.videomanager.tool.mapper.support.BasicMapper.readValue(..))")
//    public Object exceptionHandlerWithReturnType(ProceedingJoinPoint joinPoint) throws Throwable{
//        Object obj = null;
//        logger.info("current get method name is {}", ((MethodSignature)joinPoint.getSignature()).getMethod());
//        try {
//            obj = joinPoint.proceed();
//        } catch(LazyInitializationException ex) {
//            throw ex;
//        }
//        return obj;
//    }
}
