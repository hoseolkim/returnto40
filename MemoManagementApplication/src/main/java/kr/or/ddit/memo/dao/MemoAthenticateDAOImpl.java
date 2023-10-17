package kr.or.ddit.memo.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.MemoSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemoVO;

public class MemoAthenticateDAOImpl implements MemoAthenticateDAO {
	private static MemoAthenticateDAO dao;
	private SqlSessionFactory sqlSessionFactory  = MemoSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	public static synchronized MemoAthenticateDAO getInstance() {
		if(dao==null) dao = new MemoAthenticateDAOImpl();
		return dao;
	}
	
	private MemoAthenticateDAOImpl() {}
	
	@Override
	public int memoAthenticate(MemoVO memovO) {
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			MemoAthenticateDAO mapper = session.getMapper(MemoAthenticateDAO.class); 
			return mapper.memoAthenticate(memovO);
		}
	}

}
