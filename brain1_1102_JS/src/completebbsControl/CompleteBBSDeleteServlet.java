package completebbsControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class CompleteBBSDeleteServlet
 */
public class CompleteBBSDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      int seqNo = Integer.parseInt(request.getParameter("seqNo"));
	      
	      CompleteBBSDAO completeBBSDAO = null;
	      try {
	    	  completeBBSDAO = new CompleteBBSDAO();
	      } catch (Exception e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	      }
	      
	      CompleteBBSDTO completeBBSInfo = new CompleteBBSDTO();

	      try {
	    	  completeBBSInfo = completeBBSDAO.read(seqNo);
	      } catch (Exception e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	      }
	      
	      completeBBSDAO.delete(completeBBSInfo.getSeqNo());
	      response.sendRedirect("./completeBBSView");

	}

}
