package fr.lokm.model.utils;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import fr.lokm.model.beans.User;



public class SigninAction extends Action
{
	@Override
	public boolean executeAction(HttpServletRequest request)
	{
		request.setAttribute("title", "Page d'inscription");
		boolean redirect = false;
		if(request.getMethod().equals("POST"))
		{
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String pseudo = request.getParameter("pseudo");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			
			if(firstname != null && lastname != null && pseudo != null && password != null && email != null &&
					!firstname.isEmpty() && !lastname.isEmpty() && !pseudo.isEmpty() && !password.isEmpty())
			{
				User user = new User(pseudo, password, firstname, lastname, email, "user");
				
				EntityManager em = JpaUtil.getEntityManager();
				
				try
				{
					em.getTransaction().begin();
					em.persist(user);
					em.getTransaction().commit();
					request.getSession().setAttribute("user", user);
					
					redirect = true;
				}catch(Exception e){
					em.getTransaction().rollback();
					e.printStackTrace();
				}
				finally
				{					
					em.close();
				}
				
			}
		}
		
		return redirect;
	}

}
