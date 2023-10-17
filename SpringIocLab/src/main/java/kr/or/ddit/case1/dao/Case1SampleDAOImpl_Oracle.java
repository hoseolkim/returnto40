package kr.or.ddit.case1.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class Case1SampleDAOImpl_Oracle implements Case1SampleDAO {

	
	public Case1SampleDAOImpl_Oracle() {
		super();
		log.info("{} 생성되었음.", this.getClass().getSimpleName());
	}
	
	public void init() {
		log.info("{} 객체 생성 후 초기화 완료.", this.getClass().getSimpleName());
	}
	public void destroy() {
		log.info("{} 객체 소멸.", this.getClass().getSimpleName());
	}

	@Override
	public String selectSample(String pk) {
		return String.format("Oracle 에서 %s 로 조회한 raw data", pk);
	}

}
