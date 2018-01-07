package fr.lokm.model.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;

import fr.lokm.model.beans.Author;

public class AddAuthor extends Action {

    @Override
    public boolean executeAction(HttpServletRequest request) {

    	Author author = new Author(
    			request.getParameter("firstname"),
    			request.getParameter("lastname"),
    			request.getParameter("native_country")
  		);
		
    	EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			
			em.persist(author);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
		em.close();
		
		return true;

    }
}
