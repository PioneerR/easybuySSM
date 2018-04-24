package com.easybuy.util;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("theLogger")
public class TheLogger {

	private static final Logger log=Logger.getLogger(TheLogger.class);
	
	/*//前置增强
	public void before(JoinPoint jp){
		log.info("调用"+jp.getTarget()+"的"+jp.getSignature().getName()+"方法。方法参数："
				+Arrays.toString(jp.getArgs()));
	}
	
	//后置增强
	public void afterReturning(JoinPoint jp,Object result){
		log.info("调用"+jp.getTarget()+"的"+jp.getSignature().getName()+"方法。返回值："
				+result);
	}
	
	//异常抛出增强
	public void afterThrowing(JoinPoint jp,RuntimeException e){
		log.error(jp.getSignature().getName()+"方法发生异常："+e);
	}
	
	//最终增强
	public void after(JoinPoint jp){
		log.info(jp.getSignature().getName()+"方法结束执行。");
	}*/
	
	//环绕增强
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
