package com.easybuy.aop;


import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class TheLogger {

	private static final Logger log=Logger.getLogger(TheLogger.class);
	//通过注解，配置切入点
	@Pointcut("execution(* com.easybuy.service..*.*(..))")
	public void pointcut(){}

	//环绕增强
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint jp)throws Throwable{
		//前置增强
		log.info("调用"+jp.getTarget()+"的"+jp.getSignature().getName()+"方法。参数列表："
				+Arrays.toString(jp.getArgs()));
		try {
			//方法执行后，返回结果
			Object result=jp.proceed();
			//后置增强
			log.info("调用"+jp.getTarget()+"的"+jp.getSignature().getName()+"方法。方法返回值"
					+result);
			return result;			
		} catch (Exception e) {
			//异常抛出增强
			log.error(jp.getSignature().getName()+"方法发生异常"+e);
			throw e;
		} finally {
			//最终增强
			log.info(jp.getSignature().getName()+"方法执行结束");
		}
	}
	
}
