package com.gxaes.es.dao;

import com.gxaes.es.entity.TestDetails;

public interface TestDetailsDao {

	void save(TestDetails testDetails);

	void update(Integer testId, Integer queId, String actualAnswer, Integer correct);

	TestDetails findBytestId(Integer testId, Integer queId);

	Integer findcorrectCount(Integer testId);

}
