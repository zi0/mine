package com.yedam.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service	// bean등록
@Aspect		// advice클래스
public class BeforeAdvice {
	@Pointcut("execution(* com.yedam.app..*Impl.*(..))")	// pointcut expression
	public void allPointcut() {}	// pointcut id
	
/*	@Pointcut("execution(* com.yedam.app..*Impl.get(..))")	// pointcut expression
	public void getPointcut() {}	// pointcut id
*/	
	@Before("allPointcut()")	// 실행할 어드바이스 위에 동작시점 설정
		public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		System.out.println("[사전처리] 비즈니스 로직 수행 전 동작 "+ method);
		if (args != null && args.length > 0) {
			System.out.println(args[0]);
		}
	}
}
