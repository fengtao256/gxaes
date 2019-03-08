package com.gxaes.es.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.gxaes.es.common.QuestionPage;
import com.gxaes.es.common.SystemConstants;
import com.gxaes.es.dao.BaseDao;
import com.gxaes.es.dao.QuestionDao;
import com.gxaes.es.dao.QuestionDaoImpl;
import com.gxaes.es.dao.StudentDao;
import com.gxaes.es.dao.StudentDaoImpl;
import com.gxaes.es.dao.TestDao;
import com.gxaes.es.dao.TestDaoImpl;
import com.gxaes.es.dao.TestDetailsDao;
import com.gxaes.es.dao.TestDetailsDaoImpl;
import com.gxaes.es.entity.Student;
import com.gxaes.es.entity.Test;
import com.gxaes.es.entity.TestDetails;
import com.gxaes.es.entity.Question;

public class StudentServiceImpl extends BaseDao implements StudentService {

	private StudentDao studentDao = new StudentDaoImpl();
	private TestDao testDao = new TestDaoImpl();
	private QuestionDao questionDao = new QuestionDaoImpl();
	private TestDetailsDao testdetailsDao = new TestDetailsDaoImpl();
	
	@Override
	public boolean register(Student stu) { 
		// 检查stu的loginName是否存在,调用数据访问层
		Student student =studentDao.findByLoginName(stu.getLoginName());
		if(student != null){
			return false;
		}
		//获取注册时间
		stu.setRegisterTime(new java.util.Date());
		//把stu持久化到数据库
		studentDao.save(stu);
		return true;
	}

	@Override
	public boolean login(String loginName, String pwd) {
		//根据用户名在数据库查找
		Student student = studentDao.findByLoginName(loginName);
		
		//对象不为Null,则用户名存在，再比较密码
		if(student != null){
			if(pwd.equals(student.getPwd())){
				return true ;
			}
			
		}
		return false;
	}

	@Override
	public void createTest(String loginName) {
		// TODO Auto-generated methodstub
		try{
			//开启事务
			BaseDao.beginTransaction();
			// 1.向test插入答题记录
			Test test = new Test();
			Student stu = studentDao.findByLoginName(loginName);
			test.setStuId(stu.getStuId());
			test.setQueCount(SystemConstants.EXAM_QUESTION_COUNT);
			test.setBeginTime(new java.util.Date());
			test.setEndTime(null);
			test.setCorrectCount(0);
			test.setAccuracy(0.0);
			testDao.save(test);//顺便查出这次testId号
			// 2.随机从question选出十道题
			List<Question> randomQuestions = questionDao.getRandomQuestion(SystemConstants.EXAM_QUESTION_COUNT);
			// 3.把第二步得到的10题目插入到testdetails
			for (Question question : randomQuestions){
				TestDetails testDetails = new TestDetails();
				testDetails.setTestId(test.getTestId());
				testDetails.setQueId(question.getQueId());
				testDetails.setActualAnswer(null);
				testDetails.setCorrect(0);
				testdetailsDao.save(testDetails);
				
			}	//提交事务
			BaseDao.commit();
		}catch(Exception e){
			//BaseDao.getConnection().setAutoCommit(false);
			BaseDao.rollback();	
			
		}finally{
			BaseDao.closeConnection();
		}
	}

	@Override
	public Question loadQuestion(Integer testId, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		//System.out.println(testId);
		String sql= "SELECT q.* from question q\r\n"+
				"INNER JOIN testdetails td on q.queId = td.queId\r\n"+
				"INNER JOIN test t on td.testId = t.testId\r\n"+
				"where t.testId =? LIMIT ?,?";
		Object[] parameters = new Object[]{
				testId,
				(page-1)*pageSize,
				pageSize
		};
		return this.executeQuery(sql, new BeanHandler<Question>(Question.class), parameters);
	}

	@Override
	public void postActualAnswer(Integer testId, Integer queId, String actualAnswer) {
		//查出这道题，比较答案，testId，queId
		Question q = questionDao.getById(queId);
		Integer correct = 0 ;
		if(q.getCorrectAnswer().equals(actualAnswer)){
			correct = 1 ;
		}
		//保存这道题答题信息
		testdetailsDao.update(testId,queId,actualAnswer,correct);
		
	}
	//根据用户名查找当前test
	@Override
	public Test findTest(String loginName) {
		
		Student st = studentDao.findByLoginName(loginName);
		
		return testDao.findLatestBtStuId(st.getStuId());
	}
	//根据testId，和queId查找当前这一道题目
	@Override
	public TestDetails findTestDetails(Integer testId, Integer queId) {
		// TODO Auto-generated method stub
		return testdetailsDao.findBytestId(testId,queId);
	}
	/*
	 * 结束一次考试
	 * @see com.gxaes.es.service.StudentService#endTest(java.lang.Integer)
	 */
	@Override
	public double endTest(Integer testId) {
		//查出这次对的题目数量correctCount
		Integer correctCount = testdetailsDao.findcorrectCount(testId);
		//计算正确率accuracy
		double accuracy = (correctCount/10.0);
		//得到endTime
		Date endTime = new Date();
		//更新到数据库
		testDao.update1(testId,endTime,accuracy,correctCount);
		//返回正确率
		return accuracy;
	}
	/*
	 * 查询本次答题信息
	 * @see com.gxaes.es.service.StudentService#getresult(java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> getresult(Integer testId) {
		// 通过testID查询到考试明细集合
		List<Map<String, Object>> list =  questionDao.getTestResult(testId);
		return list;
	}
	/*
	 * 查找学生信息(non-Javadoc)
	 * @see com.gxaes.es.service.StudentService#findByLoginName(java.lang.String)
	 */
	@Override
	public Student findByLoginName(String loginName) {
		return studentDao.findByLoginName(loginName);
	}
	/*
	 * 查找某个学生的所有错题(non-Javadoc)
	 * @see com.gxaes.es.service.StudentService#getErrorQuestion(java.lang.Integer)
	 */
	@Override
	public QuestionPage getErrorQuestion(Integer stuId,Integer pageNo,Integer pageSize) {
		QuestionPage qp = new QuestionPage();
		
		//得到错题集合
		@SuppressWarnings("rawtypes")
		List dataOfCurrentPage= questionDao.getErrorQuestionList(stuId,pageNo,pageSize);
		
		//得到错题总数量
		int totalRowCount = questionDao.getErrorCount(stuId);
		
		qp.setDataOfCurrentPage(dataOfCurrentPage);
		qp.setPageNo(pageNo);
		qp.setPageSize(pageSize);
		qp.setTotalRowCount(totalRowCount);
		return qp;
	}
	/*
	 * 保存一次收藏(non-Javadoc)
	 * @see com.gxaes.es.service.StudentService#favoriteQuestion(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void favoriteQuestion(Integer stuId, Integer queId) {	
		questionDao.saveFavorite(stuId,queId);	
	}
	/*
	 * 查出指定学生的收藏题目(non-Javadoc)
	 * @see com.gxaes.es.service.StudentService#getFavorite(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public QuestionPage getFavorite(Integer stuId, Integer pageNo, Integer pageSize) {
		QuestionPage favoriteQP = new QuestionPage();
		favoriteQP.setDataOfCurrentPage(questionDao.getFavoriteQuestionList(stuId, pageNo, pageSize));
		favoriteQP.setTotalRowCount(questionDao.getFavoriteCount(stuId));
		favoriteQP.setPageNo(pageNo);
		favoriteQP.setPageSize(pageSize);
		return favoriteQP;
	}



}
