package kr.or.ddit.filter.wrapper;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import kr.or.ddit.vo.MemberVO;

public class PrincipalHttpServletRequestWrapper extends HttpServletRequestWrapper{
	private HttpServletRequest request;

	public PrincipalHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public Principal getUserPrincipal() {
		MemberVO authMember = (MemberVO) getSession().getAttribute("authMember");
		if(authMember!=null) {
			MemberVOWrapper principal = new MemberVOWrapper(authMember);
			return principal;
		}else {
			return super.getUserPrincipal();
		}
	}
}















