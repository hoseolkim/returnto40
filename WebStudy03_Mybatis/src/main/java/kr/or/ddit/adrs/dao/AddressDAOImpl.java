package kr.or.ddit.adrs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AddressVO;

public class AddressDAOImpl implements AddressDAO {
	private SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertAddress(AddressVO adrsVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);	
		){
			AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
			return mapperProxy.insertAddress(adrsVO);
		}
	}

	@Override
	public List<AddressVO> selectAddressList(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
//			return sqlSession.selectList("kr.or.ddit.adrs.dao.AddressDAO.selectAddressList", memId);
			AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
			return mapperProxy.selectAddressList(memId);
		}
	}

	@Override
	public int updateAddress(AddressVO adrsVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);	
		){
			AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
			return mapperProxy.updateAddress(adrsVO);
		}
	}

	@Override
	public int deleteAddress(int adrsNo) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			AddressDAO mapperProxy = sqlSession.getMapper(AddressDAO.class);
			int rowcnt = mapperProxy.deleteAddress(adrsNo);
			sqlSession.commit();
			return rowcnt;
		}
	}
	
	
}













