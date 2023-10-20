package com.springboard.board.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.springboard.board.service.BoardService;
import com.springboard.board.vo.AttatchVO;

@Controller
public class BoardFileDownloadController {
	
	@Inject
	private BoardService service;
	
	@Value("#{appInfo.boFiles}")
	private Resource boFiles;
	
	@GetMapping("/board/{boNo}/boFile/{attNo}")
	public ResponseEntity<Resource> download(
		@PathVariable(name = "attNo") int attNo
	) throws IOException {
		
		AttatchVO atch = service.retrieveAttatch(attNo);
		
		Resource boFile = boFiles.createRelative(atch.getAttSavename());
		
		if(!boFile.exists()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 파일이 없음.");
		}
		
		ContentDisposition disposition = ContentDisposition.attachment()
				.filename(atch.getAttFilename(), Charset.defaultCharset())
				.build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(disposition);
//		headers.setContentLength(boFile.contentLength());
		headers.setContentLength(atch.getAttFilesize());
		// 보통 스트림카피로 전달되는 컨텐츠의 타입을 이렇게 세팅한다.
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return ResponseEntity.ok()
				.headers(headers)
				.body(boFile);
	}
	
	
}
