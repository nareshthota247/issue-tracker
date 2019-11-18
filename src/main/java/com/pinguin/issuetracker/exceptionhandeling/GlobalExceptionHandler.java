package com.pinguin.issuetracker.exceptionhandeling;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(NoRecordException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		logger.error("Exception DeveloperNotFoundException :: {} ", ex.getMessage());
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(Integer.valueOf(1001));
		error.setErrorDesc(ex.getMessage());
		error.setTimestamp(new Date());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		logger.error("Exception MethodArgumentNotValidException :: {} ", ex.getMessage());
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(HttpMessageNotReadableException ex) {
		logger.error("Exception HttpMessageNotReadableException :: {} ", ex.getMessage());
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(Integer.valueOf(1003));
		error.setErrorDesc(ex.getMessage());
		error.setTimestamp(new Date());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(IllegalArgumentException ex) {
		logger.error("Exception IllegalArgumentException :: {} ", ex.getMessage());
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(Integer.valueOf(1004));
		error.setErrorDesc(ex.getMessage());
		error.setTimestamp(new Date());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/error")
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

		Integer statusCode = null;
		String errorMessage = null;
		if (status != null)
			statusCode = Integer.valueOf(status.toString());
		if (exception != null)
			errorMessage = exception.getMessage();

		ErrorResponse errorDetails = new ErrorResponse(new Date(), statusCode, errorMessage);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
