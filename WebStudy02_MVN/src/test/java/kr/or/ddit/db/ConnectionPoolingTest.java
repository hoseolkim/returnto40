package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

class ConnectionPoolingTest {
	private static String url;
	private static String user;
	private static String password;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Properties dbInfo = new Properties();
		try(
			InputStream is =  ConnectionFactory.class.getResourceAsStream("dbInfo.properties");
		) {
			dbInfo.load(is);
			driverClassName = dbInfo.getProperty("driverClassName");
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String driverClassName;

	@Test
	void test4() throws SQLException {
		
		for(int i = 0 ; i < 100 ; i++) {
			try(
				Connection conn = ConnectionFactory.getConnection();
			){}
		}
	}
	
	
	
	
	@Test
	void test3() throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName );
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		for(int i = 0 ; i < 100 ; i++) {
			try(
				Connection conn = dataSource.getConnection();
			){}
		}
	}
	
	
	
	
	
	@Test
	void test2()throws SQLException {
		OracleConnectionPoolDataSource dataSource = new OracleConnectionPoolDataSource();
		dataSource.setURL(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		
		for(int i = 0 ; i < 100 ; i++) {
			try(
				Connection conn = dataSource.getConnection();
			){}
		}
	}
	
	@Test
	void test() throws SQLException {
		
		for(int i = 0 ; i < 100 ; i++) {
			try(
				Connection conn = DriverManager.getConnection(url, user, password);
			){}
		}
		
	}

}
