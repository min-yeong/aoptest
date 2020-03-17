package com.bitacademy.aoptest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	// ProductService의 앞 뒤에서 횡단 관심을 추가 
	// 메서드 명 내부 인자값의 .. 은 내부 인자값을 가리지 않는다
	@Before("execution(public ProductVo com.bitacademy.aoptest.ProductService.findProduct(..))")
	public void beforeAdvice() {
		System.out.println("call [beforeAdvice]");
	}
	
	// com.bitacademy.aoptest 패키지 내의 모든 클래스의 findProduct 이후에 
	@After("execution(* com.bitacademy.aoptest.*.findProduct(..))")
	public void afterAdvice() {
		System.out.println("call [afterAdvice]");
	}
	
	// 결과를 리턴한 이후에 호출, 모든 패키지내의 ProductService를 대상으로 findProduct 메서드 이후에 호출하고 반환 값을 확인 할 수 있다.
	@AfterReturning(value="execution(* *..ProductService.findProduct(..))", returning="vo")
	public void afterReturningAdvice(ProductVo vo) {
		System.out.println("call [afterReturningAdvice]");
		System.out.println("메서드 findProduct가 반환한 객체 : "+ vo);
	}
	
	// Exception이 발생한 경우, 필요하다면 패키지명과 클래스명은 생략가능, exception은 throwing으로 받을 수있다.
	@AfterThrowing(value="execution(* findProduct(String))", throwing="ex")
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("call [afterThrowingAdvice]");
		System.err.println("발생한 에러 : "+ex.getMessage());
	}
}
