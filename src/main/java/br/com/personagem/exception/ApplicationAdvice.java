package br.com.personagem.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApplicationAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		
		ApiErrors apiErrors =  new ApiErrors(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
				.collect(Collectors.toList()));
		
		return new ResponseEntity<Object>(apiErrors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
		String messageErro = ex.getReason();
		HttpStatus status = ex.getStatus();
		ApiErrors apiErrors = new ApiErrors(messageErro);
		return new ResponseEntity<Object>(apiErrors, status);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
		String messageErro = ex.getMessage();

		ApiErrors apiErrors = new ApiErrors(messageErro);

		return new ResponseEntity<Object>(apiErrors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}