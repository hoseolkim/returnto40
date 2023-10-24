package spring.practice.ksh.alba.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.practice.ksh.alba.vo.AlbaVO;
import spring.practice.ksh.paging.vo.PaginationInfo;

@Mapper
public interface AlbaDAO {
	public int selectTotalCount(PaginationInfo<AlbaVO> paging);
	public List<AlbaVO> selectAlbaList(PaginationInfo<AlbaVO> paging);
}
