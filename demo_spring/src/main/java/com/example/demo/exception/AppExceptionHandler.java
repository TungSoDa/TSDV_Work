/*********************************************************************************
 * VERSION      AUTHOR      DATA       DESCRIPTION
 * 0.01         TSDV     2017/10/25 Initialize
 *********************************************************************************/
package com.example.demo.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.util.MessageResource;
import com.example.demo.util.ResponseEntity;

/**
 * The AppExceptionHandler class
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

	@ExceptionHandler({ IOException.class })
	@ResponseBody
	public ResponseEntity<?> handleIoException(Exception e) {
		logger.error(e.getMessage(), e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND, MessageResource.IO_EXCEPTION, null);
	}

	@ExceptionHandler({ AppException.class })
	@ResponseBody
	public ResponseEntity<?> handleCustomException(AppException e) {
		logger.error("", e);
		return new ResponseEntity<>(e.getMessage(), e.getStatus(), e.getMessage(), null);
		// return null;
	}

	@ExceptionHandler({ Throwable.class, Exception.class })
	@ResponseBody
	public ResponseEntity<?> handleOthersException(Throwable e) {
		logger.error("", e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				MessageResource.INTERNAL_SERVER_ERROR, null);
	}

	@ExceptionHandler({ DataAccessException.class })
	@ResponseBody
	public ResponseEntity<?> handleDataAccessException(Exception e) {
		logger.error(e.getMessage(), e);
		return new ResponseEntity<>(MessageResource.toMessage(MessageResource.MANIPULATE_DB, MessageResource.CRUD),
				HttpStatus.INTERNAL_SERVER_ERROR, MessageResource.DATA_ACCESS_EXCEPTION, null);
	}
}
