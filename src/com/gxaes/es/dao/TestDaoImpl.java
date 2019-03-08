package com.gxaes.es.dao;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gxaes.es.entity.Test;

public class TestDaoImpl extends BaseDao implements TestDao {

	@Override
	public void save(Test test) {
		String sql = "INSERT INTO `gxaes`.`test` (`stuId`, `beginTime`, `endTime`, `accuracy`, `queCount`, `correctCount`) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		Object[] parameters1 = new Object[]{
			test.getStuId(),
			test.getBeginTime(),
			test.getEndTime(),
			test.getAccuracy(),
			test.getQueCount(),
			test.getCorrectCount()
		};
		this.executeUpdate(sql, parameters1);
		//@@identity
		sql = "select @@identity;";
		ScalarHandler<BigInteger> sh = new ScalarHandler<BigInteger>();
		BigInteger testId = this.executeQuery(sql, sh);
		//System.out.println(testId);
		test.setTestId(testId.intValue());
	}
	//返回当前考试test
	@Override
	public Test findLatestBtStuId(Integer stuId) {
		String sql = "select * from test where stuId =? order by testId desc limit 1";
		return this.executeQuery(sql, new BeanHandler<Test>(Test.class), stuId);
	}
	/*
	 * 保存test信息(non-Javadoc)
	 * @see com.gxaes.es.dao.TestDao#update(java.lang.Integer, java.util.Date, double, java.lang.Integer)
	 */
	@Override
	public void update1(Integer testId, Date endTime, double accuracy, Integer correctCount) {
		String sql = "update test set endTime=?,correctCount=?,accuracy=? where testId=?";
		this.executeUpdate(sql,endTime,correctCount,accuracy, testId);
	}

}
