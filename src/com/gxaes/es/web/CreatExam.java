package com.gxaes.es.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gxaes.es.common.SystemConstants;
import com.gxaes.es.service.StudentService;
import com.gxaes.es.service.StudentServiceImpl;

/**
 * Servlet implementation class CreatExam
 */
@WebServlet(urlPatterns="/creatExam.action")
public class CreatExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StudentService studentService= new StudentServiceImpl(); 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginName = (String) session.getAttribute(SystemConstants.USERTDENTITY);
		if(loginName == null ){
			//重定向
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			studentService.createTest(loginName);
			//试卷生成成功
			//response.getWriter().write("Create Exam OK !");
			response.sendRedirect(request.getContextPath() + "/exam.jsp");
		}
	}

}
