package com.example.demo.test;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Scope(value="prototype")
public class TestLazyComponent2 {

	static {
		System.err.println("init TestLazyComponent2 static code..");
	}
	
	public void say() {
		System.out.println("hello world");
	}
}
