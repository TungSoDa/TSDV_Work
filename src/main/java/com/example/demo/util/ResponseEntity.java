/*********************************************************************************
 * VERSION      AUTHOR      DATA       DESCRIPTION
 * 0.01         TSDV     2017/10/25 Initialize
 *********************************************************************************/
package com.example.demo.util;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * The ResponseEntity class
 */
@Getter
@Setter
@Builder
public class ResponseEntity<T> {
	private T body;
	private Map<String, String> headers;
	private HttpStatus statusCode;
	private String message;
	private Object parameter;

	public ResponseEntity() {
		super();
	}

	public ResponseEntity(T body, Map<String, String> headers, HttpStatus status, String message, Object parameter) {
		this.body = body;
		this.headers = headers;
		this.statusCode = status;
		this.message = message;
		this.parameter = parameter;
	}

	public ResponseEntity(T body, Map<String, String> headers, HttpStatus status, String message) {
		this.body = body;
		this.headers = headers;
		this.statusCode = status;
		this.message = message;
	}

	public ResponseEntity(T body, Map<String, String> headers, HttpStatus statusCode) {
		super();
		this.body = body;
		this.headers = headers;
		this.statusCode = statusCode;
	}

	public ResponseEntity(T body, HttpStatus statusCode, String message) {
		super();
		this.body = body;
		this.statusCode = statusCode;
		this.message = message;
	}

	public ResponseEntity(T body, HttpStatus statusCode, String message, Object parameter) {
		super();
		this.body = body;
		this.statusCode = statusCode;
		this.message = message;
		this.parameter = parameter;
	}

	public ResponseEntity(HttpStatus statusCode, String message, Object parameter) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.parameter = parameter;
	}

	public ResponseEntity(T body, HttpStatus statusCode) {
		this.body = body;
		this.statusCode = statusCode;
	}

	public ResponseEntity(HttpStatus statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	@JsonGetter("statusCode")
	public int getStatusValue() {
		return statusCode.value();
	}

}
