package com.example.demo.tryresources;

public class TryTest implements AutoCloseable {

	public static void main(String[] args) {
		TryTest.test();
	}

	static void test() {
		try (TryTest t = new TryTest()) {
			System.out.println(t.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {

	}

}
