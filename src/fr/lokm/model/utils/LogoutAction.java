package fr.lokm.model.utils;

import javax.servlet.http.HttpServletRequest;



public class LogoutAction extends Action {
	@Override
	public boolean executeAction(HttpServletRequest request)
	{
		request.getSession().setAttribute("user", null);
		return true;
	}
}
