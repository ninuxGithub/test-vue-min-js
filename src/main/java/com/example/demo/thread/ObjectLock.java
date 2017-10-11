package com.example.demo.thread;

public class ObjectLock {

	public static void main(String[] args) {
		ObjectLock.testObjectLock();
	}

	/**
	 * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，显然 Thread.join()
	 * 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
	 */
	public static void testObjectLock() {
		Object lock = new Object();
		Thread a = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lock) {
					System.out.println("A 1");
					try {
						System.out.println("A wait...");
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("A 2");
					System.out.println("A 3");
				}

			}
		});
		Thread b = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lock) {
					System.out.println("B 1");
					System.out.println("B 2");
					System.out.println("B 3");
					System.out.println("B notify...");
					lock.notify();
				}

			}
		});

		a.start();
		b.start();
	}

}
