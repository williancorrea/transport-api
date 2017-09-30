package br.com.wcorrea.transport.api.exceptionHandler;

import org.springframework.http.HttpStatus;

/**
 * Class responsible for Manipulating Errors
 */
public class ApiError {
    private int status;
    private String error;
    private String userMessage;
    private String developerMessage;

    public ApiError(String userMessage, String developerMessage, HttpStatus httpStatus) {
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
