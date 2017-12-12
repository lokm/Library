package fr.lokm.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

import fr.lokm.model.beans.Author;
import fr.lokm.model.beans.Book;
import fr.lokm.model.beans.User;



public class DAOFactory {

    private static DAOFactory instance;

    private static String url;
    private static String user;
    private static String password;

    private DAOFactory (String url, String user, String password) {
    	DAOFactory.url = url;
    	DAOFactory.user = user;
    	DAOFactory.password = password;
    }

    // Creer une instance si elle est inexistante, sinon revoie l'instance
    public static DAOFactory getInstance() {
        if(null == instance) {
            instance = new DAOFactory(
                "jdbc:mysql://localhost:3306/library",
                "root",
                "");
        };

        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instance;
    }
    
    // Creer et retourne un connection
    public Connection getConnection() {
        
    	Connection cnx = null;
    	
    	try {
    		cnx = DriverManager.getConnection(url, user, password);
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
        return cnx;
    }
    
    // Ferme la connection
    public void closeConnection(Connection cnx) {
    	try {
    		if (null != cnx && !cnx.isClosed()) {
    			cnx.close();
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    // Instance DAO
     public InterfaceDAO<Book> getBookDAO () {
        return new BookDAOImpl();
    }
     
     public InterfaceDAO<Author> getAuthorDAO () {
         return new AuthorDAOImpl();
     }
     
     public InterfaceDAO<User> getUserDAO () {
         return new UserDAOImpl();
     }
}