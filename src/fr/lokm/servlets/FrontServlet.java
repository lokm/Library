package fr.lokm.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.lokm.model.beans.Author;
import fr.lokm.model.beans.Book;
//import fr.lokm.model.utils.Country;

@WebServlet(
		name="FrontServlet",
		value= {"/home"}
		)
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HOME = "/WEB-INF/index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection cnx = null;
		Book book;
		Author author;
		Map<Integer, Book> books = new HashMap<Integer, Book>();
		String query = "SELECT * FROM Book b INNER JOIN Authors_books ab ON ab.book_id = b.id JOIN Author a ON ab.author_id = a.id";
		

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
	
			Statement statement = cnx.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				
				author = new Author(
						result.getInt("author_id"),
						result.getString("firstname"),
						result.getString("lastname"),
						result.getString("native_country")
						);
				if(books.containsKey(result.getInt("book_id"))) {
					books.get(result.getInt("book_id")).addAuthor(author);
					
				} else {
					
					book = new Book();
					book.setId(result.getInt("book_id"));
					book.setTitle(result.getString("title"));
					book.setOverview(result.getString("overview"));
					book.setPrice(result.getInt("price"));
					book.setAvailability(result.getBoolean("availability"));
					book.addAuthor(author);
					books.put(book.getId(), book);
				}
			}
			
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != cnx && !cnx.isClosed()) {
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		getServletContext().setAttribute("books", books.values());
		getServletContext().getRequestDispatcher(PAGE_HOME).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
