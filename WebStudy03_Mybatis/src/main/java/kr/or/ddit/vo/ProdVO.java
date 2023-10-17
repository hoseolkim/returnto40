package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 하나의 삼풍에 대한 정보를 캡슐화하기 위한 도메인 레이어.
 *
 */
@Data
@EqualsAndHashCode(of = "prodId")
public class ProdVO implements Serializable {
	@NotBlank(groups = {UpdateGroup.class,DeleteGroup.class})
	@Pattern(regexp = "P[0-9]{9}",groups = {UpdateGroup.class,DeleteGroup.class})
	private String prodId;
	@NotBlank(groups = InsertGroup.class)
	private String prodName;
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 4 , max = 4, groups = InsertGroup.class)
	private String prodLgu;
	@NotBlank(groups = InsertGroup.class)
	@Size(min = 6 , max = 6,groups = InsertGroup.class)
	private String prodBuyer;
	@NotNull
	private Integer prodCost;
	@NotNull
	private Integer prodPrice;
	@NotNull
	private Integer prodSale;
	@NotBlank(groups = InsertGroup.class)
	private String prodOutline;
	private String prodDetail;
	
	@NotBlank(groups = InsertGroup.class)
	private String prodImg;
	
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	private String prodInsdate;
	@NotNull
	@Min(0)
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private int rnum;
	private int buyerCnt;
	
	// has a ( 1 : 1)
	private LprodVO lprod;
	private BuyerVO buyer;
	
	// has many ( 1 : N )
	private List<CartVO> cartList;
}