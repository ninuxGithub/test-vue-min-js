package com.example.demo.test;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class TestLazyComponent {

	static {
		System.err.println("init TestLazyComponent static code..");
	}
	
	public void say() {
		System.out.println("hello world");
	}
}
