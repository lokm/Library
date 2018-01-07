package fr.lokm.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class principale {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Author");
			
			while (result.next()) {
				System.out.println(result.getInt(1) + " " + result.getString(1) + " " + result.getString(2));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
