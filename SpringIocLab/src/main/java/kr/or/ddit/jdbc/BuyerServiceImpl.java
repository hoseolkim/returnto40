package kr.or.ddit.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import kr.or.ddit.jdbc.dao.BuyerDAO;
import kr.or.ddit.jdbc.vo.BuyerVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuyerServiceImpl {
	
	private final BuyerDAO dao;
	
	@PostConstruct
	public void init() {
		log.info("주입된 객체 : {}" , dao);
	}
	
	
	public List<BuyerVO> retrieveBuyerList(){
		return dao.selectBuyerList();
	}
}