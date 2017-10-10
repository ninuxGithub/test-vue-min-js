package com.example.demo.test;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class ProxyTest {

	private static final Logger logger = LoggerFactory.getLogger(ProxyTest.class);

	public static void main(String[] args) throws Exception {
		reflectTest();
		System.out.println("========================================================================");

		jdkDynamicProxy();

		System.out.println("========================================================================");
		cglibDynamicProxy();

	}

	private static void cglibDynamicProxy() {
		ProxyTest testClassForName = new ProxyTest();
		CglibProxy cglibProxy = testClassForName.new CglibProxy();
		B b = new B();
		System.out.println("original hashcode:" + b.hashCode());
		B proxyInstance = (B) cglibProxy.getProxyInstance(new B());
		System.out.println("proxy hashcode:" + proxyInstance.hashCode());
		proxyInstance.play();
		String calculate = proxyInstance.calculate();
		System.out.println(calculate);
	}

	/**
	 * CGLIB原理
	 * CGLIB原理：动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。它比使用java反射的JDK动态代理要快。
	 * CGLIB底层：使用字节码处理框架ASM，来转换字节码并生成新的类。不鼓励直接使用ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。
	 * CGLIB缺点：对于final方法，无法进行代理。
	 */
	private class CglibProxy implements MethodInterceptor {

		private Object target;

		public Object getProxyInstance(Object target) {
			this.target = target;
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(target.getClass());
			enhancer.setCallback(this);
			enhancer.setClassLoader(this.getClass().getClassLoader());
			return enhancer.create();
		}

		@Override
		public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
			if (null != args) {
				logger.info("args:" + Arrays.toString(args));
			}

			if (null != proxy) {
				logger.info("proxy is :{}", proxy);
			}

			if (null != method) {
				logger.info("method is:{}", method);
			}

			if (null != methodProxy) {
				logger.info("methodProxy {}", methodProxy.toString());
			}
			return methodProxy.invoke(target, args);
		}

	}

	/**
	 * 反射的测试
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static void reflectTest() throws Exception {
		Class<?> clazz = Class.forName("com.example.demo.A");
		A instance = (A) clazz.newInstance();
		String calculate = instance.calculate();
		logger.info(calculate);
		instance.play();
	}

	/**
	 * ProxyCreator 内部实现了implements InvocationHandler 是jdk 的动态代理 特定：代理类必选实现特定的接口！！！！
	 * 
	 * 和cglib 不同的是， cilib 的被代理的类不需要实现特定的接口
	 * 
	 * A --> InterfaceA
	 */
	private static void jdkDynamicProxy() {
//		InterfaceA proxy = ProxyCreator.getProxy(InterfaceA.class, new ObjectFactory<InterfaceA>() {
//
//			@Override
//			public InterfaceA getObject() throws BeansException {
//				return getInterfaceA();
//			}
//		});
//
//		logger.info("proxy hashcode:{}", proxy.hashCode());
//
//		proxy.play();
	}

	protected static InterfaceA getInterfaceA() {
		A a = new A();
		logger.info("original hashcode:{}", a.hashCode());
		return a;
	}

}
