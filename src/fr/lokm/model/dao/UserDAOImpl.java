package fr.lokm.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;


import fr.lokm.model.beans.User;

public class UserDAOImpl implements InterfaceDAO<User> {
	private DAOFactory daoFactory;
	private final String COLUMN_ID = "id";
	private final String COLUMN_PSEUDO = "pseudo";
	private final String COLUMN_PASSWD = "passwd";
	private final String COLUMN_FIRSTNAME = "firstname";
	private final String COLUMN_LASTNAME = "lastname";
	private final String COLUMN_EMAIL = "email";
	private final String COLUMN_ROLE = "role";
	
	private Connection cnx;
	private Statement statement;
	
	public UserDAOImpl() {
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
	public User find(int id) {
		User user = new User();
		executeQuery("SELECT * FROM user WHERE id="+id);
		try {
			ResultSet result = statement.getResultSet();
			if (result.next()) {
				user.setId(result.getInt(COLUMN_ID));
				user.setPseudo(result.getString(COLUMN_PSEUDO));
				user.setPasswd(result.getString(COLUMN_PASSWD));
				user.setFirstname(result.getString(COLUMN_FIRSTNAME));
				user.setLastname(result.getString(COLUMN_LASTNAME));
				user.setEmail(result.getString(COLUMN_EMAIL));
				user.setRole(result.getString(COLUMN_ROLE));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoFactory.closeConnection(cnx);
		}
		return user;
	}

	@Override
	public void delete(int id) {
		executeQuery("DELETE FROM user WHERE id="+id);
		daoFactory.closeConnection(cnx);
	}

	@Override
	public void update(User obj) {
		executeQuery("UPDATE user SET " 
						+ " pseudo = " + obj.getPseudo()
						+ " passwd = " + obj.getPasswd()
						+ " firstname = " + obj.getFirstname()
						+ " lastname = " + obj.getLastname()
						+ " email = " + obj.getEmail()
						+ " role = " + obj.getRole()
						+ " WHERE id = " + obj.getId());
		daoFactory.closeConnection(cnx);
	}

	@Override
	public void create(User obj) {
		executeQuery("INSERT INTO book (pseudo, passwd, firstname, lastname, email, role) VALUES"
						+ "("
						+ obj.getPseudo() + ","
						+ obj.getPasswd() + ","
						+ obj.getFirstname() + ","
						+ obj.getLastname() + ","
						+ obj.getEmail() + ","
						+ obj.getRole() + ","
						+ ")"
						+ " WHERE id=" + obj.getId());
		daoFactory.closeConnection(cnx);
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		executeQuery("SELECT * FROM user");
		try {
			ResultSet result = statement.getResultSet();
			
			while (result.next()) {
				users.add(
						new User(
								result.getInt(COLUMN_ID),
								result.getString(COLUMN_PSEUDO),
								result.getString(COLUMN_PASSWD),
								result.getString(COLUMN_FIRSTNAME),
								result.getString(COLUMN_LASTNAME),
								result.getString(COLUMN_EMAIL),
								result.getString(COLUMN_ROLE)
								)
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoFactory.closeConnection(cnx);
		}
		return users;
	}
}
