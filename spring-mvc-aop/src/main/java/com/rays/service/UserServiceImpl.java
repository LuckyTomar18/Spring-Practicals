
package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

@Service
public class UserServiceImpl implements UserServiceInt {

	@Autowired
	private UserDAOInt dao = null;

	public long add(UserDTO dto)  {

		UserDTO existDto = findByLogin(dto.getLogin());

		if (existDto != null) {
			throw new RuntimeException("login id already exist");
		}

		long pk = dao.add(dto);
		return pk;
	}

	public void update(UserDTO dto)  {
		
		UserDTO existDto = findByLogin(dto.getLogin());
		if (existDto != null && existDto.getId() != dto.getId()) {
			throw new RuntimeException("login id already exist");
		}
		dao.update(dto);
	}

	public UserDTO findByPk(long pk) {
		return dao.findByPk(pk);
	}

	public void delete(long pk) {
		dao.delete(pk);
	}

	public UserDTO findByLogin(String login) {
		return dao.findByLogin(login);
	}

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

	public UserDTO authenticate(String login, String password) {

		return dao.authenticate(login, password);
	}
}
