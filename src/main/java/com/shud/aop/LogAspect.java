package com.shud.aop;

import com.mongodb.BasicDBObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by shud on 2017/5/23.
 */
@Aspect
@Component
public class LogAspect {
    Logger logger = Logger.getLogger("mongodb");

    @Pointcut("execution(* com.shud.controller.*.*(..))")
    public void controllerAspect(){
        logger.info("enter controller aspect");
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  attributes.getRequest();
        Object result = proceedingJoinPoint.proceed();
        BasicDBObject basicDBObject = getBasicDBObject(request);
        logger.info(basicDBObject);
        return result;
    }

    private Map<String ,String> getHeaderInfo(HttpServletRequest httpServletRequest){
        Map<String,String> result = new HashMap<>();
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = (String) headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            result.put(key,value);
        }
        return result;
    }

    private BasicDBObject getBasicDBObject(HttpServletRequest request) {
        BasicDBObject r = new BasicDBObject();
        r.append("requestURL", request.getRequestURL().toString());
        r.append("requestURI", request.getRequestURI());
        r.append("queryString", request.getQueryString());
        r.append("remoteAddr", request.getRemoteAddr());
        r.append("remoteHost", request.getRemoteHost());
        r.append("remotePort", request.getRemotePort());
        r.append("localAddr", request.getLocalAddr());
        r.append("localName", request.getLocalName());
        r.append("method", request.getMethod());
        r.append("headers", getHeaderInfo(request));
        r.append("parameters", request.getParameterMap());
        r.append("createTime",new Date());
        return r;
    }
}
