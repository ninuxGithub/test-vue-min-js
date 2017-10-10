package com.example.demo.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	
	@Autowired
	BeanFactory beanFactory;
	
	public void dosometh() {
		TestLazyComponent bean = beanFactory.getBean(TestLazyComponent.class);
		bean.say();
	}
}
