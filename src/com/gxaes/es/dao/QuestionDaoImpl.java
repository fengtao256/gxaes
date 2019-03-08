package com.gxaes.es.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gxaes.es.entity.Question;

public class QuestionDaoImpl extends BaseDao implements QuestionDao {

	@Override
	public List<Question> getRandomQuestion(int examQuestionCount) {
		
		String sql = "select queId from question order by RAND() LIMIT ?";
		BeanListHandler<Question> blh = new BeanListHandler<Question>(Question.class);
		return this.executeQuery(sql, blh, new Object[]{examQuestionCount});
		/*
		ArrayList<Question> list = new ArrayList<Question>();
		Connection conn = null ;
		PreparedStatement stmt = null ;
		ResultSet rs = null ;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,examQuestionCount);
			rs = stmt.executeQuery();
			while(rs.next()){
				Question q = new Question();
				q.setQueId(rs.getInt("queId"));
				list.add(q);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			closeRT(rs, stmt);
			if(noTransaction()){
				closeConnection();
			}
		}
		return list;*/
	}

	@Override
	public Question getById(Integer queId) {
		String sql = "select * from Question where queId = ?";
		return this.executeQuery(sql, new BeanHandler<Question>(Question.class), queId);
	}
	/*
	 * 返回此次考试信息(non-Javadoc)
	 * @see com.gxaes.es.dao.QuestionDao#getTestResult(java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> getTestResult(Integer testId) {
		String sql = "SELECT q.*,td.actualAnswer FROM question q\r\n"+
						"INNER JOIN testdetails td ON q.queId = td.queId\r\n"+
						"INNER JOIN test t ON  t.testId = td.testId\r\n"+
						"INNER JOIN student s ON  s.stuId = t.stuId\r\n"+
						"WHERE t.testId = ? LIMIT 0,10";
		return this.executeQuery(sql,new MapListHandler(), testId);
	}
	/*
	 * 查询指定用户的错题集合(non-Javadoc)
	 * @see com.gxaes.es.dao.QuestionDao#getErrorQuestionList(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Map<String,Object>> getErrorQuestionList(Integer stuId,Integer pageNo,Integer pageSize) {
		String sql = "SELECT q.*,td.actualAnswer,IF(f.queId is null ,0,1)\r\n"+
							"as 'isfavor' FROM question q\r\n"+
							"INNER JOIN testdetails td ON q.queId = td.queId\r\n"+
							"INNER JOIN test t ON td.testId = t.testId\r\n"+
							"INNER JOIN student s ON t.stuId = s.stuId\r\n"+
							"LEFT JOIN favorite f on f.queId = q.queId\r\n" +
							"WHERE td.correct=0 AND t.stuId = ? LIMIT ?,?";
		return this.executeQuery(sql, new MapListHandler(), stuId,pageNo*pageSize-pageSize,pageSize);
	}
	/*
	 * 查询指定用户错题数(non-Javadoc)
	 * @see com.gxaes.es.dao.QuestionDao#getErrorCount(java.lang.Integer)
	 */
	@Override
	public int getErrorCount(Integer stuId) {
		String sql = "SELECT COUNT(0) FROM question q\r\n"+
				"INNER JOIN testdetails td ON q.queId = td.queId\r\n"+
				"INNER JOIN test t ON td.testId = t.testId\r\n"+
				"INNER JOIN student s ON t.stuId = s.stuId\r\n"+
				"WHERE td.correct=0 AND t.stuId = ?";
		return this.executeQuery(sql,new ScalarHandler<Long>(), stuId).intValue();
	}
	/*
	 * 添加一次收藏(non-Javadoc)
	 * @see com.gxaes.es.dao.QuestionDao#saveFavorite(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void saveFavorite(Integer stuId, Integer queId) {
		String sql = "INSERT into favorite(stuId,queId)  value(?,?);";
		this.executeUpdate(sql, stuId,queId);
	}
	/*
	 * 得到收藏的题目
	 */
	public List<Question> getFavoriteQuestionList(Integer stuId,Integer pageNo,Integer pageSize) {
		String sql = "SELECT * FROM question WHERE queId in\r\n"+
					 "(select queId from favorite where stuId = ?) LIMIT ?,?";
		return this.executeQuery(sql, new BeanListHandler<Question>(Question.class), stuId,pageNo*pageSize-pageSize,pageSize);
	}
	/*
	 * 得到收藏题目数量
	 */
	public int getFavoriteCount(Integer stuId) {
		String sql = "SELECT COUNT(0) FROM favorite\r\n"+
				"WHERE stuId = ?";
		return this.executeQuery(sql,new ScalarHandler<Long>(), stuId).intValue();
	}
}
