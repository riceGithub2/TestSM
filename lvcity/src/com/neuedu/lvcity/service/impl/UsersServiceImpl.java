package com.neuedu.lvcity.service.impl;

import java.sql.Connection;
import java.util.List;

import com.neuedu.lvcity.domain.*;
import com.neuedu.lvcity.service.UsersService;
import com.neuedu.lvcity.common.*;
import com.neuedu.lvcity.dao.*;
import com.neuedu.lvcity.dao.impl.*;

public class UsersServiceImpl implements UsersService {
	
	/**
	 * 类实例
	 */
	private static final UsersService instance = new UsersServiceImpl();

	/**
	 * 取得实例
	 * 
	 * @return 实例对象
	 */
	public static UsersService getInstance() {
		return instance;
	}

	/**
	 * 构造方法
	 */
	private UsersServiceImpl() {
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		//声明数据库连接对象，用于保存数据库连接对象
				Connection conn = null;
				//声明变量，用于保存数据库查询结果
				List<Users> users = null;
				try{
					//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
					conn = DBUtils.getConnection();
					//创建userDao的实现类对象
					UserDao userDao = new UserDaoImpl(conn);
					//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
					users = userDao.findAll();			
				
				} catch (Exception e) {
					//将异常封装成自定义异常并抛出
					throw new ServiceException("查询所有用户错误", e);
				} finally {
					//调用数据库工具类的closeConnection方法，关闭连接
					DBUtils.closeConnection(conn);
				}
				//返回数据库查询结果
				return users;
	}

	@Override
	public Users findUser(Users u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addUsers(Users u) {
		// TODO Auto-generated method stub
	//声明数据库连接对象，用于保存数据库连接对象
			Connection conn = null;
			//声明变量，用于保存数据库查询结果
			int result = 0;
			try{
				//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
				conn = DBUtils.getConnection();
				//创建userDao的实现类对象
				UserDao userDao = new UserDaoImpl(conn);
				//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
				result = userDao.addUsers(u);			
			
			} catch (Exception e) {
				//将异常封装成自定义异常并抛出
				throw new ServiceException("查询所有用户错误", e);
			} finally {
				//调用数据库工具类的closeConnection方法，关闭连接
				DBUtils.closeConnection(conn);
			}
			//返回数据库查询结果
			return result;
	}

	@Override
	public int updateUsers(Users u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUsers(int id) {
		// TODO Auto-generated method stub
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		//声明变量，用于保存数据库查询结果
		int result = 0;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//创建userDao的实现类对象
			UserDao userDao = new UserDaoImpl(conn);
			//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
			result = userDao.deleteUsers(id);			
		
		} catch (Exception e) {
			//将异常封装成自定义异常并抛出
			throw new ServiceException("删除用户错误", e);
		} finally {
			//调用数据库工具类的closeConnection方法，关闭连接
			DBUtils.closeConnection(conn);
		}
		//返回数据库查询结果
		return result;
	}

	@Override
	public Users login(String name, String passwd) {
		// TODO Auto-generated method stub
		Users user = null;
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			//System.out.println("OK");
			UserDao userDao = new UserDaoImpl(conn);
			//DBUtils.beginTransaction(conn);
			user = userDao.login(name, passwd);
			/*System.out.println("KO");
			System.out.println(user.getName());*/
			//DBUtils.commit(conn);
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询用户错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return user;
	}
	
	
}
