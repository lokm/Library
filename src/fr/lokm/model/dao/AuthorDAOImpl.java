package fr.lokm.model.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import fr.lokm.model.beans.Author;


public class AuthorDAOImpl implements InterfaceDAO<Author> {
	private DAOFactory daoFactory;
	private final String COLUMN_ID = "id";
	private final String COLUMN_FIRSTNAME = "firstname";
	private final String COLUMN_LASTNAME = "lastname";
	private final String COLUMN_NATIVE_COUNTRY = "native_country";
	
	private Connection cnx;
	private Statement statement;
	
	public AuthorDAOImpl() {
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
	public Author find(int id) {
		Author author = new Author();
		executeQuery("SELECT * FROM author WHERE id="+id);
		try {
			ResultSet result = statement.getResultSet();
			
			if (result.next()) {
				author.setId(result.getInt(COLUMN_ID));
				author.setFirstname(result.getString(COLUMN_FIRSTNAME));
				author.setLastname(result.getString(COLUMN_LASTNAME));
				author.setNative_country(result.getString(COLUMN_NATIVE_COUNTRY));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoFactory.closeConnection(cnx);
		}
		return author;
	}

	@Override
	public void delete(int id) {
		executeQuery("DELETE FROM author WHERE id="+id);
		daoFactory.closeConnection(cnx);
	}

	@Override
	public void update(Author obj) {
		executeQuery("UPDATE author SET " 
				+ " firstname = " + obj.getFirstname()
				+ " lastname = " + obj.getLastname()
				+ " native_country = " + obj.getNative_country()
				+ " WHERE id = " + obj.getId());
		daoFactory.closeConnection(cnx);
	}

	@Override
	public void create(Author obj) {
		executeQuery("INSERT INTO author (firstname, lastname, native_country) VALUES"
				+ "("
				+ obj.getFirstname() + ","
				+ obj.getLastname() + ","
				+ obj.getNative_country() + ","
				+ ")"
				+ " WHERE id=" + obj.getId());
				daoFactory.closeConnection(cnx);
	}

	@Override
	public List<Author> findAll() {
		List<Author> authors = new ArrayList<Author>();
		executeQuery("SELECT * FROM author");
		try {
			ResultSet result = statement.getResultSet();
			
			while (result.next()) {
				authors.add(
						new Author(
								result.getInt(COLUMN_ID),
								result.getString(COLUMN_FIRSTNAME),
								result.getString(COLUMN_LASTNAME),
								result.getString(COLUMN_NATIVE_COUNTRY)
								)
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoFactory.closeConnection(cnx);
		}
		return authors;
	}

}
