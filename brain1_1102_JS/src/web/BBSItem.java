package web;
import java.sql.*;
import javax.servlet.*;
public class BBSItem {
	private int seqNo;
	private String title;
	private String content;
	private Date date;
	
	public BBSItem(){
		
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setSeqNo(int seqNo){
		this.seqNo = seqNo;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public int getSeqNo(){
		return seqNo;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getContent(){
		return content;
	}
	
	
	public void readDB() throws ServletException {
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "root", "student");
			if(conn==null)
				throw new Exception("데이터베이스에 연결 ㄴㄴ");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from bbs where seqNo = '" + seqNo + "';");
			if(rs.next()){
				title = rs.getString("title");
				content = rs.getString("content");
				date = rs.getDate("date");
			}
		}catch(Exception e) {
			throw new ServletException(e);
		}finally{
			try{
				stmt.close();
			}catch(Exception ignored){}
			try{
				conn.close();
			}catch(Exception ignored){}
		}
	}
}
