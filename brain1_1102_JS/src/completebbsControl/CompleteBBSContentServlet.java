package completebbsControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class CompleteBBSContentServlet
 */
public class CompleteBBSContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		int seqNo = Integer.parseInt(request.getParameter("seqNo"));
		
		CompleteBBSDAO completeBBSDAO = null;
		try{
			completeBBSDAO = new CompleteBBSDAO();
		} catch(Exception e1){
			e1.printStackTrace();
		}

		CompleteBBSDTO completeInfo = new CompleteBBSDTO();
		try{
			completeInfo = completeBBSDAO.read(seqNo);
		} catch(Exception e1){
			e1.printStackTrace();
		}
		
		RequestDispatcher dispatcher = null;
		request.setAttribute("completeInfo", completeInfo);
		dispatcher = context.getRequestDispatcher("/CompleteBBSContent.jsp");
		dispatcher.forward(request, response);
	}

}
