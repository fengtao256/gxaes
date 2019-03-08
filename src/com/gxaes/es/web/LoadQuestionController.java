package com.gxaes.es.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.alibaba.fastjson.JSON
import com.gxaes.es.common.SystemConstants;

import com.gxaes.es.entity.Question;
import com.gxaes.es.entity.Test;
import com.gxaes.es.entity.TestDetails;
import com.gxaes.es.service.StudentService;
import com.gxaes.es.service.StudentServiceImpl;

/**
 * Servlet implementation class LoadQuestionController
 */
@WebServlet(urlPatterns="/LoadQuestionController.action")
public class LoadQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private StudentService ss = new StudentServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//需要查看的题目序号
		Integer page = 1;
		Integer pageSize = 1 ;
		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));}
		//获取用户名
		String loginName=(String)request.getSession().getAttribute(SystemConstants.USERTDENTITY); 
		//查到当前考试test信息
		Test test = ss.findTest(loginName);		
		//查找当前十道题目，通过testId查找出题号
		Integer testId = test.getTestId();
		//根据题号查找出题目
		Question question = ss.loadQuestion(testId,page,pageSize);
		//查到testdetails信息，获取actualAnswer
		TestDetails td = ss.findTestDetails(testId,question.getQueId());
		//json数据
		String json = "{\"topic\":\""+question.getTopic()+
				"\",\"queId\":\""+question.getQueId()+
				"\",\"optionA\":\""+question.getOptionA()+
				"\",\"optionB\":\""+question.getOptionB()+
				"\",\"optionC\":\""+question.getOptionC()+
				"\",\"optionD\":\""+question.getOptionD()+
				"\",\"testId\":\""+testId+
				"\",\"actualAnswer\":\""+td.getActualAnswer()+
				"\"}";
		//发送json数据
		response.getWriter().write(json);

	}

}
