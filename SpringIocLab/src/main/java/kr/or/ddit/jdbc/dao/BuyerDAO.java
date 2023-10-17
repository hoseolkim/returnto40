package kr.or.ddit.jdbc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.jdbc.vo.BuyerVO;

@Mapper
public interface BuyerDAO {
	public List<BuyerVO> selectBuyerList();
}
