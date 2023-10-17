package kr.or.ddit.servlet06.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DataBasePropertyVO;

// POJO(Plain Old Java Object)
public class DataBasePropertyDAO {
	private SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
//	Mybatis 프레이워크는 필요한 객체를 대신 만들고, 어플리케이션 내부로 주입하는 역할을 함. 
//	- IoC(Inversion Of Controll, DI : Dependency Injection) 패턴 활용
	
	public List<DataBasePropertyVO> selectDBPropertyList(){
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			return sqlSession.selectList("kr.or.ddit.servlet06.dao.DataBasePropertyDAO.selectDBPropertyList");
		}
	}
}














