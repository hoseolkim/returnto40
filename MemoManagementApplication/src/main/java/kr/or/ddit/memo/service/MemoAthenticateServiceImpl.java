package kr.or.ddit.memo.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.memo.dao.MemoAthenticateDAO;
import kr.or.ddit.memo.dao.MemoAthenticateDAOImpl;
import kr.or.ddit.vo.MemoVO;

public class MemoAthenticateServiceImpl implements MemoAthenticateService {
	private static MemoAthenticateService service;
	private MemoAthenticateDAO dao = MemoAthenticateDAOImpl.getInstance();
	
	public static synchronized MemoAthenticateService getInstance() {
		if(service==null) service = new MemoAthenticateServiceImpl();
		return service;
	}
	
	private MemoAthenticateServiceImpl() {}
	
	
	@Override
	public ServiceResult authMemo(MemoVO memoVO) {
		return dao.memoAthenticate(memoVO) >= 1 ? ServiceResult.OK : ServiceResult.INVALIDEMAIL;
	}

}
