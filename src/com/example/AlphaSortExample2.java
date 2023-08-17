package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Alpha;

/*
 * 
 * 변경이 없는 값을 보낼때는  Statement 
 * 
 * 변경이 있을 때는 prepareStatement
 * 
 * insert into alpha 
				(line,col,fg,bg,ch)
				values
				(?,?,?,?,?)
 * 
 */
public class AlphaSortExample2 {

	public static void main(String[] args) throws SQLException {
		
		
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="scott";
		String password="tiger";
		
		Connection conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false); // 수동으로 커밋제어. (default가 ture)
		
		
		Statement stmt = conn.createStatement();	
		
		stmt.executeUpdate("insert into alpha values(10,10,'red','red','A')");
		conn.commit();
		
//	    stmt.executeUpdate("Delete from alpha"); // where절이 없으면 모든 row를 삭제한다.
//	    conn.commit();
		
		
		System.out.println("ok");
		
	    var rs = stmt.executeQuery("select * from alpha order by line, col");

		
		
		
		
	}
	
	

}
