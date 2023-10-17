package kr.or.ddit.filter.wrapper;

import java.security.Principal;

import kr.or.ddit.vo.MemberVO;

public class MemberVOWrapper implements Principal {
	private MemberVO adaptee;

	public MemberVOWrapper(MemberVO adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public String getName() {
		return adaptee.getMemId();
	}
	
	public MemberVO getRealUser() {
		return adaptee;
	}
}
