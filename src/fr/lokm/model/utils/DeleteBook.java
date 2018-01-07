package fr.lokm.model.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import fr.lokm.model.beans.Book;

public class DeleteBook extends Action {
	@Override
	public boolean executeAction(HttpServletRequest request) {
		
		String idStr = request.getParameter("id");
		
		if (idStr != null) {
			try {
				int id = Integer.parseInt(idStr);
				
				EntityManager em = JpaUtil.getEntityManager();
				EntityTransaction tr = em.getTransaction();
				
				
				try {
					Book book = em.find(Book.class, id);
					tr.begin();
					em.remove(book);
					tr.commit();
				} catch (Exception e) {
					tr.rollback();
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
