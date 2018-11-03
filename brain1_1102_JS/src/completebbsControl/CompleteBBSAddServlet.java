package completebbsControl;

import web.*;
import model.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteBBSAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			BBSItem bbsitem = new BBSItem();
			
			BBSItemDAO bbsItemDAO = new BBSItemDAO();
			
			CompleteBBSDAO completeDAO = new CompleteBBSDAO();
			
			CompleteBBSDTO completeDTO = new CompleteBBSDTO();
			

			int seqNo = Integer.parseInt(request.getParameter("seqNo"));
			
			bbsitem = bbsItemDAO.read(seqNo);
			
			completeDTO.setDate(bbsitem.getDate());
			completeDTO.setContent(bbsitem.getContent());
			completeDTO.setTitle(bbsitem.getTitle());
			
			bbsItemDAO.delete(bbsitem.getSeqNo());
			
			completeDAO.insert(completeDTO);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("./completeBBSView");
	}

}
