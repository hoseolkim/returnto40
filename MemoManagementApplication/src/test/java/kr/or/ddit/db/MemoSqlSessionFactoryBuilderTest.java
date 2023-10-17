package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

class MemoSqlSessionFactoryBuilderTest {

	@Test
	void test() {
		SqlSessionFactory fac = MemoSqlSessionFactoryBuilder.getSqlSessionFactory();
		
		assertNotNull(fac);
	}

}
