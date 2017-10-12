package com.example.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//http://www.importnew.com/26850.html
public class CallableTest {

	private static final Logger logger = LoggerFactory.getLogger(CallableTest.class);

	public static void main(String[] args) {
		CallableTest.testCallable();
		CallableTest.testCallableExcutor();
	}

	/**
	 * 可以看到，主线程调用 futureTask.get() 方法时阻塞主线程；然后 Callable 内部开始执行，并返回运算结果；此时
	 * futureTask.get() 得到结果，主线程恢复运行
	 */
	public static void testCallable() {
		Callable<Integer> call = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("开始计算");
				int sum = 0;
				for (int i = 0; i < 100; i++) {
					sum += i;
				}
				Thread.sleep(2000);
				System.out.println("计算完毕");
				return sum;
			}
		};

		FutureTask<Integer> task = new FutureTask<>(call);
		new Thread(task).start();

		try {
			System.out.println("开始获取结果：");
			Integer sum = task.get();
			System.out.println("sum :" + sum);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testCallableExcutor() {
		logger.debug("begin to initCachMap....");
		Long to = System.currentTimeMillis();
		ExecutorService pool = Executors.newFixedThreadPool(3);
		List<Future<Integer>> futureList = new ArrayList<>();

		Callable<Integer> call = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("开始计算");
				int sum = 0;
				for (int i = 0; i < 100; i++) {
					sum += i;
				}
				Thread.sleep(8000);
				System.out.println("计算完毕");
				return sum;
			}
		};
		futureList.add(pool.submit(call));
		System.out.println(pool.toString());

		// get result
		Future<Integer> future = futureList.get(0);
		try {
			Boolean flag = future.isDone();
			// future 的get 方法是阻塞的
			// System.out.println(future.get());
			while ((null == flag) || !flag) {
				System.out.println("main thread is sleeping ");
				Thread.sleep(1000);
				flag = future.isDone();
			}
			if (future.isDone() && future.get() != null) {
				System.out.println("final result is :" + future.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		pool.shutdown();
		System.out.println(pool.toString());
		pool = null;
		futureList = null;
		Long tx = System.currentTimeMillis();
		logger.debug("initCachMap spend " + (tx - to) + "ms");

	}

}
