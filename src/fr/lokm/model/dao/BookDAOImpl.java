package fr.lokm.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.lokm.model.beans.Book;

public class BookDAOImpl implements BookDAO {
	private final String COLUMN_ID = "id";
	private final String COLUMN_TITLE = "title";
	private final String COLUMN_AVAILABILITY = "availability";
	private final String COLUMN_PRICE = "price";
	private final String COLUMN_OVERVIEW = "overview";
	
	@Override
	public Book find(int id) {
		Book book = new Book();
		Connection cnx = null;
		String query = "SELECT * FROM Book WHERE id="+id;
		try {
			cnx = DAOFactory.getConnection();
			Statement st = cnx.createStatement();
			st.execute(query);
			ResultSet result = st.getResultSet();
			
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
			DAOFactory.closeConnection();
		}
		return book;
	}

	@Override
	public void delete(int id) {
	}

	@Override
	public void update(Book book) {
	}

	@Override
	public void create(Book book) {
	}

	@Override
	public List<Book> findAll() {
		return null;
	}

}
