package com.gxaes.es.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.gxaes.es.entity.Student;

public class StudentDaoImpl extends BaseDao implements StudentDao {

	@Override
	public Student findByLoginName(String loginName) {
		// TODO Auto-generated method stub
		String sql = "select * from student where loginName = ?;";
		BeanHandler<Student> bh = new BeanHandler<Student>(Student.class);
		return this.executeQuery(sql, bh, loginName);
		//Connection conn = null ;
		
		/*PreparedStatement stmt = null ;
		ResultSet rs = null ;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, loginName);
			rs = stmt.executeQuery();
			if(rs.next()){
				Student student = new Student();
				student.setEmail(rs.getString("email"));
				student.setStuId(rs.getInt("stuId"));
				student.setMobile(rs.getString("mobile"));
				student.setPwd(rs.getString("pwd"));
				student.setRegisterTime(rs.getDate("registerTime"));
				return student;
			}
			
		}catch(Exception e ){
			throw new RuntimeException(e);
		}finally{
			closeRT(rs, stmt);
			if(noTransaction()){
				closeConnection();
			}
		}
		return null ;*/
	}

	@Override
	public void save(Student stu) {
		// TODO Auto-generated method stub
		String sql = "insert into student (loginName,pwd,email,mobile,registerTime) value(?,?,?,?,?)";
		this.executeUpdate(sql, stu.getLoginName(),stu.getPwd(),stu.getEmail(),
				stu.getMobile(),stu.getRegisterTime());
		/*
		Connection conn = null ;
		PreparedStatement stmt = null ;
		ResultSet rs = null ;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,stu.getLoginName());
			stmt.setString(2,stu.getPwd());
			stmt.setString(3, stu.getEmail());
			stmt.setString(4, stu.getMobile());
			stmt.setDate(5,new java.sql.Date(stu.getRegisterTime().getTime()));
			stmt.executeUpdate();
			
		}catch(Exception e ){
			throw new RuntimeException(e);
		}finally{
			closeRT(rs, stmt);
			if(noTransaction()){
				closeConnection();
			}
		} */
	}
}
