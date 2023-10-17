package kr.or.ddit.adrs.service;

import java.util.List;

import kr.or.ddit.vo.AddressVO;

/**
 * 주소록 관리를 위한 Business Logic Layer
 *
 */
public interface AddressService {
	public boolean createAddress(AddressVO adrsVO);
	public List<AddressVO> retriveAddressList(String memId);
	public boolean modifyAddress(AddressVO adrsVO);
	public boolean removeAddress(int adrsNo);
}
