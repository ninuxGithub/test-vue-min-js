package com.example.demo.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	public static void main(String[] args) {
		CyclicBarrierTest.runABCWhenAllReady();
	}

	/**
	 * 针对 线程 A B C 各自开始准备，直到三者都准备完毕，然后再同时运行 。也就是要实现一种 线程之间互相等待 的效果，那应该怎么来实现呢？
	 * 
	 * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，
	 * CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
	 */
	public static void runABCWhenAllReady() {
		int runner = 3;
		CyclicBarrier barrier = new CyclicBarrier(runner);
		final Random random = new Random();
		for (char c = 'A'; c <= 'C'; c++) {
			final String threadName = String.valueOf(c);
			new Thread(new Runnable() {

				@Override
				public void run() {
					long prepareTime = random.nextInt(10000) + 100;

					try {
						System.out.println("preparetime : " + prepareTime + " ms");
						Thread.sleep(prepareTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						System.out.println(threadName + " is waiting for others");
						barrier.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(threadName + " is running");

				}
			}).start();
		}
	}

}
