package fr.lokm.model.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import fr.lokm.model.beans.Author;
import fr.lokm.model.beans.Book;

public class ListBooks extends Action {



    @Override
    public boolean executeAction(HttpServletRequest request) {

      
    	EntityManager em = JpaUtil.getEntityManager();
		
		Query q = em.createQuery("SELECT b FROM Book b");
		List<Book> books = q.getResultList();
		Query p = em.createQuery("SELECT a FROM Author a");
		List<Author> authors = p.getResultList();
		
		request.setAttribute("books", books);
		
		request.setAttribute("authors", authors);
		
		return false;

    }
}