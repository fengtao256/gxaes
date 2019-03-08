package com.gxaes.es.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxaes.es.common.SystemConstants;
import com.gxaes.es.entity.Student;

import com.gxaes.es.service.StudentService;
import com.gxaes.es.service.StudentServiceImpl;

/**
 * Servlet implementation class PostFavorite
 */
@WebServlet(urlPatterns="/addFavorite.action")
public class AddFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService ss = new StudentServiceImpl();   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 得到queId
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");
		int queId = Integer.parseInt(request.getParameter("queId"));
		//获取用户名
		String loginName=(String)request.getSession().getAttribute(SystemConstants.USERTDENTITY); 
		//查到当前考试test
		Student st = ss.findByLoginName(loginName);
		ss.favoriteQuestion(st.getStuId(),queId);
		
	}

}
