package com.rays.dao;

import com.rays.dto.UserDTO;

public interface UserDAOInt {

	public long nextPk();

	public long add(UserDTO dto);
	
	public void update( UserDTO dto);
	
	public void delete( int id);

}