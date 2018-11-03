<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.servlet.http.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String strSeqNo = request.getParameter("seqNo");
	int seqNo = Integer.parseInt(strSeqNo);
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	if(title.isEmpty() || content.isEmpty())
		throw new Exception("데이터를 입력하세요.");
	Connection conn = null;
	Statement stmt = null;
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "root", "student");
		if(conn == null)
			throw new Exception("데이터베이스에 연결할 수 없습니다.");
		stmt = conn.createStatement();
		String command = String.format("update bbs set title := '%s', content := '%s' where seqno = %s;", title, content, seqNo);
		int rowNum = stmt.executeUpdate(command);
		if(rowNum < 1)
			throw new Exception("데이터를 입력할 수 없습니다.");
	}
	finally{
		try{
			stmt.close();
		}
		catch (Exception ignored){}
		try{
			conn.close();
		}
		catch (Exception ignored){}
	}
	response.sendRedirect("Q&AFree.jsp?SEQ_NO=" + seqNo);
%>