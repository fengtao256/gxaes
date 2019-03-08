package com.gxaes.es.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxaes.es.entity.Student;
import com.gxaes.es.service.StudentService;
import com.gxaes.es.service.StudentServiceImpl;

/**
 * 这是一个servlet类，用于获取我们的表单数据，响应浏览器的post请求
 */
@WebServlet(urlPatterns="/register.action")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 响应浏览器的post请求
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单数据
		String loginName = request.getParameter("loginName");
		String pwd = request.getParameter("pwd");
		String pwdConfirm = request.getParameter("pwdConfirm");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		//System.out.println(loginName);
		if(pwd.equals(pwdConfirm) == false){
			response.sendRedirect("/register_succes.jsp");
		}
		//封装成实体对象（OOP）
		Student stu = new Student(loginName,pwd,email,mobile);
		//调用业务逻辑层，执行处理业务
		StudentService studentService = new StudentServiceImpl() ;
		boolean success = studentService.register(stu);
		// 响应
		if(success){
			response.sendRedirect(request.getContextPath()+"/register_succes.jsp");
		}else{
			response.sendRedirect(request.getContextPath()+"/register_fail.jsp");
		}
	}

}
