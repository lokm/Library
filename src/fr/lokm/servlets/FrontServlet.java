package fr.lokm.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lokm.model.beans.Author;
import fr.lokm.model.beans.Book;
import fr.lokm.model.utils.ActionManager;
import fr.lokm.model.utils.JpaUtil;

@WebServlet(
		name="FrontServlet",
		value= { "/books", "/books/add", "/books/delete", "/books/edit"}
		)
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HOME = "/WEB-INF/index.jsp";
	
	public static final String ACTION_ADD = "add";
	public static final String ACTION_DELETE = "delete";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_LIST_BOOKS = "books";

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionName = getActionName(request);
		if(!actionName.equals("home")) {
			ActionManager.getAction(actionName).executeAction(request);
		}
		request.setAttribute("actionname", actionName);
		
		EntityManager em = JpaUtil.getEntityManager();
		
		
		List<Book> books = new ArrayList<Book>();
	
//			books = em.createQuery("select b from Book b").getResultList();
			System.out.println("toto : " + em.createQuery("select b from Book b").getResultList());
			
		em.close();
		
		
//		Author author = new Author("tutur","Lordur","France");
//		Book book = new Book("titre du book","resumer du book",5.2f,true, new ArrayList<Author>());
//		
//		author.addBooks(book);
//		book.addAuthor(author);
		
//		try {
//			em.getTransaction().begin();
//			em.persist(author);
//			em.getTransaction().commit();			
//		} catch (RollbackException e) {
//			em.getTransaction().rollback();
//		}
		
		request.setAttribute("books", books);

		getServletContext().getRequestDispatcher(PAGE_HOME).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		if (request.getParameter("firstname") != null) {
			Author author = new Author();
			author.setFirstname(request.getParameter("firstname"));
			author.setLastname(request.getParameter("lastname"));
			author.setNative_country(request.getParameter("native_country"));
			
		
		}
		
		if (request.getParameter("title") != null) {
			Book book = new Book();
			book.setTitle(request.getParameter("title"));
			book.setOverview(request.getParameter("overview"));
			float price = Float.parseFloat(request.getParameter("price"));
			book.setPrice(price);
			Boolean availability = Boolean.valueOf(request.getParameter("availability"));
			book.setAvailability(availability);
			Author authorSolo = new Author();
			String[] authorSend;
			
			authorSend = request.getParameterValues("authors");
			for (String a : authorSend) {		
				String[] authorSplit;
				authorSplit = a.split(" ");
				authorSolo.setFirstname(authorSplit[0]);
				System.out.println(authorSolo.getFirstname());
				authorSolo.setLastname(authorSplit[1]);
				System.out.println(authorSolo.getLastname());
				authorSolo.setNative_country("France");
				book.addAuthor(authorSolo);
			}
			
			
			

		}
		
		
		response.sendRedirect(request.getContextPath()+"/books");
	}
	
	private String getActionName(HttpServletRequest req) {
        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/")+1); // on decoupe uri en 2, lindex de depart le dernier / +1 pour effacer le /
        return uri;

    }

}
