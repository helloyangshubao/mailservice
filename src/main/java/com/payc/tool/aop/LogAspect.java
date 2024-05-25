package com.payc.tool.aop;


import com.payc.tool.annotation.Log;
import com.payc.tool.constants.BaseConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author dyy
 * @see {http://www.importnew.com/13367.html, https://www.cnblogs.com/jianjianyang/p/4910851.html}
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    @Around("@annotation(aroundLog)")
    public Object around(ProceedingJoinPoint point, Log aroundLog) throws Throwable {
        long start = System.currentTimeMillis();
        String className = point.getSignature().getDeclaringTypeName();
        String funName = point.getSignature().getName();
        Object[] objectArgs = point.getArgs();
        try {
            return point.proceed();
        } finally {
            long cost = System.currentTimeMillis() - start;
            if (cost >= BaseConstants.TIME_WARNING) {
                log.warn("{}#{} >> {} >> {}ms", className, funName, objectArgs, cost);
            } else {
                log.info("{}#{} >> {} >> {}ms", className, funName, objectArgs, cost);
            }
        }
    }
}
