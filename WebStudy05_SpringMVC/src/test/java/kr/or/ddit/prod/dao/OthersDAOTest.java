package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import kr.or.ddit.AbstractRootContextTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class OthersDAOTest extends AbstractRootContextTest {
	
	@Inject
	private OthersDAO othersDAO;
	
	@Test
	void testSelectLprodList() {
		othersDAO.selectLprodList();
	}

	@Test
	void testSelectBuyerList() {
		othersDAO.selectBuyerList(null);
	}

}
