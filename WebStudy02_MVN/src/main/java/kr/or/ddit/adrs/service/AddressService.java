package kr.or.ddit.adrs.service;

import java.util.List;

import kr.or.ddit.vo.AddressVO;

/**
 * 주소록 관리를 위한 Business Logic Layer
 *
 */
public interface AddressService {
	/**
	 * 주소록에 주소 추가
	 * @param adrsVO 추가할 주소 정보가 담긴 객체
	 * @return 추가 성공시 true 실패시 false
	 */
	public boolean createAddress(AddressVO adrsVO);
	/**
	 * 본인이 소유한 주소록 조회
	 * @param memId 조회할 소유자의 ID
	 * @return size에 따라 주소록 존재 여부 확인
	 */
	public List<AddressVO> retriveAddressList(String memId);
	/**
	 * 주소록 수정
	 * @param adrsVO 수정할 대상이 되는 정보를 가진 객체
	 * @return 수정 성공시 true 실패시 false
	 */
	public boolean modifyAddress(AddressVO adrsVO);
	/**
	 * @param adrsNo 삭제할 주소록 번호
	 * @return 삭제 성공시 true 실패시 false
	 */
	public boolean removeAddress(int adrsNo);
}