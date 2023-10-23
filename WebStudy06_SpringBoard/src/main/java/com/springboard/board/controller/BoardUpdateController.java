package com.springboard.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboard.board.InvalidPasswordException;
import com.springboard.board.service.BoardService;
import com.springboard.board.vo.FreeBoardVO;
import com.springboard.grouphint.UpdateGroup;

@Controller
@RequestMapping("/board/{boNo}/edit")
@SessionAttributes(names = "board")
public class BoardUpdateController {
	
	@Inject
	private BoardService service;
	
	@GetMapping
	public String boardUpdateForm(
		@PathVariable int boNo
		, Model model
	) {
		FreeBoardVO board = null;
		if(!model.containsAttribute("board")) {
			board = service.retrieveBoard(boNo);
			model.addAttribute("board", board);
		}
		return "board/boardEdit";
	}
	
	@PutMapping
	public String boardUpdate(
		@Validated(UpdateGroup.class) @ModelAttribute("board") FreeBoardVO board
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, SessionStatus sessionStatus
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			try {
				service.modifyBoard(board);
				sessionStatus.setComplete();
				viewName = "redirect:/board/{boNo}";
			} catch (InvalidPasswordException e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				viewName = "redirect:/board/{boNo}/edit";
			}
		}else {
			String attrName =BindingResult.MODEL_KEY_PREFIX + "board";
			redirectAttributes.addFlashAttribute(attrName, errors);
			viewName = "redirect:/board/{boNo}/edit";
		}
		return viewName;
	}
	
	
	
	
}
