package kr.or.ddit.validate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

class HibernateValidatorTest {
	
	private static Validator validator;

	@BeforeAll
	static void beforeClass() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void test() {
		MemberVO member = new MemberVO();
//		member.setMemId("a001");
//		member.setMemPass("asdf");
//		member.setMemMail("aa@naver.com");
//		member.setMemName("김은대");
//		member.setMemZip("12345");
//		member.setMemAdd1("대전");
//		member.setMemAdd2("오류");
		member.setMemRegno1("1");
		member.setMemRegno2("1");
		Set<ConstraintViolation<MemberVO>> constraintViolations = validator.validate(member, InsertGroup.class);
		if(constraintViolations.isEmpty()) {
			System.out.println("검증 통과");
		}else {
			Map<String, List<String>> errors = new HashMap<>();
			constraintViolations.stream()
						.forEach((cv)->{
							String propName = cv.getPropertyPath().toString();
							String message = cv.getMessage();
							List<String> already = errors.get(propName);
							if(already==null) {
								already = new ArrayList<String>();
								errors.put(propName, already);
							}
							already.add(message);
							
						});
			errors.forEach((k,v)->{
				System.err.printf("%s : %s\n", k, v);
			});
		}
	}

}












