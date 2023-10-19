package kr.or.ddit.buyer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyer")
public class BuyerDeleteController {
	@DeleteMapping("{buyerId}")
	public String buyerDelete(@PathVariable String buyerID) {
		return "redirect:/buyer";
	}
}
