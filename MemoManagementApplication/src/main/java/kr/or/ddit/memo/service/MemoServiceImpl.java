package kr.or.ddit.memo.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.memo.dao.MemoDAOImpl;
import kr.or.ddit.vo.MemoVO;

public class MemoServiceImpl implements MemoService {
	private MemoDAO dao = MemoDAOImpl.getInstance();
	private MemoAthenticateService authService = MemoAthenticateServiceImpl.getInstance();
	
	private static MemoService service;
	private MemoServiceImpl() {}
	public static MemoService getInstacne() {
		if(service == null) service = new MemoServiceImpl();
		return service;
	}
	
	@Override
	public ServiceResult createMemo(MemoVO memoVO) {
		ServiceResult result = null;
		int rowcnt = dao.insertMemo(memoVO);
		if(rowcnt >= 1 ) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public List<MemoVO> retrieveMemoList() {
		return dao.selectMemoList();
	}

	@Override
	public ServiceResult removeMemo(MemoVO memoVO) {
		ServiceResult result = null;
		int rowcnt = -1;
//		if(result.equals(ServiceResult.OK)){
//		}
		rowcnt = dao.deleteMemo(memoVO.getCode());
		
		if(rowcnt >= 1) {
			result = ServiceResult.OK;
		}else if(rowcnt != -1) {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}

	@Override
	public ServiceResult modifyMemo(MemoVO memoVO) {
		ServiceResult result = null;
		int rowcnt = -1;
		rowcnt = dao.updateMemo(memoVO);
		/*
		if(result.equals(ServiceResult.OK)){
		}
		*/
		if(rowcnt >= 1) {
			result = ServiceResult.OK;
		}else if(rowcnt != -1) {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}

}
