package com.gxaes.es.dao;

import java.util.Date;

import com.gxaes.es.entity.Test;

public interface TestDao {

	/*
	 * 保存成功，则生成了testId，我们顺便把他设置给test类的testId值
	 */
	void save(Test test);

	Test findLatestBtStuId(Integer stuId);

	void update1(Integer testId, Date endTime, double accuracy, Integer correctCount);

}
