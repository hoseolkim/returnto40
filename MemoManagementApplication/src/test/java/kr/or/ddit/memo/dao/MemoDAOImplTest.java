package kr.or.ddit.memo.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemoVO;

class MemoDAOImplTest {

	
	
	@Test
	void testInsertMemo() {
		MemoDAO dao = MemoDAOImpl.getInstance();
		MemoVO memoVO = new MemoVO();
		memoVO.setContent("테스트용3");
		memoVO.setEmail("loveclimax94@naver.com");
		memoVO.setWriter("김석호");
		memoVO.setWrdate(LocalDate.now());
		
		dao.insertMemo(memoVO);
	}

	@Test
	void testSelectMemoList() {
		MemoDAO dao = MemoDAOImpl.getInstance();
		List<MemoVO> list = dao.selectMemoList();
		
		assertEquals(3, list.size());
		
	}

	@Test
	void testUpdateMemo() {
		MemoVO memoVO = new MemoVO();
		memoVO.setCode(3);
		memoVO.setContent("테스트용3수정테스트");
		MemoDAO dao = MemoDAOImpl.getInstance();
		int cnt = dao.updateMemo(memoVO);
		assertEquals(1, cnt);
		assertNotNull(memoVO.getWrdate());
		System.out.println(memoVO.getWrdate());
	}

	@Test
	void testDeleteMemo() {
		MemoDAO dao = MemoDAOImpl.getInstance();
		int cnt = dao.deleteMemo(3);
		assertEquals(1, cnt);
		
	}

}
