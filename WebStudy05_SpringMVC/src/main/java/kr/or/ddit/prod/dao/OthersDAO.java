package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

/**
 * 상품 분류 선택과 제조사 선택 UI를 구성하기 위한 Persistence Layer
 *
 */
@Mapper
public interface OthersDAO {
	/**
	 * 전체 상품 분류 조회
	 * @return
	 */
	public List<LprodVO> selectLprodList();
	
	/**
	 * 특정 분류에 해당하는 제조사 목록 조회
	 * @param lprodGu 없는 경우, 전체 제조사 목록 조회
	 * @return
	 */
	public List<BuyerVO> selectBuyerList(@Param("lprodGu") String lprodGu);
}






















