package fr.lokm.model.utils;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import fr.lokm.model.beans.Author;
import fr.lokm.model.beans.Book;

public class AddBook extends Action {
	@Override
	public boolean executeAction(HttpServletRequest request) {
		
		Book book = new Book(
				request.getParameter("title"),
				request.getParameter("overview"),
				Float.parseFloat(request.getParameter("price")),
				Boolean.valueOf(request.getParameter("availability")),
				new ArrayList<Author> ()
				);

		String[] authorSend = request.getParameterValues("authors");
		
		EntityManager em = JpaUtil.getEntityManager();
		
		for (String a : authorSend) {		
			String[] authorSplit = a.split(" ");
			Author author = em.find(Author.class, Integer.parseInt(authorSplit[0]));
			System.out.println("author : " + author.getId() + " " + author.getFirstname() + " " + author.getLastname() + " " + author.getNative_country());
			book.addAuthor(author);
		}
		
		System.out.println("book "+ book.getTitle() + " " + book.getOverview() + " " + book.getPrice() + " " + book.isAvailability());
		System.out.println("book.author : "+ book.getAuthors().get(0).getId() + " " + book.getAuthors().get(0).getFirstname() + " " + book.getAuthors().get(0).getLastname() + " " + book.getAuthors().get(0).getNative_country());
		
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
	
			em.persist(book);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
		em.close();
	return true;
	}
}
