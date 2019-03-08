package com.gxaes.es.dao;

import java.util.List;
import java.util.Map;

import com.gxaes.es.entity.Question;

public interface QuestionDao {

	List<Question> getRandomQuestion(int examQuestionCount);

	Question getById(Integer queId);

	List<Map<String, Object>> getTestResult(Integer testId);

	List<Map<String,Object>> getErrorQuestionList(Integer stuId,Integer pageNo,Integer pageSize);

	int getErrorCount(Integer stuId);

	void saveFavorite(Integer stuId, Integer queId);
	
	List<Question> getFavoriteQuestionList(Integer stuId,Integer pageNo,Integer pageSize);
	
	int getFavoriteCount(Integer stuId);
}
