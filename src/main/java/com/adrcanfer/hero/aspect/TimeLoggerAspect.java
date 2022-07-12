package com.adrcanfer.hero.aspect;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeLoggerAspect {
	
	private static final Logger LOGGER = LogManager.getLogger(TimeLoggerAspect.class);
	
	@Around("@annotation(com.adrcanfer.hero.annotation.TimeLogger)")
	public Object timeLogger(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		
		Object res =  joinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		LOGGER.info("Time: " + (end - start) + " ms");
		
		return res;
		
	}
	
	

}
