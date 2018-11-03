<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bbsItem" class="web.BBSItem"/>
<jsp:setProperty property="seqNo" name="bbsItem" value="${param.SEQ_NO}"/>
<% bbsItem.readDB(); %>
<%@ page import="java.sql.*"%>
<%@ page import="javax.servlet.http.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String strSeqNo = request.getParameter("SEQ_NO");
	int seqNo = Integer.parseInt(strSeqNo);

	Connection conn = null;
	Statement stmt = null;
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "root", "student");
		if(conn == null)
			throw new Exception("데이터베이스에 연결할 수 없습니다.");
		stmt = conn.createStatement();
		String command = String.format("delete from bbs where seqno=%s", seqNo);
		int rowNum = stmt.executeUpdate(command);
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
%>
<script>
	location.href="bbs-list";
</script>