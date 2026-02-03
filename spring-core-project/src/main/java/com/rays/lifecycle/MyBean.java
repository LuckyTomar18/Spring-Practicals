package com.rays.lifecycle;

public class MyBean {

	public void init() {
		System.out.println("MyBean bean is created...");
	}

	public void cleanup() {
		System.out.println("container close MyBean bean cleaned..");
	}
}
