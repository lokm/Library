package fr.lokm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lokm.model.utils.ActionManager;

@WebServlet(
		name="FrontServlet",
		value= { "/books", "/add","/addauthor", "/delete", "/edit", "/signin", "/logout", "/login"}
		)
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HOME = "/WEB-INF/index.jsp";
	public static final String ACTION_ADD = "add";
	public static final String ACTION_ADDAUTHOR = "addauthor";
	public static final String ACTION_DELETE = "delete";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_LIST_BOOKS = "books";
	public static final String ACTION_SIGNIN = "signin";
	public static final String ACTION_LOGOUT = "logout";
	public static final String ACTION_LOGIN = "login";

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionName = getActionName(request);
//		System.out.println(actionName);
		Boolean redirect = ActionManager.getAction(actionName).executeAction(request);
		if(redirect) {
//            System.out.println("actioname = " + actionName);        
            response.sendRedirect(request.getContextPath()+"/books");
        } else {
//            System.out.println(redirect);    
            request.setAttribute("actionname", actionName);
            getServletContext().getRequestDispatcher(PAGE_HOME).forward(request, response);
        }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		String actionName = getActionName(request);
		boolean redirect = ActionManager.getAction(actionName).executeAction(request);
	
		
		request.setAttribute("actionname", actionName);
		if(redirect) { 
			response.sendRedirect(request.getContextPath()+"/books");
		} else {
			
			response.sendRedirect(request.getContextPath()+"/"+actionName);
			
		}
	

	}
		
		
		
	
	private String getActionName(HttpServletRequest req) {
        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/")+1); // on decoupe uri en 2, lindex de depart le dernier / +1 pour effacer le /
        return uri;

    }

}
