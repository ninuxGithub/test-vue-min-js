package com.example.demo.test;

public class A implements InterfaceA{

	static {
		System.out.println("registry Driver.....");
	}

	public void play() {
		System.out.println("play basketball....");
	}

	public String calculate() {
		return "Hello A";
	}

}
