package kr.or.ddit.case1.dao;

public class Case1SampleDAOFactory {
	public static Case1SampleDAO getCase1SampleDAO() {
		return new Case1SampleDAOImpl_Oracle();
	}
}
