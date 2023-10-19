package kr.or.ddit.buyer.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class BuyerRetrieveControllerTest extends AbstractRootContextTest{

	@Inject
	private BuyerService service;
	
	@Test
	void testBuyerDetail() {
		String buyerId = "P10202";
		
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		
		log.info("조회한 바이어~ : {}",buyer);
		List<ProdVO> prodList = buyer.getProdList();
		log.info("조회한 목록~ : {}",prodList);
		
	}

}
