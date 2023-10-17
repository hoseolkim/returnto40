package kr.or.ddit.collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SelfTest {
	enum Operator {
		PLUS("+"),
		MINUS("-"),
		MULTIPLY("*"),
		DIVIDE("/");

		private String oper;
		
		public String getOper() {
			return this.oper;
		}
		
		Operator(String oper){
			this.oper = oper;
		}

	}
	@Test
	void test() {
		Operator oper = Operator.PLUS;
		
		String op = oper.toString();
		
		System.out.println(op);
		System.out.println(oper);
		
		System.out.println(oper.getOper());
		
		Operator[] opers = Operator.values();
		
		
		
		
		
		
		
		
		
	}

}
