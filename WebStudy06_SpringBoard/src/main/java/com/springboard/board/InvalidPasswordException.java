package com.springboard.board;

public class InvalidPasswordException extends RuntimeException {

	public InvalidPasswordException() {
		super("게시글 비밀번호 오류");
	}
	
}
