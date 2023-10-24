package spring.practice.ksh.alba.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import spring.practice.ksh.alba.dao.AlbaDAO;
import spring.practice.ksh.alba.vo.AlbaVO;
import spring.practice.ksh.paging.vo.PaginationInfo;

@Service
public class AlbaServiceImpl implements AlbaService{

	@Inject
	private AlbaDAO dao;
	
	@Override
	public void retrieveAlbaList(PaginationInfo<AlbaVO> paging) {
		int totalRecord = dao.selectTotalCount(paging);
		paging.setTotalRecord(totalRecord);
		List<AlbaVO> dataList = dao.selectAlbaList(paging);
		paging.setDataList(dataList);
	}

}
