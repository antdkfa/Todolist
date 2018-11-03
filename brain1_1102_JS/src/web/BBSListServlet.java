package web;

import java.io.*;
import javax.servlet.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.*;

import model.*;

public class BBSListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BBSItemDAO bbsItemDAO = new BBSItemDAO();
		ExpiredBBSDAO expiredBBSDAO = new ExpiredBBSDAO();
		
		boolean flag = false;
		
		ArrayList<BBSItem> bbsList = bbsItemDAO.loadList(); 
		
		for(BBSItem bbsInfo : bbsList){
			if(bbsInfo.getDate() == null)
				continue;
			Date date = new Date();
			java.util.Date sentDate = new java.sql.Date(date.getTime());
			
			Calendar bbsCalDate = Calendar.getInstance();
			bbsCalDate.setTime(bbsInfo.getDate());
			bbsCalDate.set(Calendar.HOUR_OF_DAY, 24);
			
			Date date2 = bbsCalDate.getTime();
			java.sql.Date expiredDate = new java.sql.Date(date2.getTime());
			
			if(sentDate.after(expiredDate)){
				flag = true;
				ExpiredBBSDTO expiredBBSInfo = new ExpiredBBSDTO();
				
				expiredBBSInfo.setDate(bbsInfo.getDate());
				expiredBBSInfo.setTitle(bbsInfo.getTitle());
				expiredBBSInfo.setContent(bbsInfo.getContent());
				
				expiredBBSDAO.insert(expiredBBSInfo);
				
				bbsItemDAO.delete(bbsInfo.getSeqNo());
			}
		}
		
		
		
		String strUpperSeqNo = request.getParameter("LAST_SEQ_NO");
		int upperSeqNo;
		if(strUpperSeqNo == null)
			upperSeqNo = Integer.MAX_VALUE;
		else
			upperSeqNo = Integer.parseInt(strUpperSeqNo);
		web.BBSList list = readDB(upperSeqNo);
		request.setAttribute("BBS_LIST", list);
		request.setAttribute("flag", flag);
		RequestDispatcher dispatcher = request.getRequestDispatcher("FreeBoard.jsp");
		dispatcher.forward(request, response);
	}
	
	private web.BBSList readDB(int upperSeqNo) throws ServletException {
		web.BBSList list = new web.BBSList();
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "root", "student");
			if(conn == null)
				throw new Exception("데이터 베이스 연결 ㄴㄴ");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from bbs where seqNo < " + upperSeqNo + " order by seqno desc; ");
			for(int cnt = 0; cnt < 10; cnt++){
				if(!rs.next())
					break;
				list.setSeqNo(cnt, rs.getInt("seqNo"));
				list.setTitle(cnt, rs.getString("title"));
				list.setDate(cnt, rs.getDate("date"));
			}
			if (!rs.next())
				list.setLastPage(true);
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				stmt.close();
			}catch(Exception ignored){}
			try{
				conn.close();
			}catch (Exception ignored) {}
		}
		return list;
	}
}
