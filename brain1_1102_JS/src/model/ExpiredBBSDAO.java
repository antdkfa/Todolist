package model;

import java.sql.*;
import java.util.ArrayList;

public class ExpiredBBSDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private void getConnection() throws Exception{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "root", "student");
		if(conn==null)
			throw new Exception("데이터베이스에 연결할 수 없습니다.");
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs)throws SQLException{
		if(rs!=null) rs.close();
		if(stmt!=null) stmt.close();
		if(conn!=null) conn.close();
	}
	
	public int insert(ExpiredBBSDTO expiredbbsdto){
		try{
			String result=String.valueOf(getNext());
	         getConnection();
	         if(result=="-1") return -1;
			String query = "insert into expiredbbs (seqno, title, content, date) values ('%s', '%s', '%s', '%s');";
			String command = String.format(query, result, expiredbbsdto.getTitle(), expiredbbsdto.getContent(), expiredbbsdto.getDate());
			stmt = conn.createStatement();
			stmt.executeUpdate(command);
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try{
				close(conn,stmt,rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public ExpiredBBSDTO load(int seqNo) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		ExpiredBBSDTO expiredbbsdto = new ExpiredBBSDTO();
		String query = "select * from expiredbbs where seqno = " + seqNo + ";";
		this.rs=stmt.executeQuery(query);
		if(rs.next()){
			expiredbbsdto.setSeqNo(rs.getInt("seqno"));
			expiredbbsdto.setTitle(rs.getString("title"));
			expiredbbsdto.setContent(rs.getString("content"));
			expiredbbsdto.setDate(rs.getDate("date"));
		}
		close(this.conn, this.stmt, this.rs);
		return expiredbbsdto;
	}
	
	public int getNext(){
		try{
			getConnection();
			this.stmt = conn.createStatement();
			String query = "select seqno from expiredbbs order by seqno desc;";
			String command = String.format(query);
			rs=stmt.executeQuery(command);
			if(rs.next()){
				return rs.getInt(1)+1;
			}
			return 1;
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				close(conn,stmt, rs);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public ArrayList<ExpiredBBSDTO> loadList (int pageNumber){
		ArrayList<ExpiredBBSDTO> arr = new ArrayList<ExpiredBBSDTO>();
		try{
			String query = "select * from expiredbbs where seqno<'%s' order by seqno desc limit 10;";
			int result = getNext()-(pageNumber-1)*10;
			String command = String.format(query, Integer.toString(result));
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(command);
			while(rs.next()){
				ExpiredBBSDTO expiredbbs = new ExpiredBBSDTO();
				expiredbbs.setSeqNo(rs.getInt("seqno"));
				expiredbbs.setTitle(rs.getString("title"));
				expiredbbs.setContent(rs.getString("content"));
				expiredbbs.setDate(rs.getDate("date"));
				arr.add(expiredbbs);
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
	
	public int delete(int seqno){
		try{
			getConnection();
			this.stmt = conn.createStatement();
			String query = "delete from expiredbbs where seqno = '%s'";
			String command = String.format(query, seqno);
			int result = stmt.executeUpdate(command);
			if(result == 0) return 0;
			String query2 = "alter table expiredbbs auto_increment=1;";
			stmt.executeUpdate(query2);
			String query3 = "set @cnt=0;";
			stmt.executeUpdate(query3);
			String query4 = "update expiredbbs set seqno=@cnt:=@cnt+1;";
			String command2 = String.format(query4);
			stmt.executeUpdate(command2);
			if(result==1) return 1;
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				close(conn, stmt, rs);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public boolean nextPage(int pageNumber){
		try{
			String query= "select * from expiredbbs where seqno<'%d' order by seqno desc limit 10;";
			int result = getNext() - (pageNumber-1)*10;
			String command = String.format(query, result);
			getConnection();
			this.stmt=conn.createStatement();
			this.rs= stmt.executeQuery(command);
			if(rs.next()){
				return true;
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				close(conn, stmt, rs);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}
}
