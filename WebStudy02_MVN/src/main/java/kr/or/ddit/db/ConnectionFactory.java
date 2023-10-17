package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * A a1 = new A();
 * A a2 = new A();
 * 1. A 클래스가 로딩.
 * 2. instance 생성(heap memory - GC 대상)
 * 3. a1 에 생성된 인스턴스의 참조 주소 할당.
 * 
 *  Factory Object[Method] Pattern
 *   : 인스턴스 생성에 대한 책임을 지는 factory 객체의 운영
 * 
 * 
 */
public class ConnectionFactory {
	private static BasicDataSource dataSource;
	static {
//		classpath resource 형태 자원 읽기.
//		kr/or/ddit/db/dbInfo.properties
		Properties dbInfo = new Properties();
		try(
			InputStream is =  ConnectionFactory.class.getResourceAsStream("dbInfo.properties");
		) {
			dbInfo.load(is);
//			Class.forName(dbInfo.getProperty("driverClassName"));
			String driverClassName = dbInfo.getProperty("driverClassName");
			String url = dbInfo.getProperty("url");
			String user = dbInfo.getProperty("user");
			String password = dbInfo.getProperty("password");
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driverClassName);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			
			int initialSize = Integer.parseInt(dbInfo.getProperty("initialSize"));
			int maxIdle = Integer.parseInt(dbInfo.getProperty("maxIdle"));
			int maxTotal = Integer.parseInt(dbInfo.getProperty("maxTotal"));
			long maxWait = Long.parseLong(dbInfo.getProperty("maxWait"));
			
			dataSource.setInitialSize(initialSize);
			dataSource.setMaxIdle(maxIdle);
			dataSource.setMaxTotal(maxTotal);
			dataSource.setMaxWaitMillis(maxWait);
			
			
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
}
