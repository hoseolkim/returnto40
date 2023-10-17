package kr.or.ddit.login.service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	
	private MemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public boolean authenticate(MemberVO inputData) {
		MemberVO saved = memberDAO.selectMemberForAuth(inputData);
		return saved!=null;
	}
}