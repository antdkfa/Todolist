package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class FreeWriteServlet
 */
public class FreeWriteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String date = request.getParameter("date");
		int rowNum;
		Connection conn = null;
		Statement stmt = null;
		try{
			if(title.isEmpty() || content.isEmpty())
				throw new Exception("데이터를 입력하세요.");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb", "root", "student");
			if(conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			String command1 = String.format("insert into bbs" + 
				"(title, content, date) values ('%s', '%s', '%s');",title, content, date);
			String command2 = String.format("insert into bbs" + 
					"(title, content, date) values ('%s', '%s', %s);",title, content, null);
			if (date.equals(""))
				rowNum = stmt.executeUpdate(command2);
			else 
				rowNum = stmt.executeUpdate(command1);
			if(rowNum < 1)
				throw new Exception("데이터를 DB에 입력할 수 없습니다.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		response.sendRedirect("bbs-list");
	}
}
