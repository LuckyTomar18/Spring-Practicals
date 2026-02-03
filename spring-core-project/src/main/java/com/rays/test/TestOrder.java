package com.rays.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestOrder {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("user.xml");
		
		Order o = (Order)context.getBean("order");
		
		System.out.println(o.getOrderNo());
		System.out.println(o.getItem());
		System.out.println(o.getName());
		
	}

}
