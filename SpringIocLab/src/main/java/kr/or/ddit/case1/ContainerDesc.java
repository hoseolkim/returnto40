package kr.or.ddit.case1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContainerDesc {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case1/conf/Container-Desc.xml");
		context.registerShutdownHook();
		
//		Case1SamplePresentation presentation1 = context.getBean(Case1SamplePresentation.class);
//		Case1SamplePresentation presentation2 = context.getBean(Case1SamplePresentation.class);
//		log.info("주입된 객체 비교 (==) : {}", presentation1 == presentation2 );
		
	}
}
