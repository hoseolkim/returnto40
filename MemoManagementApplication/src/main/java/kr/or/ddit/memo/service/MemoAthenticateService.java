package kr.or.ddit.memo.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemoVO;

public interface MemoAthenticateService {
	/**
	 * 메모 수정 / 삭제시 이메일 인증확인
	 * @param memoVO 이메일, 메모 정보가 담긴 VO
	 * @return 인증 성공시 OK , 실패시 INVALIDEMAIL
	 */
	public ServiceResult authMemo(MemoVO memoVO);
}
