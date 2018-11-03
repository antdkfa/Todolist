package expiredbbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class ExpiredBBSContentServlet
 */
public class ExpiredBBSContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		String seqNo = request.getParameter("seqNo");

		ExpiredBBSDAO expiredBBSDAO = null;
		try {
			expiredBBSDAO = new ExpiredBBSDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ExpiredBBSDTO expiredBBSDTO = new ExpiredBBSDTO();

		try {
			expiredBBSDTO = expiredBBSDAO.load(Integer.parseInt(seqNo));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			RequestDispatcher dispatcher = null;
			request.setAttribute("expiredBBSDTO", expiredBBSDTO);
			dispatcher = context.getRequestDispatcher("/ExpiredBBSContent.jsp");
			dispatcher.forward(request, response);
	}	
}
