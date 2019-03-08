package com.gxaes.es.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxaes.es.service.StudentService;
import com.gxaes.es.service.StudentServiceImpl;

/**
 * Servlet implementation class postActualAnswer
 */
@WebServlet(urlPatterns="/postActualAnswer.action")
public class postActualAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentService ss = new StudentServiceImpl();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer testId  = Integer.parseInt(request.getParameter("testId"));
		Integer queId  = Integer.parseInt(request.getParameter("queId"));
		String actualAnswer = request.getParameter("actualAnswer");
		ss.postActualAnswer(testId,queId,actualAnswer);
	}

}
