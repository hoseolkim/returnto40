package com.springboard.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboard.board.service.BoardService;
import com.springboard.board.vo.FreeBoardVO;

/**
 * C : /board/boardInsert.do
 * R : /board/boardList.do?page=2 , /board/boardView.do?what=23
 * U : /board/boardUpdate.do
 * D : /board/boardDelete/do
 *
 */
@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	@Inject
	private BoardService service;
	
	@GetMapping("boardList.do")
	public String retrieveBoardList(
		@RequestParam(name = "page",required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		
		return "";
	}
	
	
	
	@GetMapping("boardView.do")
	public String retrieveBoard(
		@RequestParam("what") int boNo
		, Model model
	) {
		FreeBoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardView";
	}
}
