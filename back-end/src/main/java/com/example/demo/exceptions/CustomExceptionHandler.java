package com.example.demo.exceptions;

import com.example.demo.dto.responses.ResponseBodyDTO;
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
    public ResponseEntity<Object> handleUsernamePasswordInvalidException(UsernamePasswordInvalidException exception, WebRequest webRequest) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("credentials", "Invalid username or password");

        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException exception, WebRequest webRequest) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("JWT token", "Invalid token");

        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            response.getErrors().put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("user", "User not found");

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put(exception.getHeaderName(), exception.getMessage());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistedException.class)
    protected ResponseEntity<Object> handleUserExistedException(UserExistedException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("email", "Email has been registered");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("id", "Resources doesn't exist");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UpperNotFoundException.class)
    protected ResponseEntity<Object> handleUpperNotFoundException(UpperNotFoundException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("upperId", "upper not found");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    protected ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("categoryId", "category not found");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SportNotFoundException.class)
    protected ResponseEntity<Object> handleSportNotFoundException(SportNotFoundException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("sportId", "sport not found");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SizeNotFoundException.class)
    protected ResponseEntity<Object> handleSizeNotFoundException(SizeNotFoundException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("sizeId", "size not found");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("productId", "product not found");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CollectionNotFoundException.class)
    protected ResponseEntity<Object> handleCollectionNotFoundException(CollectionNotFoundException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("collectionId", "collection not found");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenderNotFoundException.class)
    protected ResponseEntity<Object> handleGenderNotFoundException(GenderNotFoundException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("genderId", "gender not found");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MidsoleNotFoundException.class)
    protected ResponseEntity<Object> handleMidsoleNotFoundException(MidsoleNotFoundException exception, WebRequest request) {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("midsoleId", "midsole not found");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
