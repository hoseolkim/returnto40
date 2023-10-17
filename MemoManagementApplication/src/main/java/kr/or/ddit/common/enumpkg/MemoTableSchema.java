package kr.or.ddit.common.enumpkg;

public enum MemoTableSchema {
	WRITER(20,"CHAR"),CONTENT(500,"CHAR"),EMAIL(200,"BYTE");

	private final int length;
	private final String dataType;
	MemoTableSchema(int len, String type) {
		this.length = len;
		this. dataType = type;
	}
	public String getDataType() {
		return dataType;
	}
	public int getLength() {
		return length;
	}
}
