package fr.lokm.model.utils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import fr.lokm.model.beans.User;



public class LoginAction extends Action {

	@Override
	public boolean executeAction(HttpServletRequest request)
	{
		//SecurityConstrainte
		boolean redirect = false;
		if(request.getMethod().equals("POST"))
		{
			String pseudo = request.getParameter("pseudo");
			String password = request.getParameter("password");
			System.out.println(pseudo + " " + password);
			if(pseudo != null && password != null)
			{
				EntityManager em = JpaUtil.getEntityManager();
				
				Query q = em.createQuery("SELECT u FROM User u WHERE pseudo='"+pseudo+"' AND passwd='"+password+"'");
				
				try
				{
					User user = (User) q.getSingleResult();
					request.getSession().setAttribute("user", user);
					redirect = true;
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				finally{
					em.close();
				}
			}
		}
		return redirect;
	}

}
