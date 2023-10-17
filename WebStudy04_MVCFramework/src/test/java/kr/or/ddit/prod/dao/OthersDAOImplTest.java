package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class OthersDAOImplTest {

	OthersDAO dao = new OthersDAOImpl();
	
	@Test
	void testSelectLprodList() {
		dao.selectLprodList();
	}

	@Test
	void testSelectBuyerList() {
		dao.selectBuyerList(null);
	}

}
