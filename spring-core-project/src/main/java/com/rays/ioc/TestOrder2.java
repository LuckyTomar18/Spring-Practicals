package com.rays.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestOrder2 {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("order2.xml");

		Order2 order = context.getBean("order", Order2.class);

		order.bookATicket(2);

	}
}
