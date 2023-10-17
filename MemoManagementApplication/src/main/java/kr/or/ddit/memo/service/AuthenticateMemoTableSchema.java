package kr.or.ddit.memo.service;

import java.util.Map;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemoVO;

public interface AuthenticateMemoTableSchema {
	/**
	 * 메모 VO에 담긴 정보로 테이블의 데이터길이에 적합한지 검증
	 * @param memoVO
	 * @return 적합시 OK, 데이터 길이 초과시 LARGEVALUE
	 */
	public ServiceResult authenticateTableSchema(MemoVO memoVO);
}
