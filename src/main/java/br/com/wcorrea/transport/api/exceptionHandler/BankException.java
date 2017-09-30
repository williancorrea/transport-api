package br.com.wcorrea.transport.api.exceptionHandler;

import br.com.wcorrea.transport.api.service.exception.BankUpdateNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Class responsible for dealing with all bank errors
 */
@ControllerAdvice
public class BankException extends DefaultExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({BankUpdateNotFound.class})
    public ResponseEntity<Object> handleBankUpdateNotFound(BankUpdateNotFound ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("resource.bank-not-found", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_FOUND));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
