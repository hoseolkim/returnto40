package kr.or.ddit.adrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressUIController{
	
	@GetMapping("/adrs/view")
	public String adrsView(){
		return  "adrs/adrsView";
	}
}

