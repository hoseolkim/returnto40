package kr.or.ddit.case1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentation;

public class Case1PlayGround {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("kr/or/ddit/case1/conf/Case1-Context.xml");
		Case1SamplePresentation presentation = context.getBean(Case1SamplePresentation.class);
		
		
		String pk = "a001";
		String content = presentation.makeContent(pk);
		
		System.out.println(content);
	}
}
