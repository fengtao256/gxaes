package com.gxaes.es.web;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxaes.es.common.SystemConstants;
import com.gxaes.es.service.StudentService;
import com.gxaes.es.service.StudentServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns="/LoginController.action")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	StudentService studentService = new StudentServiceImpl();
   	/**
	 * 响应post登录请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取表单
		request.setCharacterEncoding("UTF-8");
		String loginName = request.getParameter("loginName");
		String pwd = request.getParameter("pwd");
		
		//调用业务层的方法判断是否成功
		boolean loginSuccces = studentService.login(loginName,pwd);
		//响应
		if(loginSuccces){
			//用Session保存用具登录状态
			request.getSession().setAttribute(SystemConstants.USERTDENTITY, loginName);
			System.out.println("用戶名："+request.getSession().getAttribute(SystemConstants.USERTDENTITY));
			if(request.getParameter("rememberMe") != null){
				//创建一个Cookie类，记录这个用户。Cookie记录返回给浏览器
				Cookie cookie  = new Cookie("rememberMe",loginName);
				cookie.setMaxAge(10*24*60*60);
				response.addCookie(cookie);
			}			
			//传递信息给login.jsp
			response.sendRedirect(request.getContextPath()+"/home.jsp");

//			request.getRequestDispatcher(request.getContextPath()+"/home.jsp").forward(request,response);
		}else{
			request.setAttribute("loginError", "用户名或密码错误，登录失败。");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		}
	}

}
