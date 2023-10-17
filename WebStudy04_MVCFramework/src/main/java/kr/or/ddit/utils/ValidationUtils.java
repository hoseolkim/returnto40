package kr.or.ddit.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.MemberVO;

public class ValidationUtils {
	private static Validator validator;

	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	public static <T> boolean validate(T target, Map<String, List<String>> errors, Class<?>...groupHints) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(target, groupHints);
		if(!constraintViolations.isEmpty()) {
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
		}
		return constraintViolations.isEmpty();
	}
}












