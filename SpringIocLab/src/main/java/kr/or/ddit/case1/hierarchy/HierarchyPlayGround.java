package kr.or.ddit.case1.hierarchy;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case1.presentation.Case1SamplePresentation;
import kr.or.ddit.case1.service.Case1SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HierarchyPlayGround {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case1/conf/hierarchy/parent-context.xml");
		ConfigurableApplicationContext child =
				new ClassPathXmlApplicationContext(new String[] {
					"kr/or/ddit/case1/conf/hierarchy/child-context.xml"
				}, parent);
		
		Case1SamplePresentation presentation = child.getBean(Case1SamplePresentation.class);
		String content = presentation.makeContent("a001");
		log.info(content);
		
		Case1SampleService service = child.getBean(Case1SampleService.class);
		log.info("주입된 객체 : {}", service);
		
//		parent.getBean(Case1SamplePresentation.class);
		
	}
}
