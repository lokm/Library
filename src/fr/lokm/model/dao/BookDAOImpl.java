package fr.lokm.model.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import fr.lokm.model.beans.Author;
import fr.lokm.model.beans.Book;

public class BookDAOImpl implements InterfaceDAO<Book> {
	private DAOFactory daoFactory;
	private final String COLUMN_ID = "id";
	private final String COLUMN_TITLE = "title";
	private final String COLUMN_AVAILABILITY = "availability";
	private final String COLUMN_PRICE = "price";
	private final String COLUMN_OVERVIEW = "overview";
	private final String COLUMN_FIRSTNAME = "firstname";
	private final String COLUMN_LASTNAME = "lastname";
	private final String COLUMN_NATIVE_COUNTRY = "native_country";
	
	private Connection cnx;
	private Statement statement;
	
	public BookDAOImpl() {
		this.daoFactory = DAOFactory.getInstance();
	}
	
	private void executeQuery(String query) {
		cnx = daoFactory.getConnection();
		try {
			statement = cnx.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public Book find(int id) {
		Book book = new Book();
		executeQuery("SELECT * FROM book WHERE id="+id);
		try {
			ResultSet result = statement.getResultSet();
			if (result.next()) {
				book.setId(result.getInt(COLUMN_ID));
				book.setTitle(result.getString(COLUMN_TITLE));
				book.setAvailability(result.getBoolean(COLUMN_AVAILABILITY));
				book.setPrice(result.getFloat(COLUMN_PRICE));
				book.setOverview(result.getString(COLUMN_OVERVIEW));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoFactory.closeConnection(cnx);
		}
		return book;
	}

	@Override
	public void delete(int id) {
		executeQuery("DELETE FROM book WHERE id="+id);
		daoFactory.closeConnection(cnx);
	}

	@Override
	public void update(Book obj) {
		executeQuery("UPDATE book SET " 
						+ " title = " + obj.getTitle()
						+ " overview = " + obj.getOverview()
						+ " price = " + obj.getPrice()
						+ " availability = " + obj.isAvailability()
						+ " WHERE id = " + obj.getId());
		daoFactory.closeConnection(cnx);
	}

	@Override
	public void create(Book obj) {
		executeQuery("INSERT INTO book (title, overview, price, availability) VALUES"
						+ "("
						+ obj.getTitle() + ","
						+ obj.getOverview() + ","
						+ obj.getPrice() + ","
						+ obj.isAvailability() + ","
						+ ")"
						+ " WHERE id=" + obj.getId());
		daoFactory.closeConnection(cnx);
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = new ArrayList<Book>();
		executeQuery("SELECT * FROM book JOIN authors_books ab ON ab.book_id=book.id JOIN author a ON ab.author_id=a.id;");
		try {
			ResultSet result = statement.getResultSet();
			Author author = daoFactory.getAuthorDAO().find(result.getInt("author_id"));
			while (result.next()) {
				books.add(
						new Book(
								result.getInt(COLUMN_ID),
								result.getString(COLUMN_TITLE),
								result.getString(COLUMN_OVERVIEW),
								result.getFloat(COLUMN_PRICE),
								result.getBoolean(COLUMN_AVAILABILITY),
								new HashSet<Author>(
										author.getId(),
										author.getFirstname(),
										author.getLastname(),
										author.getNative_country()
										)
								)
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoFactory.closeConnection(cnx);
		}
		
		return books;
	}

}
