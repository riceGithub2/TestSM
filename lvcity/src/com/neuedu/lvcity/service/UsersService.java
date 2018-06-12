package com.neuedu.lvcity.service;

import java.util.List;
import com.neuedu.lvcity.domain.*;

public interface UsersService {
	
	
	public List<Users> findAll();
	public Users findUser(Users u);
	
	public int countUsers();
	public int addUsers(Users u);
	public int updateUsers(Users u);
	public int deleteUsers(int id);
	public Users login(String name, String passwd);
}
