package kr.or.ddit.buyer.service;

import kr.or.ddit.vo.BuyerVO;

public interface BuyerService {
	/**
	 * 제조사 상세 조회
	 * @param buyerId
	 * @return 존재하지 않으면, null 반환
	 */
	public BuyerVO retrieveBuyer(String buyerId);

}
