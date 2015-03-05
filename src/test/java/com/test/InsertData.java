package com.test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class InsertData {
	
	
	
	
	public void ss(){
		Properties properties = new Properties();  
		properties.setProperty("jdbc.driver", "动态设置");  
		properties.setProperty("jdbc.url", "动态设置");  
		properties.setProperty("jdbc.username", "动态设置");  
		properties.setProperty("jdbc.password", "动态设置");  
		  
		//加载mybatis配置文件和映射文件  
		String resource = "mybatis/mybatis-config.xml";  
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();  
		SqlSessionFactory factory = builder.build(reader, properties);  
		SqlSession session = factory.openSession();  
		Connection conn=session.getConnection();
//		conn.createStatement();
//		//获取对象  
//		userDsm = session.getMapper(UserDsm.class);  
//		...  
//		userDsm.addUser(..);  
		
	}
	
	public static Connection getConnection() {
		  try {
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.99.83:3306/interActive?characterEncoding=utf-8&allowMultiQueries=true&autoReconnect=true", "root", "fXL2bO$RQgaRS^lH");
		   return conn;
		  } catch (Exception e) {
		   e.printStackTrace();
		   return null;
		  }
		 }
	public static void main(String[] args) {
		 	String driver = "com.mysql.jdbc.Driver";
	        String passwrod = "fXL2bO$RQgaRS^lH";
	        String userName = "root";
	        String url = "jdbc:mysql://192.168.99.83:3306/interActive?characterEncoding=utf-8&allowMultiQueries=true&autoReconnect=true";
	        String sql = null;;
	        PreparedStatement ps=null;
	        try {
	            Class.forName(driver);
	            Connection conn = DriverManager.getConnection(url, userName,
	                    passwrod);
	             
	            for(int i=5;i<1000;i++){
	            	if(i<10){
	            		sql="INSERT INTO `interActive`.`card_info`(`card_number`,`password`,`status`,`update_time`)VALUES('0000000"+i+"','00000000','0','20170831135001');";
	            	}else if(i<100){
	            		sql="INSERT INTO `interActive`.`card_info`(`card_number`,`password`,`status`,`update_time`)VALUES('000000"+i+"','00000000','0','20170831135001');";
	            	}else if(i<1000){
	            		sql="INSERT INTO `interActive`.`card_info`(`card_number`,`password`,`status`,`update_time`)VALUES('000000"+i+"','00000000','0','20170831135001');";
	            	}
	            	ps = conn.prepareStatement(sql);
	            	ps = conn.prepareStatement(sql);
	 	            ps.executeUpdate();
	            }
	            // 关闭声明
	            if (ps != null) {
	                try {
	                    ps.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	 
	            // 关闭链接对象
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	
	}
	
	
