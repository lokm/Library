package fr.lokm.model.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import fr.lokm.model.beans.Author;
import fr.lokm.model.beans.Book;

public class EditBook extends Action {
	@Override
	public boolean executeAction(HttpServletRequest request) {
		
		if (request.getMethod().equals("GET")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.getSession().setAttribute("bookid", id);
		} else {
			EntityManager em = JpaUtil.getEntityManager();
			EntityTransaction tr = em.getTransaction();
			
			int id = Integer.parseInt(request.getParameter("id"));
			Book book = em.find(Book.class, id);
			
			List<Author> authors = book.getAuthors();
			for (Author author : authors) {
				author.setFirstname(request.getParameter("author-firstname-"+author.getId()));
				author.setLastname(request.getParameter("author-lastname-"+author.getId()));
				author.setNative_country(request.getParameter("author-native_country-"+author.getId()));
			}
			
			book.setTitle(request.getParameter("book-title"));
			book.setOverview(request.getParameter("book-overview"));
			book.setAvailability(Boolean.valueOf(request.getParameter("book-availability")));
			book.setPrice(Float.parseFloat(request.getParameter("book-price")));

			tr.begin();
			em.persist(book);
			tr.commit();

			request.getSession().setAttribute("bookid", -1);
		}
		
		return true;
	}
}
