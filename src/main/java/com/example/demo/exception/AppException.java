package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public AppException(HttpStatus status, String msg) {
		super(msg);
		this.status = status;
	}
}
