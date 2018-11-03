package expiredbbs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class ExpiredBBSViewServlet
 */
public class ExpiredBBSViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		
		ArrayList<ExpiredBBSDTO> expiredBBSList = new ArrayList<ExpiredBBSDTO>();
		
		try{
			int pageNumber = 1;
			if(request.getParameter("pageNumber")!= null)
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			
			ExpiredBBSDAO expiredBBSDAO = new ExpiredBBSDAO();
			
			expiredBBSList = expiredBBSDAO.loadList(pageNumber);
			
			boolean nextPage = expiredBBSDAO.nextPage(pageNumber+1);
			
			RequestDispatcher dispatcher = null;
			request.setAttribute("expiredBBSList", expiredBBSList);
			request.setAttribute("nextPage", nextPage);
			request.setAttribute("pageNumber", pageNumber);
			dispatcher = context.getRequestDispatcher("/ExpiredBBSView.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
