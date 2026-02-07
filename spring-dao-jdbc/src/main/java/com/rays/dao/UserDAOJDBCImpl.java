package com.rays.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rays.dto.UserDTO;

@Repository
public class UserDAOJDBCImpl implements UserDAOInt {

	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource = null;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public long nextPk() {
		String sql = "select max(id) from st_user";
		Long maxId = jdbcTemplate.queryForObject(sql, Long.class);
		if (maxId == null) {
			return 1; // table empty
		}
		return maxId + 1;
	}
	
	public long add(UserDTO dto) {
		long pk = nextPk();
		String sql = "insert into st_user values(?, ?, ?, ?, ?)";
		int i = jdbcTemplate.update(sql, pk, dto.getFirstName(), dto.getLastName(), dto.getLogin(), dto.getPassword());
		return i;
	}

	public void update(UserDTO dto) {
		String sql = "update st_user set first_Name = ?, last_Name = ?, login = ?, password = ? where id = ?";
		int i = jdbcTemplate.update(sql, dto.getFirstName(), dto.getLastName(), dto.getLogin(), dto.getPassword(),
				dto.getId());
		System.out.println("record updated successfully: " + i);
	}


	public void delete(int id) {
		String sql = "delete from st_user where id = ?";
		int i = jdbcTemplate.update(sql, id);
		System.out.println("record deleted: " + i);
		
	}
		
	}

