package com.ms.user.ms_user.exception;

import com.ms.user.ms_user.configs.ExceptionConfigs;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class HandleException {
    private final ExceptionConfigs exceptionConfigs;
//    @Value("${control.exception}")
//    private String businessException;

    @ExceptionHandler(MyHandleException.class)
    public ResponseEntity<Object> handleException(MyHandleException ex) {

        return ResponseEntity
                .badRequest()
                .body(String.format(
                        """
                                Business exception: %s
                                """,ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(String.format(
                        """
                                System exception: %s
                                """,ex.getMessage()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> methodArgumentList = new ArrayList<>();
        for(FieldError error: ex.getBindingResult().getFieldErrors()) {
            methodArgumentList.add(error.getObjectName() + ": " + error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity
                .badRequest()
                .body(String.format(
                        """
                                Business exception: %s
                                """,ex.getCause()
                ));
    }
}
