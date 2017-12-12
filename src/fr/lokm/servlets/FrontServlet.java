package fr.lokm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import fr.lokm.model.dao.DAOFactory;



 
@WebServlet(
		name="FrontServlet",
		value= {"/home"}
		)
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HOME = "/WEB-INF/index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		Object books = daoFactory.getBookDAO().findAll();
//		Author author = (Author) daoFactory.getAuthorDAO().findAll();
		
		request.setAttribute("books", books);
//		request.setAttribute("author", author);
		
		getServletContext().getRequestDispatcher(PAGE_HOME).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
