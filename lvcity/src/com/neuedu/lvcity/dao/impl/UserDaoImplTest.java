package com.neuedu.lvcity.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.model.Users;

public class UserDaoImplTest {
	Connection connection = DBUtils.getConnection();
	UserDaoImpl sDaoImpl = new UserDaoImpl(connection);
	@Test
	@Ignore
	public void testCountUsers() {
		int count =sDaoImpl.countUsers();
		System.out.println(count);		
	}

	@Test      //@Test(expected = NullPointerException.class)  
	public void testFindAll() {
		List<Users> users = sDaoImpl.findAll();
		System.out.println(users.size());
	}

}
