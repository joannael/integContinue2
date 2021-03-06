package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class JDBCConfigurationSol1 {
	
	static Logger logger = Logger.getLogger("Logger");	
	
	public static String url;
	@Value("${jdbc.url}")
	public void setUrl(String string) {
		url = string;
	}
	public static String user;
	@Value("${jdbc.user}")
	public void setUser(String string) {
		user = string;
	}
	public static String password;
	@Value("${jdbc.password}")
	public void setPassword(String string) {
		password = string;
	} 
	
	@Bean
    public static Connection getConnection(){		

		Connection connection = null;
		
		try {
		      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		      connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ville_france?user=root&password=password");
		    } catch (SQLException e) {
		    	logger.log(Level.INFO, "erreur");

		      
		    }
		    return connection;
	
    }
    
}
