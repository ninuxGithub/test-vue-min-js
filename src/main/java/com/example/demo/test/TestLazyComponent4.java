package com.example.demo.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TestLazyComponent4 {

	static {
		System.err.println("init TestLazyComponent4 static code..");
	}
	
	public void say() {
		System.out.println("hello world");
	}
}
