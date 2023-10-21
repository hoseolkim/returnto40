package com.springboard.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboard.board.service.BoardService;
import com.springboard.board.vo.FreeBoardVO;
import com.springboard.paging.BootstrapPaginationRenderer;
import com.springboard.paging.vo.PaginationInfo;
import com.springboard.paging.vo.SearchVO;

/**
 * C : /board/boardInsert.do
 * 	GET(POST) /board/new
 * 	
 * R : /board/boardList.do?page=2 , /board/boardView.do?what=23
 * 	GET /board?page=2,	/board/23
 * 
 * U : /board/boardUpdate.do
 * 	GET /board/23/edit,	PUT /board/23/edit
 * D : /board/boardDelete/do
 * 	DELETE /board/23
 *
 */
@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	@Inject
	private BoardService service;
	
	@GetMapping
	public String retrieveBoardListUI() {
		return "board/boardList";
	}
	
	
	@GetMapping("data")
	public String retrieveBoardList(
		@RequestParam(value="searchType", required = false) String searchType
		, @RequestParam(value="searchWord", required = false) String searchWord
		, @RequestParam(name = "page",required = false, defaultValue = "1") int currentPage
		, Model model
		, @ModelAttribute("simpleCondition") SearchVO simpleCondition
	) {
		PaginationInfo<FreeBoardVO> paging = new PaginationInfo<FreeBoardVO>(5, 3);
		paging.setSimpleCondition(simpleCondition);
		paging.setCurrentPage(currentPage);
		paging.setRenderer(new BootstrapPaginationRenderer());
		service.retrieveBoardList(paging);
		
		model.addAttribute("paging", paging);
		return "jsonView";
	}
	
	@GetMapping("{boNo}")
	public String boardView(
		@PathVariable int boNo
		, Model model
	) {
		FreeBoardVO board = service.retrieveBoard(boNo);
		service.incrementBoardHit(board);
		model.addAttribute("board", board);
		return "board/boardView";
	}
}
