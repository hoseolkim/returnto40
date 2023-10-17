package kr.or.ddit.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DBInfo {
	@Value("#{dbInfoProps.url}")
	private String url;
	@Value("#{dbInfoProps.password}")
	private String password;
	
	@Value("#{dbInfoProps.maxTotal}")
	private int maxTotal;
	@Value("#{dbInfoProps.maxWait}")
	private long maxWait;
	
}
