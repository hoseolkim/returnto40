package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="adrsNo")
public class AddressVO implements Serializable{
	private int adrsNo;
	private String memId;
	@NotBlank
	private String adrsName;
	@NotBlank
	private String adrsHp;
	private String adrsAdd;
}
