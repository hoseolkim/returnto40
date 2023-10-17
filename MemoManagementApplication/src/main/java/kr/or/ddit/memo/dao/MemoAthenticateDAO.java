package kr.or.ddit.memo.dao;

import kr.or.ddit.vo.MemoVO;

public interface MemoAthenticateDAO {
	/**
	 * 메모 삭제,수정 시 메모 번호와 이메일이 일치하는지 확인
	 * @param memovO 메모 번호와 이메일이 담긴 memoVO
	 * @return 일치 시 ( >=1)
	 */
	public int memoAthenticate(MemoVO memovO);
	
	
	
	
}
