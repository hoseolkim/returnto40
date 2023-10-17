package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

class CustomSqlSessionFactoryBuilderTest {

	@Test
	void testGetSqlSessionFactory() {
		SqlSessionFactory factory = assertDoesNotThrow(()->{
			return CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
		});
		assertNotNull(factory);
	}

}
