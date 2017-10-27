//package com.example.demo.utils;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.demo.bean.ExcelField;
//import com.example.demo.bean.Order;
//
//public class ExtractAnnotationBean {
//
//	public static <T> List<T> extractAnnotation(Class<Order> clazz) {
//		if (null == clazz) {
//			throw new RuntimeException("clazz参数不能为空");
//		}
//
//		List<T> list = new ArrayList<>();
//		for (Class<?> c = clazz; c != Object.class; c = c.getSuperclass()) {
//			Field[] declaredFields = c.getDeclaredFields();
//			for (Field field : declaredFields) {
//				if(field.getClass().getName().equals(clazz.getName())) {
//					System.out.println("field-->"+field);
//				}
//				System.out.println("field-->"+field);
//				ExcelField excelField = field.getAnnotation(ExcelField.class);
//			}
//
//		}
//
//		return list;
//	}
//
//}
