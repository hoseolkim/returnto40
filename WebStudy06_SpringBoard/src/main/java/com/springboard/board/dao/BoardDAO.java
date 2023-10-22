package com.springboard.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboard.board.vo.FreeBoardVO;
import com.springboard.paging.vo.PaginationInfo;

@Mapper
public interface BoardDAO {
	public int insertBoard(FreeBoardVO board);
	public FreeBoardVO selectBoard(@Param("boNo") int boNo);
	public int incrementBoard(@Param("boNo") int boNo);
	
	public int selectTotalRecord(PaginationInfo<FreeBoardVO> paging);
	public List<FreeBoardVO> selectBoardList(PaginationInfo<FreeBoardVO> paging);
	
	public int updateBoard(FreeBoardVO board);
	
	public FreeBoardVO checkBoard(FreeBoardVO board);
	
	public int deleteBoard(@Param("boNo") int boNo);
}
