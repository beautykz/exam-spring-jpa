package com.example.examspringjpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ResourceCanNotBeDeleteException extends RuntimeException {

    public ResourceCanNotBeDeleteException(String message) {
        super(message);
    }
}
