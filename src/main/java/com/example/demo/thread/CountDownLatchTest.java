package com.example.demo.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) {
		// CountDownLatchTest.runDAfterABC();
		System.out.println("=======================");
		CountDownLatchTest.runDAfterABCUpdate();

	}

	/**
	 * 我们希望达到的目的是：A B C 三个线程同时运行，各自独立运行完后通知 D；对 D 而言，只要 A B C 都运行完了，D
	 * 再开始运行。针对这种情况，我们可以利用 CountdownLatch 来实现这类通信方式。它的基本用法是：
	 * 
	 * CountDownLatch使用的原理： CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。
	 * 每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时， 它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
	 */
	public static void runDAfterABCUpdate() {
		int worker = 3;
		CountDownLatch countDown = new CountDownLatch(worker);
		for (char c = 'A'; c <= 'D'; c++) {
			final String threadName = String.valueOf(c);
			new Thread(new Runnable() {

				@Override
				public void run() {
					// 如果线程的名称为D那么就等待
					if (threadName.equals("D")) {
						try {
							countDown.await();
							System.out.println("D start workinig");
							System.out.println("D finish work");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println(threadName + " is working...");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(threadName + " finished...");
						countDown.countDown();
					}
				}
			}, String.valueOf(c)/* 定义线程的名称 */).start();
		}
	}

	public static void runDAfterABC() {
		int worker = 3;
		CountDownLatch countDown = new CountDownLatch(worker);
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("D is waiting for other three threads finish working");
				try {
					countDown.await();

					System.out.println("D start working");

					Thread.sleep(100);

					System.out.println("D finish working");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

		for (char c = 'A'; c <= 'C'; c++) {
			final String threadName = String.valueOf(c);
			new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(threadName + " is working...");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(threadName + " finished...");
					countDown.countDown();
				}
			}).start();
			;
		}
	}

}
