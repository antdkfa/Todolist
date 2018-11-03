package completebbsControl;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
public class CompleteBBSViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		
		ArrayList<CompleteBBSDTO> completeBBSList = new ArrayList<CompleteBBSDTO>();
		try{
			int pageNumber = 1;
			if(request.getParameter("pageNumber") != null) 
				pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			
			CompleteBBSDAO completeBBSDAO = new CompleteBBSDAO();
			completeBBSList = completeBBSDAO.loadList(pageNumber);
			boolean nextPage = completeBBSDAO.nextPage(pageNumber + 1);
			request.setAttribute("completeBBSList", completeBBSList);
			request.setAttribute("nextPage", nextPage);
			request.setAttribute("pageNumber", pageNumber);
		}catch(Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = null;
		dispatcher = context.getRequestDispatcher("/CompleteBBS.jsp");
		dispatcher.forward(request,response);
	}
}
