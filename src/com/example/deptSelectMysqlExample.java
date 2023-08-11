package com.example;


// 데이터를 가져와서 출력하는데, 반복문을 활용하여 간단하게 처리할 수 있다. 
// while의 조건문은 rs.next();

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class deptSelectMysqlExample {

	public static void main(String[] args)  {

		Connection conn =null;
		Statement stmt =null;
		ResultSet rs =null;
		
		try {
		String url="jdbc:mysql://localhost:3306/world";
		String user = "root";
		String password = "1234";
		conn = DriverManager.getConnection(url,user,password);
		
		
		stmt = conn.createStatement();
		
		 rs = stmt.executeQuery("select * from dept");   

		
		while(rs.next()) {
		int deptno = rs.getInt("deptno");
		String dname = rs.getString("dname");
		String loc = rs. getString("loc");
		System.out.printf("[%2d %s %s]\n",deptno,dname,loc);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
		}
		

		System.out.println("end");
		
	}

}
