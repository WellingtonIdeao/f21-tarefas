package br.com.wellington.tarefas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	public static Connection getConection() {
		
		try {
			Properties prop = new Properties();
			prop.setProperty("user","postgres");
			prop.setProperty("password","Ccy123");
			
			Class.forName("org.postgresql.Driver");
			String url= "jdbc:postgresql://localhost:5432/postgres";
			return DriverManager.getConnection(url,prop);
		} catch (SQLException e ) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e2) {
			throw new RuntimeException(e2);
		}
		
		//urlMsql -- "jdbc:mysql://localhost/caellumjavaweb?serverTimezone=UTC&useSSL=false","root","Ccy123"		
		
	} 
}
