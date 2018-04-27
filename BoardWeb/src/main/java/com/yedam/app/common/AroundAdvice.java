package com.yedam.app.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service	// bean등록
@Aspect		// advice클래스
public class AroundAdvice {
	@Pointcut("execution(* com.yedam.app..*Impl.*(..))")	// pointcut expression
	public void allPointcut() {}	// pointcut id
	
	@Around("allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("[around before] : 로직 수행전");
		Object returnObj = pjp.proceed();	// 서비스 메소드
		System.out.println("[around after] : 로직 수행후");
		return returnObj;
	}
}
