package com.gxaes.es.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.*;

public class BaseDao {
	final static String driver_class = "com.mysql.jdbc.Driver";
	final static String url = "jdbc:mysql://172.18.150.49:3306/ks";
	final static String user = "root";
	final static String password = "admin";
	
	public static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	static {

			try {
				Class.forName(driver_class);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public static Connection getConnection() throws SQLException{
		//
		Connection conn = threadLocal.get();
		if(conn == null || conn.isClosed()){
			conn = DriverManager.getConnection(url,user,password);
			threadLocal.set(conn);
		}
		return conn;
	}
	public void close(ResultSet rs,Statement stmt , Connection conn){
		try {
			if(rs != null)
			rs.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeRT(ResultSet rs,Statement stmt){
		try {
			if(rs != null)
			rs.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeConnection() {
		try{
			getConnection().close();
		}catch(SQLException e){
			throw new RuntimeException(e) ;
		}
		
	}
	public static void beginTransaction() {
		// TODO Auto-generated method stub
		try{
			getConnection().setAutoCommit(false);
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
		
	}
	public static void commit() {
		// TODO Auto-generated method stub
		try{
			getConnection().commit();
		}catch(SQLException e){
			throw new RuntimeException(e) ;
		}
		
	}
	public static void rollback() {
		// TODO Auto-generated method stub
		try{
			getConnection().rollback();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	public static boolean noTransaction() {
		 try{
			 return getConnection().getAutoCommit()==true ;
		 }catch(Exception e){
			 throw new RuntimeException(e) ;
		 }
	}
	public int executeUpdate(String sql , Object... parameters){
		Connection conn = null ;
		//PreparedStatement stmt = null ;
		//ResultSet rs = null ;
		try{
			conn = getConnection();
			//stmt = conn.prepareStatement(sql);
			//if(parameters != null){
			//	for(int i = 0 ; i < parameters.length ; i ++){
			//		stmt.setObject(i+1, parameters[i]);
			//	}
			//}
			QueryRunner query = new QueryRunner();
			return query.update(conn, sql, parameters);
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}finally{
			//closeRT(rs, stmt);
			if(noTransaction()){
				closeConnection();
			}
		} 
	}
	public <T> T executeQuery(String sql,ResultSetHandler<T> handler, Object... parameters){
		Connection conn = null ;
		//PreparedStatement stmt = null ;
		//ResultSet rs = null ;
		try{
			conn = getConnection();
			//stmt = conn.prepareStatement(sql);
			//if(parameters != null){
			//	for(int i = 0 ; i < parameters.length ; i ++){
			//		stmt.setObject(i+1, parameters[i]);
			//	}
			//}
			QueryRunner query = new QueryRunner();
			return query.query(conn, sql, handler,parameters);
			
			//rs = stmt.executeQuery();
			//return handler.handle(rs);
		}catch(SQLException e ){
			throw new RuntimeException(e);
		}finally{
			//closeRT(rs, stmt);
			if(noTransaction()){
				closeConnection();
			}
		} 
	}
	
}
