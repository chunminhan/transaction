package com.interview.transaction.exception;

import com.interview.transaction.response.TransactionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class TransactionGlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        BindingResult result = ex.getBindingResult();
        if(result.hasErrors()){
            List<ObjectError> validationErrors = result.getAllErrors();
            return new ResponseEntity<>(TransactionResponse.badRequest(validationErrors.get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(TransactionResponse.badRequest("Unknown Validation Error"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public TransactionResponse<String> parameterTypeException(HttpMessageConversionException httpMessageConversionException, WebRequest webRequest){
        return TransactionResponse.badRequest(httpMessageConversionException.getCause().getLocalizedMessage());
    }

    @ExceptionHandler(TransactionException.EntityNotFoundException.class)
    public TransactionResponse<String> entityNotFoundException(Exception ex){
        return TransactionResponse.entityNotFound(ex.getMessage());
    }

    @ExceptionHandler(TransactionException.DuplicateEntityException.class)
    public TransactionResponse<String> entityDuplicateException(Exception ex){
        return TransactionResponse.entityDuplicate(ex.getMessage());
    }

}
