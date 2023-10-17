package kr.or.ddit.calculate;

public enum NumericOperatorType {
	PLUS('+', (l,r) -> l + r),
	MINUS('-', (l,r) -> l - r),
	MULTIPLY('*', (l,r) -> l * r),
	DIVIDE('/', (l,r) -> {return l / r; }),
	MODULO('%', (l,r) -> {return l % r; })
	;
	
	private char sign;
	private BiOperandOperator realOperator;
	
	private NumericOperatorType(char sign, BiOperandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	public char getSign() {
		return sign;
	}
	
	public int operate(int leftOp, int rightOp) {
		return realOperator.operate(leftOp, rightOp);
	}
	
	public String getExpression(int leftOp, int rightOp) {
		return String.format("%d %c %d = %d",leftOp, sign, rightOp, operate(leftOp, rightOp));
	}
	
}