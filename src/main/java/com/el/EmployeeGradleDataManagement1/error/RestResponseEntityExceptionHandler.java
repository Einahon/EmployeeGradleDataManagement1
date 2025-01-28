package com.el.EmployeeGradleDataManagement1.error;

import com.el.EmployeeGradleDataManagement1.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler({EmployeeNotFoundException.class})
    public ResponseEntity<ErrorMessage> employeeNotFoundException(EmployeeNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
@ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> employeeFieldValidationError(MethodArgumentNotValidException exception){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(HttpStatus.BAD_REQUEST,
            exception.getBindingResult().getFieldError().getDefaultMessage()));
}
}


