package kr.or.ddit.buyer.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class BuyerRetrieveController {
	
	@Inject
	private BuyerService service;
	
	@GetMapping("/buyer/buyerView.do")
	public String buyerDetail(
		@RequestParam(value = "what",required = true) String buyerId
		, Model model
	) {
		String viewName = null;
		
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		
		model.addAttribute("buyer", buyer);
		
		viewName = "buyer/buyerView";
		
		return viewName;
	}
	
}
