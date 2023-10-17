package kr.or.ddit.dummy;

public class Foo {
	
//	private Bar bar = new Bar();
//	private Baz baz = new Baz();
	
//	private Bar bar = BarFactory.getBar();
	
	
	
	public Foo(Baz baz, Bar bar) {
	super();
	this.baz = baz;
	this.bar = bar;
}

	private Baz baz;
	private Bar bar;
	

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	@Override
	public String toString() {
		return "Foo [baz=" + baz + ", bar=" + bar + "]";
	}
	
}