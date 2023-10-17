package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
class ProdDAOImplTest {
	ProdDAO dao = new ProdDAOImpl();
	static PaginationInfo<ProdVO> paging;
	
	@BeforeAll
	public static void setUp(){
		paging = new PaginationInfo<>();
	}
	
	@Disabled
	@Test
	void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
	}

	@Order(1)
	@Test
	void testSelectTotalRecord() {
		 int totalRecord = dao.selectTotalRecord(paging);
		 paging.setTotalRecord(totalRecord);
	}

	@Order(2)
	@Test
	void testSelectProdList() {
		paging.setCurrentPage(2);
		List<ProdVO> dataList = dao.selectProdList(paging);
		paging.setDataList(dataList);
		log.info("paging : {}", paging);
	}
}















