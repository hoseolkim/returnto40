package spring.practice.ksh.alba.service;

import spring.practice.ksh.alba.vo.AlbaVO;
import spring.practice.ksh.paging.vo.PaginationInfo;

public interface AlbaService {
	public void retrieveAlbaList(PaginationInfo<AlbaVO> paging);
}
