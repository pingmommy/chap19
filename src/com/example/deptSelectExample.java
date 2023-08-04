package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
  
 **********  sql 과 jdbc 데이터베이스입출력   *************


개발자는 언어과 데이터베이스를 연동하는 방법을 알아야 한다. 
java와 db 연동하려면 jdbc를 이용해야 한다.


jdbc 라이브러리 (java database connection)
 
- 데이터베이스와 연결해서 데이터입출력 작업을 할 수 있도록 자바에서 제공하는 라이브러리
- jdbc라이브러리는 클래스와 인터페이스로만 구성되어 있다. (바디가 없음.) 
- 표준적인 db연동 라이브러리라서 데이터베이스 관리시스템의 종류와 상관없이 사용할 수 있다. 

jdbc driver

각 dbms마다 데이터를 관리하고 구조를 구현하는 방식이 다르므로 dbms에 맞는 driver를 따로 다운받아 사용 


데이터베이스 연결 

리모트에서 접근할 때는 

리모트 ip + 오라클 port 1521 + sid(xe) 

host는 jdbc가 기본적으로 설치되어 있다(윈도우즈) 


db 연결
수행문 작성 >> connection conn = DriverManager.getConnection(); 
  
   */



/*
    <<자바프로젝트에서 jdbc 드라이버 설치하는 법 >>
 
 자바프로젝트 생성 - 빌드패스 - 컨피규자바 빌드패스 - 라이브러리 - ojdbc8자르 파일 추가 
 
 */


/**
 
 어제 이클립스로 sql 데이터베이스를 불러오면서 jdbc 드라이버로 연동함. 
(데이터베이스에 연동한 것임.)

오늘은 자바프로젝트에서 디비를 활용하기 위해 jdbc 드라이버를 연동함. 

연동한 것을 드라이버매니저를 통해서 접근함. 

디비에 접근하기 위해 url과 유저 비밀번호가 필요하기 때문에

드라이버 매니저를 이를 요구하고 결과값을 connection타입의 변수에 반환한다. 

이 때 url은 jdbc드라이버와 orcle이 설치된 서버 주소이다. 
  


rs에 담긴 데이터를 읽어야 하는데, 이 때 next()메소드를 쓴다. 

next()를 쓰는 이유는 처음 rs 포인터가 가리키는 곳은 데이터의 첫 row가 
아니라 그 위이다. 

데이터의 첫 row를  읽으려면 next()를 써야 하고 

해당하는 데이터가 있으면 true를 반환하고 데이터가 없으면 false를 반환한다.

true와 함께 반환된 데이터는 rs에 담기고  

이를 get'타입'() 메소드를 써서 데이터의 타입에 맞는 변수에 담는다. 

콘솔창에 출력할 때는 print()메소드를 쓴다.  


*/







public class deptSelectExample {

	public static void main(String[] args) throws SQLException {

		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		Connection conn = DriverManager.getConnection(url,user,password);
		
		// 커넥션을 얻은 다음에는 스테이트먼드로 받아야 한다. 
		Statement stmt = conn.createStatement();
		
		// 서버로 statement를 보냄. 
		// oracle이 받아서 쿼리문을 수행함. 
		//쿼리문이 정상이라면 결과를 반환하는데 이 결과는 테이블에 있는 데이터이다. 
		// 데이터는 Resultset으로 받아야 한다. 그래서 rs는 "select * from dept" 의 결과를 담고 있다. 
		ResultSet rs = stmt.executeQuery("select * from dept");   

		{
		rs.next();
		int deptno = rs.getInt("deptno");
		String dname = rs.getString("dname");
		String loc = rs. getString("loc");
		System.out.printf("[%2d %s %s]\n",deptno,dname,loc);
		}
		
		{
		rs.next();
		int deptno = rs.getInt("deptno");
		String dname = rs.getString("dname");
		String loc = rs. getString("loc");
		System.out.printf("[%2d %s %s]\n",deptno,dname,loc);
		}
		
		{
			rs.next();
			int deptno = rs.getInt("deptno");
			String dname = rs.getString("dname");
			String loc = rs. getString("loc");
			System.out.printf("[%2d %s %s]\n",deptno,dname,loc);
		}
		
		{
			rs.next();
			int deptno = rs.getInt("deptno");
			String dname = rs.getString("dname");
			String loc = rs. getString("loc");
			System.out.printf("[%2d %s %s]\n",deptno,dname,loc);
		}
		
		
		conn.close();
		System.out.println("end");
		
	}

}
