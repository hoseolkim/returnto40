package kr.or.ddit.vo;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"cartNo","cartProd"})
public class CartVO {
	
	private String cartMember;
	private String cartNo;
	private String cartProd;
	private Integer cartQty;
	
	private LocalDate cartDate;
	
	
	// 1:1 has a 관계..
	private ProdVO prod;
	private MemberVO member;
	
}