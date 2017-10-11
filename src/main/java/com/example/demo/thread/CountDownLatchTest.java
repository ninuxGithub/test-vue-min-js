package com.example.demo.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) {
		CountDownLatchTest.runDAfterABC();
	}

	/**
	 * 我们希望达到的目的是：A B C 三个线程同时运行，各自独立运行完后通知 D；对 D 而言，只要 A B C 都运行完了，D
	 * 再开始运行。针对这种情况，我们可以利用 CountdownLatch 来实现这类通信方式。它的基本用法是：
	 */
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
