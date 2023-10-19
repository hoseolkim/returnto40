package egovframework.example.dummy.controller;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.dummy.service.DummyService;

@Controller
public class DummyController {
	
	@Inject
	private DummyService service;
	
	@RequestMapping("/dummy.do")
	public String dummyHandler(
		Model model
	) {
		model.addAttribute("dummyModel", LocalDateTime.now());
		return "dummy/view";
	}
}
