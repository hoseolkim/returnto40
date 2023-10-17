package kr.or.ddit.memo.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemoVO;

public interface MemoService {
	/**
	 * 메모 작성
	 * @param memoVO
	 * @return 실패 FAIL, 성공 OK
	 */
	public ServiceResult createMemo(MemoVO memoVO);
	/**
	 * 모든 메모 리스트 조회
	 * @return 리스트의 사이즈로 판단
	 */
	public List<MemoVO> retrieveMemoList();
	/**
	 * 메모 삭제
	 * 메모 삭제하기 전 확인작업 필요
	 * @param memoVO
	 * @return 실패 FAIL, 성공 OK
	 */
	public ServiceResult removeMemo(MemoVO memoVO);
	/**
	 * 메모 수정
	 * 메모 수정하기 전 확인작업 필요
	 * @param memoVO
	 * @return 실패 FAIL, 성공 OK
	 */
	public ServiceResult modifyMemo(MemoVO memoVO);
}