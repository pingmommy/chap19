package com.example;


// 데이터를 가져와서 출력하는데, 반복문을 활용하여 간단하게 처리할 수 있다. 
// while의 조건문은 rs.next();

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class deptSelectExample2 {

	public static void main(String[] args) throws SQLException {

		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = DriverManager.getConnection(url,user,password);
		
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("select * from dept");   

		
		while(rs.next()) {
		int deptno = rs.getInt("deptno");
		String dname = rs.getString("dname");
		String loc = rs. getString("loc");
		System.out.printf("[%2d %s %s]\n",deptno,dname,loc);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		System.out.println("end");
		
	}

}
