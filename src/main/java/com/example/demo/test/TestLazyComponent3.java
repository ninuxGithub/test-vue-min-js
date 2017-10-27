package com.example.demo.test;

import org.springframework.stereotype.Component;

@Component
public class TestLazyComponent3 {

	static {
		System.err.println("init TestLazyComponent3 static code single..");
	}
	
	public void say() {
		System.out.println("hello world");
	}
}
