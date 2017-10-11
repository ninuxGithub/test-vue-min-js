package com.example.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.bean.ResultBean;

@Component
@Aspect
public class ControllerAop {

	private static final Logger logger = LoggerFactory.getLogger(ControllerAop.class);

	//@Pointcut("execution(* com.example.demo.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	@Pointcut("@annotation(com.example.demo.aop.ResultBeanAnnotation)")
	public void controllerPointCut() {
	};

	@Around("controllerPointCut()")
	public Object controllerAopProccess(ProceedingJoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		String methodName = method.getName();
		logger.info("方法签名：{}",point.getSignature().toString());
		logger.info("controllerPointCut 拦截了 {}方法",methodName);

		ResultBean<?> resultBean = null;

		try {
			resultBean = (ResultBean<?>) point.proceed();
		} catch (Throwable e) {
			resultBean = handleAopException(point, e);
		}
		
		System.out.println(resultBean);

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

}
