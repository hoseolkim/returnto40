package kr.or.ddit.dummy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PlayGround {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("kr/or/ddit/dummy/conf/dummy-context.xml");
		
		Foo foo = context.getBean(Foo.class);
		
		System.out.println(foo);
		
	}
	
}
