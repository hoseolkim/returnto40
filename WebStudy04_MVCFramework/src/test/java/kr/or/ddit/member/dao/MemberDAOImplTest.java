package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();

	@Test
	void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		assertNotNull(member);
		member.getCartSet()
			.forEach((cv)->{
				ProdVO pv = cv.getProd();
				log.info("prodName : {}, lprodNm : {}, cartQty : {}", pv.getProdName(), pv.getLprod().getLprodNm(), cv.getCartQty());
			});
	}
	
	@Test
	void testSelectMemberForAuth() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("a001");
		inputData.setMemPass("asdfasdf");
		MemberVO saved = dao.selectMemberForAuth(inputData);
		assertNotNull(saved);
	}
	
	@Test
	void testSelectMemberList() {
		PaginationInfo<MemberVO> paging = new PaginationInfo<>();
		paging.setCurrentPage(2);
		List<MemberVO> memberList = dao.selectMemberList(paging);
		assertNotEquals(true, memberList.isEmpty());
	}

	@Test
	void testInsertMember() {
		MemberVO member = new MemberVO();
		member.setMemId("a004");
		member.setMemName("신규");
		member.setMemPass("java");
		member.setMemZip("12345");
		member.setMemAdd1("대전 중구");
		member.setMemAdd2("오류동");
		member.setMemMail("aa@naver.com");
		
		int rowcnt = assertDoesNotThrow(()->{
			return dao.insertMember(member);
		});
		assertEquals(1, rowcnt);
	}
}
