package fr.lokm.model.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;



public class DAOFactory {

    private static DAOFactory instance;
    private static Connection cnx;
    private static String url;
    private static String user;
    private static String password;

    private DAOFactory (String url, String user, String password) {
    	DAOFactory.url = url;
    	DAOFactory.user = user;
    	DAOFactory.password = password;
    }

    public static DAOFactory getInstance() {
        if(null == instance) {
            instance = new DAOFactory(
                "jdbc:mysql://localhost:3306/library",
                "vincent",
                "admin");
        };

        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instance;
    }

    public static Connection getConnection() {
        
    	if (DAOFactory.instance == null) {
    		DAOFactory.instance = DAOFactory.getInstance();
    	}
    	
    	try {DriverManager.getConnection(url, user, password);
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
        return cnx;
    }
    
    public static void closeConnection() {
    	try {
    		if (null != cnx && !cnx.isClosed()) {
    			cnx.close();
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
     BookDAO getBookDAO () {
        return new BookDAOImpl();
    }
}