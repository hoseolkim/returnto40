package com.springboard.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboard.board.service.BoardService;
import com.springboard.board.vo.FreeBoardVO;

@Controller
@RequestMapping("/board/{boNo}/edit")
public class BoardUpdateController {
	
	@Inject
	private BoardService service;
	
	@GetMapping
	public String boardUpdateForm(
		@PathVariable int boNo
		, Model model
	) {
		FreeBoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardEdit";
	}
	
	
	
	
	
	
}
