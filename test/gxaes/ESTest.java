package gxaes;

import java.util.Date;


import org.junit.Test;

import com.gxaes.es.dao.TestDao;
import com.gxaes.es.dao.TestDaoImpl;


public class ESTest {
	
	/*
	 * 单元测试
	 * 
	 */
	
	@Test
	public void test() throws  Exception{
		//StudentServiceImpl ss = new StudentServiceImpl();
		//ss.createTest("fengtao");
		//Connection c1 = BaseDao.getConnection();
		//Connection c1 = BaseDao.getConnection();
		//Connection c1 = BaseDao.getConnection();
		TestDao testDaoImpl = new TestDaoImpl();
		testDaoImpl.update1(2,new Date(),0.8,8);
		//Question q = ss.loadQuestion("chenlumei", 3, 1);
		//System.out.println(q.getQueId());
		
	}

}
