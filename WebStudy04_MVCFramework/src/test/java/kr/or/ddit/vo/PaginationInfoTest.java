package kr.or.ddit.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PaginationInfoTest {

	@Test
	void test() {
		PaginationInfo paging = new PaginationInfo();
		paging.setTotalRecord(122);
		paging.setCurrentPage(3);
		log.info("paging : {}", paging);
		paging.setTotalRecord(120);
		paging.setCurrentPage(10);
		log.info("paging : {}", paging);
	}

}
