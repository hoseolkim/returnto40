package kr.or.ddit.jackson;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ObjectMapperTest {
	static ObjectMapper mapper;
	
	@BeforeAll
	static void setUp() {
		System.out.println("Before Class");
		mapper = new ObjectMapper();
	}
	
	@Test
	void marshallingTest() throws JsonProcessingException {
		System.out.println("======Java to JSON======");
		Map<String, Object> target = new HashMap<String, Object>();
		target.put("key1", "value1");
		target.put("key2", 12345);
		target.put("key3", true);
		target.put("key4", new Date());
		
		String json = mapper.writeValueAsString(target);
		System.out.println(json);
	}
	@Test
	void marshallingAndSerializationTest() throws IOException {
		System.out.println("======Java to JSON to ByteStream======");
		Map<String, Object> target = new HashMap<String, Object>();
		target.put("key1", "value1");
		target.put("key2", 12345);
		target.put("key3", true);
		target.put("key4", new Date());
		
//		String json = mapper.writeValueAsString(target);
//		System.out.println(json);
		mapper.writeValue(System.out, target);
		
	}
	
	@Test
	void unMarshallingTest() throws JsonMappingException, JsonProcessingException {
		System.out.println("======JSON to Java======");
		String json = "{\"key1\":\"value1\",\"key2\":12345,\"key3\":true,\"key4\":1694140635002}";
		Map<String, Object> target =  mapper.readValue(json, HashMap.class);
		System.out.println(target);
	}
	@Test
	void deSerializationAndUnMarshallingTest() throws IOException {
		System.out.println("======ByteStream to JSON to Java======");
		
		FileInputStream fis = new FileInputStream(new File("d:/sample.json"));
		
		Map<String, Object> target =  mapper.readValue(fis, HashMap.class);
		System.out.println(target);
	}

}








