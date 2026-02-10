
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

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(UserDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(UserDTO dto) {
		dao.update(dto);
	}

	@Transactional(readOnly = true)
	public UserDTO findByPk(long pk) {
		return dao.findByPk(pk);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long pk) {
		dao.delete(pk);
	}

	@Transactional(readOnly = true)
	public UserDTO findByLogin(String login) {
		return dao.findByLogin(login);
	}

	@Transactional(readOnly = true)
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

	public UserDTO authenticate(String login, String password) {
		
		return dao.authenticate(login, password);
	}
}
