package com.neuedu.lvcity.dao;

import java.util.List;

import com.neuedu.lvcity.model.Users;

public interface UserDao {
	public int countUsers();
	public List<Users> findAll();
	public int addUsers(Users u);
	public int updateUsers(Users u);
	public Users findUser(Users u);
	public int deleteUsers(int id);
	public Users login(String username,String password);
	
}
