package com.yedam.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service	// bean등록
@Aspect		// advice클래스
public class AfterAdvice {
	
	@AfterReturning(pointcut="BeforeAdvice.allPointcut()", returning="returnObj")
	public void finallyLog(JoinPoint jp, Object returnObj) {
		// 메소드명 확인
		String method = jp.getSignature().getName();
		// 리턴결과 확인
		System.out.println("[사후처리] 비즈니스 로직 수행 후 동작 " + method);
		System.out.println("리턴 결과 : "+ returnObj);
	}
}
