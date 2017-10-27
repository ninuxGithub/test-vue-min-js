package com.example.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.bean.ResultBean;

@Component
@Aspect
public class ControllerAop2 {

	private static final Logger logger = LoggerFactory.getLogger(ControllerAop2.class);

	// @Pointcut("execution(* com.example.demo.controller..*(..)) and
	// @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	@Pointcut("@annotation(com.example.demo.aop.ResultBeanAnnotation)")
	public void controllerPointCut() {
	};

	@Around("controllerPointCut()")
	public Object controllerAopProccess(ProceedingJoinPoint point) throws Throwable {
		logger.info("环绕通知");
		long start = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		String methodName = method.getName();
		logger.info("方法签名：{}", point.getSignature().toString());

		ResultBean<?> resultBean = null;

		resultBean = (ResultBean<?>) point.proceed();
		long end = System.currentTimeMillis();
		logger.info(" controllerPointCut 拦截了 {}方法, 消耗的时间为：{} ms", methodName, (end - start));
		return resultBean;
	}

	private ResultBean<?> handleAopException(ProceedingJoinPoint point, Throwable e) {
		ResultBean<?> resultBean = new ResultBean<>();
		// TODO 判断e的类型
		logger.error(point.getSignature() + "error:{}", e.getMessage());
		resultBean.setCode(ResultBean.FAIL);
		resultBean.setMsg(e.toString());
		return resultBean;
	}

	@Before("controllerPointCut() && args(name)")
	public void doAccessCheck(String name) {
		logger.info(name);
		logger.info("前置通知");
	}
	
	@AfterReturning("controllerPointCut()")
	public void doAfter() {
		logger.info("后置通知");
	}

	@After("controllerPointCut()")
	public void after() {
		logger.info("最终通知");
	}

	/**
	 * 始终没有进入AfterThrowing , 结果调试发现是在around中try-catch了，所以异常已经被捕获了
	 * @param e
	 */
	@AfterThrowing(value = "controllerPointCut()",throwing="e")
	public void doAfterThrow(Throwable e) {
		logger.info("例外通知"+ e.getMessage());
	}

}
