package kr.or.ddit.buyer.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.vo.BuyerVO;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Inject
	private BuyerDAO dao;
	
	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		BuyerVO buyer = null;
		
		buyer = dao.selectBuyer(buyerId);
		if(buyer == null) {
			// 추후 수정 예정
			throw new RuntimeException();
		}
		return buyer;
	}

}
