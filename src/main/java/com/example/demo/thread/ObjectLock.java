package com.example.demo.thread;

/**
 * 场景：
 * 
 * 希望A执行到一半的时候，执行B业务，等B执行完毕后在执行A剩下的业务
 * 
 * 解决方法：采用lock , wait notify
 *
 */
public class ObjectLock {
	private static Object lock = new Object();

	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
			ObjectLock.testObjectLock();
		}
	}

	/**
	 * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，显然 Thread.join()
	 * 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
	 */
	public static void testObjectLock() {
		Thread a = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lock) {
					System.err.println("A 1");
					try {
						System.err.println("A wait...");
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.err.println("A 2");
					System.err.println("A 3");
					lock.notify();
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
