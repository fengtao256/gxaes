package com.gxaes.es.service;

import java.util.List;
import java.util.Map;

import com.gxaes.es.common.QuestionPage;
import com.gxaes.es.entity.Question;
import com.gxaes.es.entity.Student;
import com.gxaes.es.entity.Test;
import com.gxaes.es.entity.TestDetails;

public interface StudentService {

	boolean register(Student stu);

	boolean login(String loginName, String pwd);

	/*
	 * 创建一次考试
	 * 定义一个stuTD
	 */
	void createTest(String loginName);

	Question loadQuestion(Integer testId, Integer page, Integer pageSize);

	void postActualAnswer(Integer testId, Integer queId, String actualAnswer);

	Test findTest(String loginName);

	TestDetails findTestDetails(Integer testId, Integer integer);

	double endTest(Integer testId);

	List<Map<String, Object>> getresult(Integer testId);

	Student findByLoginName(String loginName);

	QuestionPage getErrorQuestion(Integer stuId,Integer pageNo,Integer pageSize);

	void favoriteQuestion(Integer stuId, Integer queId);

	QuestionPage getFavorite(Integer stuId, Integer pageNo, Integer pageSize);



}
