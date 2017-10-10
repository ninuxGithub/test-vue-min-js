package com.example.demo.test;

public class B {
	
	private String symbol;
	
	public B() {
		System.out.println("super B...");
	}
	public B(String symbol) {
		this.symbol = symbol;
		System.out.println("super B ..."+ this.symbol);
	}

	static {
		System.out.println("registry Driver.....");
	}

	public void play() {
		System.out.println("play basketball....");
	}

	public String calculate() {
		return "Hello B";
	}

}
