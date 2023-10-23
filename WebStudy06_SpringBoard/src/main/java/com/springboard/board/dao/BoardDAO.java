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
	
	/**
	 * 게시글 수정과 삭제에서 사용될 인증
	 * @param board
	 * @return 인증에 성공했을 경우, 해당 게시글을 반환, 실패한 경우 null값 반환
	 */
	public FreeBoardVO checkBoard(FreeBoardVO board);
	
	public int deleteBoard(@Param("boNo") int boNo);
	
}
