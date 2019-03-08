package com.gxaes.es.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxaes.es.common.SystemConstants;
import com.gxaes.es.service.StudentService;
import com.gxaes.es.service.StudentServiceImpl;

/**
 * Servlet implementation class PostTestController
 */
@WebServlet(urlPatterns="/PostTestController.action")
public class PostTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StudentService ss = new StudentServiceImpl();  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过testId获取这十道题目
		Integer testId = Integer.parseInt(request.getParameter("testId"));
		String loginName=(String)request.getSession().getAttribute(SystemConstants.USERTDENTITY);
		System.out.println(loginName);
		// 结束考试，保存这次考试信息
		double accuracy = (ss.endTest(testId));
		String accuracy1 = accuracy*100 + "%" ;
		/*
		 * 查询这一次明细记录
		 * topic 、optionABCD、correctAnswer(通过testId查到queId再查question信息)
		 * 我的答案actualAnswer(通过testId查到actualAnswer)
		 * 
		 */
		response.setCharacterEncoding("UTF-8");
		List<Map<String,Object>> result = ss.getresult(testId);
		request.setAttribute("result", result);
		
		request.setAttribute("accuracy", accuracy1);
		request.setAttribute("loginName", loginName);
		request.getRequestDispatcher("/testresult.jsp").forward(request, response);
	}
}
