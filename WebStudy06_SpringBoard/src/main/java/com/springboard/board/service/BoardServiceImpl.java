package com.springboard.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springboard.board.dao.BoardDAO;
import com.springboard.board.vo.FreeBoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	@Override
	public void createBoard(FreeBoardVO board) {
		
	}

	@Override
	public FreeBoardVO retrieveBoard(int boNo) {
		return dao.selectBoard(boNo);
	}

}
