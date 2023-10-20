package com.springboard.board.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.springboard.board.vo.FreeBoardVO;
import com.springboard.paging.vo.PaginationInfo;
import com.springboard.paging.vo.SearchVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/com/springboard/spring/conf/*-context.xml")
class BoardServiceImplTest {
	
	@Inject
	private BoardService service;
	
	@Test
	void testCreateBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testRetrieveBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testRetrieveAttatch() {
		fail("Not yet implemented");
	}

	@Test
	void testRetrieveBoardList() {
		PaginationInfo<FreeBoardVO> paging = new PaginationInfo<FreeBoardVO>(3, 2);
		paging.setCurrentPage(1);
		paging.setSimpleCondition(new SearchVO(null, null));
		service.retrieveBoardList(paging);
		log.info("dataList : {}",paging.getDataList() );
	}

}
