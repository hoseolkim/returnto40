package spring.practice.ksh.paging.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchVO implements Serializable{
	private String searchType;
	private String searchWord;
}
