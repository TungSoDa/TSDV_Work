package com.example.demo.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.util.ResponseEntity;


/**
 * The ActivityHandler class
 */
@Aspect
@Component
public class ActivityHandler {
	private static final Logger logger = LoggerFactory.getLogger(ActivityHandler.class);
	private static final Logger operationLog = LoggerFactory.getLogger("operationLogger");
	
	@Pointcut("@annotation(com.example.demo.annotation.Timed) && execution(public * *(..))")
	public void time() {
	}
	
	/**
	 * Log time around function execution which need to calculate time
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("time()")
	public Object logTimeAroundExecute(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();

		Object value;

		try {
			value = proceedingJoinPoint.proceed();
		} finally {
			long duration = System.currentTimeMillis() - start;

			logger.info("{}.{}() took {} ms", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
					proceedingJoinPoint.getSignature().getName(), duration);
		}

		return value;
	}



	@AfterReturning(value = "execution(public com.example.demo.util.ResponseEntity *(..))", returning = "response")
	public void captureAllRestActivities(JoinPoint joinPoint, Object response) {
		Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		if(root.isInfoEnabled()){
			HttpServletRequest request = null;
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			if (requestAttributes instanceof ServletRequestAttributes) {
				request = ((ServletRequestAttributes) requestAttributes).getRequest();
			}
			if (request != null) {
				String action = request.getMethod();
				String pathInfo = request.getServletPath();

				ResponseEntity<?> responseEntity = (ResponseEntity<?>) response;
				if (responseEntity != null) {
					int status = responseEntity.getStatusCode().value();
					String message = responseEntity.getMessage();
					String parameter = "";
					if (responseEntity.getParameter() != null) {
						parameter = responseEntity.getParameter().toString();
					}
					String result = null;
					switch (status) {
					case 200:
						result = "SUCCESS";
						break;

					case 400:
						result = "BAD REQUEST";
						break;

					case 404:
						result = "NOT FOUND";
						break;

					case 406:
						result = "NOT ACCEPTABLE";
						break;

					case 500:
						result = "INTERNAL SERVER ERROR";
						break;

					default:
						result = "UNDEFINED";
						break;
					}

					logger.info("Method: [{}], Path Info: [{}], Result: [{}], Message: [{}], Param: [{}]", action, pathInfo, result,
							message, parameter);

					if ("POST".equals(action)) {
						String responseBody = "";
						if (responseEntity.getBody() != null) {
							responseBody = responseEntity.getBody().toString();
						}
						operationLog.info("Method: [{}], Path Info: [{}], Result: [{}], Message: [{}], Data:[{}]", action,
								pathInfo, result, message, responseBody);
					}
					if (("PUT").equals(action) || ("DELETE").equals(action)) {
						operationLog.info("Method: [{}], Path Info: [{}], Result: [{}], Message: [{}], Data:[{}]", action,
								pathInfo, result, message, parameter);
					}
				}

			}
		}
		
	}
}

