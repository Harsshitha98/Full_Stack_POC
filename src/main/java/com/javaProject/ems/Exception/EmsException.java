package com.javaProject.ems.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class EmsException extends RuntimeException {

    private HttpStatus status;
    private String message;
}
