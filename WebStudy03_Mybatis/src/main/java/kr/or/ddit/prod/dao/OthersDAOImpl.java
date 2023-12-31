package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

public class OthersDAOImpl implements OthersDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public List<LprodVO> selectLprodList() {
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			OthersDAO mapper = session.getMapper(OthersDAO.class);
			return mapper.selectLprodList();
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(String lprodGu) {
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			OthersDAO mapper = session.getMapper(OthersDAO.class);
			return mapper.selectBuyerList(lprodGu);
		}
	}

}
