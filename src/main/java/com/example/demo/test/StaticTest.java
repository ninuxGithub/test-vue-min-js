package com.example.demo.test;

import static java.lang.System.out;

public class StaticTest {

	class NonStatic {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	static class StaticClass {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}


	public static void main(String[] args) {
		StaticTest st = new StaticTest();
		NonStatic nonStatic = st.new NonStatic();
		out.println(nonStatic.getName());

		StaticClass staticClass = new StaticTest.StaticClass();
		out.println(staticClass.getName());
		
		StaticClass staticClass2 = new StaticClass();
		String name = staticClass2.getName();
		System.out.println(name);
		
	}

}
