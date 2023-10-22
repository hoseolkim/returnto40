package com.springboard.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboard.board.service.BoardService;
import com.springboard.board.vo.FreeBoardVO;

@Controller
@RequestMapping("/board/{boNo}")
public class BoardDeleteController {
	
	@Inject
	private BoardService service;
	
	@DeleteMapping
	public String deleteBoard(
		FreeBoardVO board
		, RedirectAttributes redirectAttributes
		, SessionStatus sessionStatus
	) {
		String viewName = null;
		try {
			service.deleteBoard(board);
			sessionStatus.setComplete();
			viewName = "redirect:/board";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "비밀번호가 틀렸어용");
			viewName = "redirect:/board/"+board.getBoNo();
		}
		return viewName;
	}
}
