package com.application.sso.server.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.tech.application.base.util.IdUtil;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class WebLogAspect {
    
    private ThreadLocal<String> requestId = new ThreadLocal<>();
	private ThreadLocal<Long> startTimestamp = new ThreadLocal<>();


    @Pointcut("execution(public * com.application.sso.server.controller..*.*(..))")
    public void webLog(){}
    
    
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        startTimestamp.set(System.currentTimeMillis());

		log.info("requestId :{}, uri: {}, params: {}", getRequestId(), request.getRequestURI().toString(),
				JSON.toJSONString(request.getParameterMap()));
		
    }
    

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        long costTime = System.currentTimeMillis() - startTimestamp.get();

		log.info("requestId :{}, uri: {}, costTime: {}ms, response: {}", getRequestId(), request.getRequestURI().toString(),
				costTime, JSON.toJSONString(ret));
		
		requestId.set(null);
		startTimestamp.set(null);

    }
    
    
    @AfterThrowing(throwing = "ex", pointcut = "webLog()")
    public void afterThrowing(Exception ex){
    	requestId.set(null);
		startTimestamp.set(null);
    }


    private String getRequestId() {
		String id = requestId.get();
		if (StringUtils.isNotBlank(id)) {
			return id;
		}
		id = IdUtil.generateUUID();
		requestId.set(id);
		return id;
	}
}