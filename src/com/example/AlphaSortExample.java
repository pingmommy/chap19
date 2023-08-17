package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Alpha;
import util.Color;
import util.VT100;

/*
 * 작성자 : 조아라
 * 작성일 : 2023/08/17
 * 과목  : 데이터베이스
 */
public class AlphaSortExample {

	public static void main(String[] args) throws SQLException, InterruptedException {
		
		
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="scott";
		String password="tiger";
		
		Connection conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false); // 수동으로 커밋제어. (default가 ture)
		
		
		Statement stmt = conn.createStatement();				
	    stmt.executeUpdate("Delete from alpha"); // where절이 없으면 모든 row를 삭제한다.
	    conn.commit();
		
		var pstmt1 = conn.prepareStatement("""
				
				insert into alpha 
				(line,col,fg,bg,ch)
				values
				(?,?,?,?,?)
				
				""");
	    
		
		var pstmt2 = conn.prepareStatement("""
				select count(*) 
				from alpha
				where line =?
				  and  col =?
				"""); 
		
		var pstmt3 = conn.prepareStatement("""
				update Alpha
				   set fg=?, 
				       bg=?,
				       ch=?
			   where line=? 
			      and col=? 
				""");
		
		
		var insertCount = 0;
		var updateCount = 0;
	    
		VT100.clearScreen();
		
	    for(;;) {
	    	
	    	var alpha = new Alpha();
	    	
	    	pstmt2.setInt(1, alpha.getLine());
	    	pstmt2.setInt(2, alpha.getColumn());
	    	var rs = pstmt2.executeQuery();
	    	    rs.next();
	    	
	        var cnt =rs.getInt(1);	
	    	
	    	if(cnt==0) {
	    	   pstmt1.setInt(1, alpha.getLine());
	    	   pstmt1.setInt(2, alpha.getColumn());
	    	   pstmt1.setString(3,alpha.getFg().toString());
	    	   pstmt1.setString(4,alpha.getBg().toString());
	    	   pstmt1.setString(5,alpha.getCh()+"");
	    	   pstmt1.executeUpdate();
	    	
	    	insertCount++;	
	    	
	    	VT100.reset();
	    	VT100.cursorMove(1, 1);
	    	
	    	System.out.printf("insertCount= %4d",insertCount);
	    	
	    	}else {
	    		pstmt3.setString(1,alpha.getFg().toString());
	    		pstmt3.setString(2,alpha.getBg().toString());
	    		pstmt3.setString(3,alpha.getCh()+"");
	    		pstmt3.setInt(4,alpha.getLine());
	    		pstmt3.setInt(5,alpha.getColumn());
	    		pstmt3.executeUpdate();
	    	
	    	
	    		updateCount++;	
		    	VT100.reset();
		    	VT100.cursorMove(2, 1);
		    	System.out.printf("updateCount= %4d",updateCount);
	    		
	    	}
	    	
	    	
	    	
	    	rs= stmt.executeQuery("select count(*) from alpha");
	        rs.next();
	    	cnt = rs.getInt(1);
	    	
	    	if(cnt == 800)
	    		break;
	    	
	    }
	    
	    conn.commit();
	 
	    VT100.reset();
	    
	    var rs = stmt.executeQuery("select * from alpha order by line, col");
	    
	    while(rs.next()) { 
	    
	      VT100.cursorMove(rs.getInt(1)+3-1,rs.getInt(2));
	      VT100.setForeground(Color.valueOf(rs.getString(3)));
	      VT100.setBackground( Color.valueOf(rs.getString(4)));
	      VT100.print(rs.getString(5).charAt(0));
	    
	      Thread.sleep(10);
	   
	    }
	    
        rs = stmt.executeQuery("select * from alpha order by line, col DESC");
	    
	    while(rs.next()) {
	    
	      VT100.cursorMove(rs.getInt(1)+3-1,rs.getInt(2)+42-1);
	      VT100.setForeground(Color.valueOf(rs.getString(3)));
	      VT100.setBackground( Color.valueOf(rs.getString(4)));
	      VT100.print(rs.getString(5).charAt(0));
	    
	      Thread.sleep(10);
	   
	    }
	    
        rs = stmt.executeQuery("select * from alpha order by col, line");
	    
	    while(rs.next()) {
	    
	      VT100.cursorMove(rs.getInt(1)+24-1,rs.getInt(2));
	      VT100.setForeground(Color.valueOf(rs.getString(3)));
	      VT100.setBackground( Color.valueOf(rs.getString(4)));
	      VT100.print(rs.getString(5).charAt(0));
	    
	      Thread.sleep(10);
	   
	    }
	    
        rs = stmt.executeQuery("select * from alpha order by col DESC, line DESC");
	    
	    while(rs.next()) {
	    
	      VT100.cursorMove(rs.getInt(1)+24-1,rs.getInt(2)+42-1);
	      VT100.setForeground(Color.valueOf(rs.getString(3)));
	      VT100.setBackground( Color.valueOf(rs.getString(4)));
	      VT100.print(rs.getString(5).charAt(0));
	    
	      Thread.sleep(10);
	   
	    }
	   
	    
	    VT100.reset();
	    
	    VT100.cursorMove(45, 1);
	    
		System.out.println("조아라 ok");
	}

}
