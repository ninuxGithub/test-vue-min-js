package com.example.demo.thread;

public class JoinTest {

	public static void main(String[] args) {
		JoinTest.testJoin();
	}

	public static void testJoin() {
		Thread a = new Thread(new Runnable() {

			@Override
			public void run() {
				printNum("A");

			}
		});
		Thread b = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("B等待A执行完毕...");
					// 如果我们希望 B 在 A 全部打印 完后再开始打印呢？我们可以利用 thread.join() 方法
					a.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				printNum("B");

			}
		});

		a.start();
		b.start();
	}

	private static void printNum(String threadName) {
		int i = 0;
		while (i < 3) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(threadName + " run " + i);
			i++;
		}
	}

}
