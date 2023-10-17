package kr.or.ddit.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResourceVO {
	
	@Value("https://www.google.com/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png")
	private Resource res1;
	
	@Value("classpath:kr/or/ddit/db/dbInfo.properties")
	private Resource res2;
	
	@Value("file:D:/02.medias/images/cute4.JPG")
	private Resource res3;
}
