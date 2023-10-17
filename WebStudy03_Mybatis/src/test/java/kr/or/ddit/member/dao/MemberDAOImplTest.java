package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;

class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();
	
	
	@Test
	void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		assertNotNull(member);
//		member.getProdSet()
//			.forEach((pv)->{
//				System.out.println(pv.getProdName()+ ", "+pv.getLprod().getLprodNm());
//			});
		
		
	}
	
	@Test
	void testSelectMemberForAuth() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("a001");
		inputData.setMemPass("asdfasdf");
		
		MemberVO saved = dao.selectMemberForAuth(inputData);
		assertNotNull(saved);
	}
	

}
