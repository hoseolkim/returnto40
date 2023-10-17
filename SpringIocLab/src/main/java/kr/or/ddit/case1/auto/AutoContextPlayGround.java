package kr.or.ddit.case1.auto;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoContextPlayGround {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("kr/or/ddit/case1/conf/auto/auto-context.xml");
		context.registerShutdownHook();
//		Case1SamplePresentation presentation = context.getBean(Case1SamplePresentation.class);
//		String pk = "a001";
//		String content = presentation.makeContent(pk);
//		log.info(content);
		int count = context.getBeanDefinitionCount();
		String[] names = context.getBeanDefinitionNames();
		
		log.info("빈 갯수 : {}" , count);
		log.info("빈 이름들 : {}" , Arrays.toString(names));
		
	}
}