package kr.or.ddit.memo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.MemoSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemoVO;

public class MemoDAOImpl implements MemoDAO {
	private static MemoDAO dao;
	private SqlSessionFactory sqlSessionFactory = MemoSqlSessionFactoryBuilder.getSqlSessionFactory();
	public static synchronized MemoDAO getInstance() {
		if(dao==null) dao = new MemoDAOImpl();
		return dao;
	}
	
	private MemoDAOImpl() {}
	
	
	@Override
	public int insertMemo(MemoVO memoVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){
			MemoDAO mapper = session.getMapper(MemoDAO.class); 
			return mapper.insertMemo(memoVO);
		}
	}

	@Override
	public List<MemoVO> selectMemoList() {
		try(
			SqlSession session = sqlSessionFactory.openSession();
		){
			MemoDAO mapper = session.getMapper(MemoDAO.class); 
			return mapper.selectMemoList();
		}
	}


	@Override
	public int updateMemo(MemoVO memoVO) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){
			MemoDAO mapper = session.getMapper(MemoDAO.class); 
			return mapper.updateMemo(memoVO);
		}
	}

	@Override
	public int deleteMemo(int code) {
		try(
			SqlSession session = sqlSessionFactory.openSession(true);
		){
			MemoDAO mapper = session.getMapper(MemoDAO.class); 
			return mapper.deleteMemo(code);
		}
	}
}