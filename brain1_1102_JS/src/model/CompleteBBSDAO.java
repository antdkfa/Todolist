package model;
import java.sql.*;
import java.util.ArrayList;
public class CompleteBBSDAO {
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
	
	public int insert(CompleteBBSDTO completebbsdto){
		try{
			String result=String.valueOf(getNext());
	         getConnection();
	         if(result=="-1") return -1;
			if (completebbsdto.getDate() == null){
				String query = "insert into completebbs (seqno, title, content) values ('%s', '%s', '%s');";
				String command2 = String.format(query, result, completebbsdto.getTitle(), completebbsdto.getContent());
				stmt = conn.createStatement();
				stmt.executeUpdate(command2);
			}
			else {
				String query = "insert into completebbs (seqno, title, content, date) values ('%s', '%s', '%s', '%s');";
				String command1 = String.format(query, result, completebbsdto.getTitle(), completebbsdto.getContent(), completebbsdto.getDate());
				stmt = conn.createStatement();
				stmt.executeUpdate(command1);
			}
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
	
	public CompleteBBSDTO read(int seqNo) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		CompleteBBSDTO completebbsdto = new CompleteBBSDTO();
		String query = "select  * from completebbs where seqno = " + seqNo + ";";
		this.rs = stmt.executeQuery(query);
		
		if(rs.next()){
			completebbsdto.setSeqNo(rs.getInt("seqNo"));
			completebbsdto.setTitle(rs.getString("title"));
			completebbsdto.setContent(rs.getString("content"));
			completebbsdto.setDate(rs.getDate("date"));
		}
		close(this.conn,this.stmt,this.rs);
		return completebbsdto;
	}
	
	public int getNext(){
		try{
			getConnection();
			this.stmt = conn.createStatement();
			String query= "select seqno from completebbs order by seqno desc";
			String command = String.format(query);
			rs=stmt.executeQuery(command);
			if(rs.next()){
				return rs.getInt(1) + 1;
			}
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try{
				close(conn,stmt,rs);
				} catch(Exception e) {
					e.printStackTrace();
				}
		}
		return -1;
	}
	
	public ArrayList<CompleteBBSDTO> loadList(int pageNumber){
		ArrayList<CompleteBBSDTO> arr = new ArrayList<CompleteBBSDTO>();
		try{
			String query = "select * from completebbs where seqno <'%s' order by seqno desc limit 10;";
			int result = getNext() - (pageNumber-1) * 10;
			String command = String.format(query, Integer.toString(result));
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(command);
			while(rs.next()){
				CompleteBBSDTO completebbs = new CompleteBBSDTO();
				completebbs.setSeqNo(rs.getInt("seqno"));
				completebbs.setTitle(rs.getString("title"));
				completebbs.setContent(rs.getString("content"));
				completebbs.setDate(rs.getDate("date"));
				arr.add(completebbs);
			};
		} catch(Exception e) {
				e.printStackTrace();
		} finally{
				try{
					close(conn,stmt,rs);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}	
		return arr;
	}
	
	public int delete(int seqno) {
		try{
			getConnection();
			this.stmt = conn.createStatement();
			String query = "delete from completebbs where seqno = '%s'";
			String command = String.format(query, seqno);
			int result = stmt.executeUpdate(command);
			if(result == 0) return 0;
			String query2 = "alter table completebbs auto_increment=1";
			stmt.executeUpdate(query2);
			String query3 = "set @cnt=0;";
			stmt.executeUpdate(query3);
			String query4 = "update completebbs set seqno=@cnt:=@cnt+1";
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
	
	public boolean nextPage(int pageNumber) {
		try{
			String query = "select * from completebbs where seqno <'%d' order by seqno desc limit 10;";
			int result=getNext() - (pageNumber - 1) * 10;
			String command = String.format(query, result);
			getConnection();
			this.stmt = conn.createStatement();
			this.rs=stmt.executeQuery(command);
			if(rs.next()){
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			try{
				close(conn,stmt,rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
