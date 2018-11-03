package model;

import web.*;
import java.sql.*;
import java.util.ArrayList;
public class BBSItemDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private void getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "root", "student");
		if(conn==null)
			throw new Exception("데이터베이스 연ㄹ결할 수 없습니다.");
	}
	
	private void close(Connection conn, Statement stmt,ResultSet rs) throws SQLException{
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}
	
	public BBSItem read(int seqNo) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		BBSItem bbsitem = new BBSItem();
		String query = "select  * from bbs where seqno = " + seqNo + ";";
		this.rs = stmt.executeQuery(query);
		
		if(rs.next()){
			bbsitem.setSeqNo(rs.getInt("seqNo"));
			bbsitem.setTitle(rs.getString("title"));
			bbsitem.setContent(rs.getString("content"));
			bbsitem.setDate(rs.getDate("date"));
		}
		close(this.conn,this.stmt,this.rs);
		return bbsitem;
	}
	
	public int delete(int seqno) {
		try{
			getConnection();
			this.stmt = conn.createStatement();
			String query = "delete from bbs where seqno = '%s'";
			String command = String.format(query, seqno);
			int result = stmt.executeUpdate(command);
			if(result == 0) return 0;
			String query2 = "alter table bbs auto_increment=1";
			stmt.executeUpdate(query2);
			String query3 = "set @cnt=0;";
			stmt.executeUpdate(query3);
			String query4 = "update bbs set seqno=@cnt:=@cnt+1";
			String command2 = String.format(query4);
			stmt.executeUpdate(command2);
			if(result==1) return 1;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{
				close(conn,stmt,rs);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public ArrayList<BBSItem> loadList (){
		ArrayList<BBSItem> arr = new ArrayList<BBSItem>();
		try{
			String query = "select * from bbs;";
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(query);
			while(rs.next()){
				BBSItem bbsInfo = new BBSItem();
				bbsInfo.setSeqNo(rs.getInt("seqno"));
				bbsInfo.setTitle(rs.getString("title"));
				bbsInfo.setContent(rs.getString("content"));
				bbsInfo.setDate(rs.getDate("date"));
				arr.add(bbsInfo);
			};
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				close(conn,stmt,rs);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return arr;
	}
}
