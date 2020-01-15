package com.gxaes.es.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

import com.gxaes.es.entity.TestDetails;

public class TestDetailsDaoImpl extends BaseDao implements TestDetailsDao {

	@Override
	public void save(TestDetails testDetails) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO `testdetails` (`testId`, `queId`, `actualAnswer`, `correct`)"
				+ " VALUES (?, ?, ?, ?);";
			/*Connection conn = null ;
			PreparedStatement stmt = null ;
			ResultSet rs = null ;
			try{
				conn = getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,testDetails.getTestId());
				stmt.setInt(2,testDetails.getQueId());
				stmt.setString(3, testDetails.getActualAnswer());
				stmt.setInt(4, testDetails.getCorrect());*/
				this.executeUpdate(sql, testDetails.getTestId(),testDetails.getQueId(),
				testDetails.getActualAnswer(),testDetails.getCorrect());
	}

	@Override
	public void update(Integer testId, Integer queId, String actualAnswer, Integer correct) {
		// TODO Auto-generated method stub
		String sql = "update testdetails set actualAnswer=?,correct=? where testId = ? and queId=?";
		this.executeUpdate(sql, actualAnswer,correct,testId,queId);
	}

	@Override
	public TestDetails findBytestId(Integer testId, Integer queId) {
		// TODO Auto-generated method stub
		String sql = "select * from testdetails where testId = ? and queId=?";
		BeanHandler<TestDetails> bh = new BeanHandler<TestDetails>(TestDetails.class);
		return this.executeQuery(sql, bh, testId,queId);
	}

	@Override
	public Integer findcorrectCount(Integer testId) {
		//正确题目数量
		String sql = "select count(0) from testdetails where testId = ? and correct = 1";
		return this.executeQuery(sql, new ScalarHandler<Long>(), testId).intValue();
	}

}
