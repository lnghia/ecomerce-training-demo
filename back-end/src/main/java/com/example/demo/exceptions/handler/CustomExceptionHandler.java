package com.example.demo.exceptions.handler;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(UsernamePasswordInvalidException.class)
  public ResponseEntity<ResponseBodyDto<Object>> handleUsernamePasswordInvalidException(
      UsernamePasswordInvalidException exception, WebRequest webRequest) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("credentials", "Invalid username or password");

    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(InvalidTokenException.class)
  public ResponseEntity<ResponseBodyDto<Object>> handleInvalidTokenException(
      InvalidTokenException exception, WebRequest webRequest) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("JWT token", "Invalid token");

    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest webRequest) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    exception
        .getBindingResult()
        .getFieldErrors()
        .forEach(error -> response.getErrors().put(error.getField(), error.getDefaultMessage()));

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleUserNotFoundException(
      UserNotFoundException exception, WebRequest webRequest) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("user", "User not found");

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MissingRequestHeaderException.class)
  @ResponseBody
  protected ResponseEntity<ResponseBodyDto<Object>> handleMissingRequestHeaderException(
      MissingRequestHeaderException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put(exception.getHeaderName(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserExistedException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleUserExistedException(
      UserExistedException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("email", "Email has been registered");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoSuchElementException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleNoSuchElementException(
      NoSuchElementException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("id", "Resources doesn't exist");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UpperNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleUpperNotFoundException(
      UpperNotFoundException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("upperId", "upper not found");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleCategoryNotFoundException(
      CategoryNotFoundException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("categoryId", "category not found");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SportNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleSportNotFoundException(
      SportNotFoundException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("sportId", "sport not found");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SizeNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleSizeNotFoundException(
      SizeNotFoundException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("sizeId", "size not found");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ProductNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleProductNotFoundException(
      ProductNotFoundException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("productId", "product not found");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CollectionNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleCollectionNotFoundException(
      CollectionNotFoundException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("collectionId", "collection not found");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(GenderNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleGenderNotFoundException(
      GenderNotFoundException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("genderId", "gender not found");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MidsoleNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleMidsoleNotFoundException(
      MidsoleNotFoundException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("midsoleId", "midsole not found");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TechnologyNotFoundException.class)
  protected ResponseEntity<ResponseBodyDto<Object>> handleTechnologyNotFoundException(
      TechnologyNotFoundException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("technologyId", "technology not found");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseBodyDto<Object>> handleGlobalException(
      Exception exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("error", "Bad request");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserBlockedException.class)
  public ResponseEntity<ResponseBodyDto<Object>> handleGlobalException(
      UserBlockedException exception, WebRequest request) {
    ResponseBodyDto<Object> response = new ResponseBodyDto<>();

    response.getErrors().put("UserId", "User has been blocked");

    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }
}
