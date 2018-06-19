package com.neuedu.lvcity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.lvcity.dao.*;
import com.neuedu.lvcity.model.*;
import com.neuedu.lvcity.common.*;

public class UserDaoImpl implements UserDao {
	
	/**
	 * 数据库连接
	 */
	private Connection conn;

	/**
	 * 构造方法
	 * 
	 * @param conn
	 *            数据库连接
	 */
	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int countUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		//声明变量，用于保存查询结果
		List<Users> list = new ArrayList<Users>();
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;
		//声明结果集对象变量，用于保存数据库查询结果
		ResultSet rs = null;
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			pstam = conn.prepareStatement("select * from users");
			//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
			rs = pstam.executeQuery();
			//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中，所有对象放到集合中
			while(rs.next()){
			//创建用户对象，用于封装当前游标中的各个字段的值
			Users user = new Users();
			user.setId(rs.getInt("id"));			
			user.setName(rs.getString("name"));
			user.setPasswd(rs.getString("passwd"));
			user.setEmail(rs.getString("email"));
			user.setAge(rs.getInt("age"));
			user.setPhone(rs.getString("phone"));
						
			//声明结果集对象变量，用于保存数据库查询结果
			list.add(user);
			}	
		}catch (SQLException e) {
			//如果出现异常，输出异常信息
			System.out.println("在查询全部用户的时候出错了.错误信息是 ：" + e.getMessage());			
		} finally {
			//调用数据库工具类，关闭结果集对象和声明对象
			DBUtils.closeStatement(rs, pstam);
		}
	    //返回查询到的用户列表
		return list;
   }

	@Override
	public int addUsers(Users u) {
		//声明变量，用于保存查询结果
				int result = 0;
				//声明预编译的声明对象变量，用于进行数据库操作的载体
				PreparedStatement pstam = null;				
				try {
					//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
					pstam = conn.prepareStatement("insert into users(name,age) values(?,?)");
					pstam.setString(1, u.getName());
					pstam.setInt(2, u.getAge());
					//调用预编译对象的executeQuery方法，执行查询操作，返回查询结果，赋值给结果集对象变量
					result = pstam.executeUpdate();
					//如果查询结果不为空，将取出结果集中的各个字段，封装在用户对象的各个属性中，所有对象放到集合中
						
				}catch (SQLException e) {
					//如果出现异常，输出异常信息
					System.out.println("增加用户出错.错误信息是 ：" + e.getMessage());			
				} finally {
					//调用数据库工具类，关闭结果集对象和声明对象
					DBUtils.closeStatement(null, pstam);
				}
			    //返回查询到的用户列表
				return result;
	}

	@Override
	public int updateUsers(Users u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Users findUser(Users u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUsers(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		//声明预编译的声明对象变量，用于进行数据库操作的载体
		PreparedStatement pstam = null;
		try {
			//调用连接对象的prepareStatement方法，得到预编译对象，赋值给预编译对象变量
			pstam = conn.prepareStatement("delete from users where id = ?");
			//调用预编译对象的setXxx方法，给？号赋值
			pstam.setInt(1,id);
			//调用预编译对象的executeUpdate方法，执行删除操作，返回删除结果，赋值给变量
			result = pstam.executeUpdate();
						
		} catch (SQLException e) {
			//如果出现异常，输出异常信息
			System.out.println("在删除用户的时候出错了.错误信息是 ：" + e.getMessage());			
		}finally {
			//调用数据库工具类，关闭结果声明对象
			DBUtils.closeStatement(null, pstam);
		}
		return result;
	}

	@Override
	public Users login(String username, String password) {
		// TODO Auto-generated method stub
		// SQL语句
				StringBuffer buff = new StringBuffer();
				buff.append("SELECT * ");				
				buff.append("FROM users ");				
				buff.append("where name=? AND passwd= ? ");
				
				String find_sql = buff.toString();
				
				Users user = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					//设置语句对象，SQL语句条件
					pstmt = conn.prepareStatement(find_sql);
					pstmt.setString(1, username);
					pstmt.setString(2, password);

					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						
						// 解析结果集对象，封装查询结果
						user = new Users();
						user.setId(rs.getInt("id"));
						user.setName(rs.getString("name"));
						user.setPasswd(rs.getString("passwd"));							
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
				return user;
	}

	

}
