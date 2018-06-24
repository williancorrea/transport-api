package br.com.wcorrea.transport.api.exceptionHandler;

import br.com.wcorrea.transport.api.exceptionHandler.defaultException.DefaultExceptionHandler;
import br.com.wcorrea.transport.api.service.exception.ExceptionDescriptografarKey;
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

@ControllerAdvice
public class CriptografiaException extends DefaultExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ExceptionDescriptografarKey.class})
    public ResponseEntity<Object> handleExceptionDescriptografarKey(ExceptionDescriptografarKey ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.erro_ao_descriptografar", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
