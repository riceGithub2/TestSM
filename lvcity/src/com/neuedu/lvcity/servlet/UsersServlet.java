package com.neuedu.lvcity.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import com.neuedu.lvcity.common.*;
import com.neuedu.lvcity.model.*;
import com.neuedu.lvcity.service.*;
import com.neuedu.lvcity.service.impl.UsersServiceImpl;

/**
 * Servlet implementation class UsersServlet
 */

public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if ("login".equals(action)) {
			doLogin(request, response);
		} else if ("findAll".equals(action)) {
			dofindAll(request, response);
		}else if ("addUser".equals(action)) {
			doAddUser(request, response);
		}else if ("delUser".equals(action)) {
			delUser(request, response);
		}
		
		
	}
	
	private void delUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//取sesion对象
		 HttpSession se = request.getSession();
		//取传递过来的id编号
		int id = Integer.parseInt(request.getParameter("id"));
		//获取Service对象，并调用addUsers方法
		UsersService userService = UsersServiceImpl.getInstance();
		int resultTag = userService.deleteUsers(id);
		
		//result!=0表示增加用户成功
		if(resultTag != 0){
			String result="success";
			response.getWriter().write(result);		
			response.getWriter().flush();
			//查找所有的用户并且存到session中
			List<Users> userList = userService.findAll();			  
			se.setAttribute("userList", userList);	
			//跳转至allUser.jsp页面，看到刚增加的新用户
			response.sendRedirect(request.getContextPath()+ "/Admin/allUser.jsp");
		}
		
	}

	private void doAddUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		  //取sesion对象
		   HttpSession se = request.getSession();
		   
			// 获取表单参数
			String username = request.getParameter("userName");
			int age = Integer.parseInt(request.getParameter("age"));
			
			// 将获取的表单数据放在Users对象
			Users user = new Users();
			user.setName(username);
			user.setAge(age);
			//获取Service对象，并调用addUsers方法
			UsersService userService = UsersServiceImpl.getInstance();
			int result = userService.addUsers(user);
			
			//result!=0表示增加用户成功
			if(result != 0){
				//查找所有的用户并且存到session中
				List<Users> userList = userService.findAll();			  
				se.setAttribute("userList", userList);	
				//跳转至allUser.jsp页面，看到刚增加的新用户
				response.sendRedirect(request.getContextPath()+ "/Admin/allUser.jsp");
			}
	}

	private void dofindAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// 调用Service方法
		UsersService userService = UsersServiceImpl.getInstance();
		List<Users> users = null;
		// 查询所有用户
		users = userService.findAll();			
	}

	/**
	 * 后台用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	     //获取session
         HttpSession se = request.getSession();
		
		// 取得请求参数
		Users user = new Users();
		populate(request, user);

		// 服务器端验证
		String msg = validate(user);
		if (TextUtils.isEmpty(msg)) {			
		// 调用Service方法
		UsersService userService = UsersServiceImpl.getInstance();

		// 检查用户是否存在
		Users dbUser = userService.login(user.getName(),
					user.getPasswd());
			if (dbUser == null) {
				/*msg = "用户名或密码错误";
				request.setAttribute("msg", msg);*/
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			} else {
				   
				    List<Users> userList = userService.findAll();
				   //System.out.println(userList.size());
				   se.setAttribute("userList", userList);
					response.sendRedirect(request.getContextPath()
							+ "/Admin/index.jsp");
				
			}
		}
	    else {			
		request.getRequestDispatcher("/login.jsp").forward(request,
				response);
	}
  }


	private String validate(Users user) {
		String errorMsg = null;
		if (TextUtils.isEmpty(user.getName())) {
			errorMsg = "请输入用户名";
		} /*else if (user.getName().length() < 6
				|| user.getName().length() > 30) {
			errorMsg = "用户名长度在6到30位之间";
		} else if (!user.getName().matches("[a-zA-Z0-9_]{6,30}")) {
			errorMsg = "用户名只能包含由字母、数字或“_”";
		} */else if (TextUtils.isEmpty(user.getPasswd())) {
			errorMsg = "请输入密码";
		} /*else if (TextUtils.isEmpty(user.getCode())) {
			errorMsg = "请输入验证码";
		}*/
		return errorMsg;
	}

	private void populate(HttpServletRequest request, Users user) {	

		// 获取表单参数
		String username = request.getParameter("name");
		String password = request.getParameter("passwd");
		
		user.setName(username);
		user.setPasswd(password);
		
	}
}
