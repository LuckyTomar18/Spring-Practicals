package com.rays.test;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.rays.dto.UserDTO;

import com.rays.service.UserServiceInt;

@Component("testUserService")
public class TestUserService {

	@Autowired
	public UserServiceInt service = null;

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		TestUserService test = (TestUserService) context.getBean("testUserService");

		// test.testAdd();
		// test.testUpdate();
		// test.testDelete();
		// test.testFindByPk();
		// test.testFindByLogin();
		// test.testAuthenticate();
		//test.testSearch();

	}

	public void testAdd() {
		UserDTO dto = new UserDTO();
		
		dto.setFirstName("lucky");
		dto.setLastName("tomar");
		dto.setLogin("abc@gmail.com");
		dto.setPassword("abc123");
		long pk = service.add(dto);
		System.out.println("Data Inserted... pk = " + pk);
	}

	public void testUpdate() {
		UserDTO dto = new UserDTO();
		dto.setId(1);
		dto.setFirstName("Lucky");
		dto.setLastName("Tomar");
		dto.setLogin("abc@gmail.com");
		dto.setPassword("abc123");
		service.update(dto);
		System.out.println("Data Updated... ");
	}

	public void testDelete() {

		service.delete(1);
		System.out.println("Data Deleted");
	}

	public void testFindByPk() {

		UserDTO dto = new UserDTO();
		dto = service.findByPk(2);

		System.out.println(dto.getFirstName());
		System.out.println(dto.getLastName());
		System.out.println(dto.getLogin());
		System.out.println(dto.getPassword());
	}

	public void testFindByLogin() {

		UserDTO dto = new UserDTO();

		dto = service.findByLogin("abc@gmail.com");

		System.out.println(dto.getFirstName());
		System.out.println(dto.getLastName());
		System.out.println(dto.getLogin());
		System.out.println(dto.getPassword());
	}

	public void testSearch() {

		List<UserDTO> list = service.search(null, 1, 5);

		Iterator<UserDTO> it = list.iterator();
		while (it.hasNext()) {
			UserDTO dto = it.next();

			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
		}
	}
	
	public void testAuthenticate() {
		
		UserDTO dto = service.authenticate("abc@gmail.com", "abc123");
		
		System.out.println(dto.getId());
		System.out.println(dto.getFirstName());
		System.out.println(dto.getLastName());
		System.out.println(dto.getLogin());
		System.out.println(dto.getPassword());
	}
}