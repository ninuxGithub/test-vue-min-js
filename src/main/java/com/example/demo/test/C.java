package com.example.demo.test;

public class C extends B {

	public C() {
		System.out.println("son C....");
	}

	public C(String symbol) {
		super(symbol);
		System.out.println("son C...." + symbol);
	}

	public static void main(String[] args) {
		new B();
		new C();
		new C("ok");

		// registry Driver..... 1.new B();调用父类的静态构造代码块
		// super B...			2.new B(); 执行B类的空参构造器
		// super B...			3.new C(); 执行隐式的super()
		// son C....			4.new C(); 执行自己的空参构造器
		// super B ...ok		5.new C("ok");执行显示调用的super(smybol)
		// son C....ok			6.new C("ok");执行自己的带参数构造函数
	}

}
