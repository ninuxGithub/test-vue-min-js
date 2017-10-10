package com.example.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.test.TestLazyComponent;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
	
	@Autowired
	BeanFactory beanFactory;

	//组件懒加载
	public void dosometh() {
		TestLazyComponent bean = beanFactory.getBean(TestLazyComponent.class);
		bean.say();
	}

	@Override
	public void run(String... arg0) throws Exception {
		dosometh();
	}
}
