package kr.or.ddit.buyer.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;

@Mapper
public interface BuyerDAO {
	/**
	 * 제조사 상세 조회
	 * @param buyerId
	 * @return
	 */
	public BuyerVO selectBuyer(String buyerId);
	
	/**
	 * 제조사 등록
	 * @param buyer
	 * @return
	 */
	public int insertBuyer(BuyerVO buyer);
}
