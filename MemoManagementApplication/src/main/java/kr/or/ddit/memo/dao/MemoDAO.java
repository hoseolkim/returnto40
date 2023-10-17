package kr.or.ddit.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.MemoVO;

public interface MemoDAO {
	/**
	 * 메모 작성
	 * @param memoVO
	 * @return 성공시 ( >=1)
	 */
	public int insertMemo(MemoVO memoVO);
	/**
	 * 모든 메모 조회
	 * @return List Size로 판단
	 */
	public List<MemoVO> selectMemoList();
	/**
	 * 메모 수정
	 * @param memoVO 수정할 메모 정보가 담긴 memoVO
	 * @return 성공시 ( >=1)
	 */
	public int updateMemo(MemoVO memoVO);

	/**
	 * 메모 삭제
	 * @param code 삭제할 메모 번호
	 * @return 삭제 성공시( >=1) 
	 */
	public int deleteMemo(@Param("code") int code);
}
