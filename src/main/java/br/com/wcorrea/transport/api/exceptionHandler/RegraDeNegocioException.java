package br.com.wcorrea.transport.api.exceptionHandler;

import br.com.wcorrea.transport.api.exceptionHandler.defaultException.ApiError;
import br.com.wcorrea.transport.api.exceptionHandler.defaultException.DefaultExceptionHandler;
import br.com.wcorrea.transport.api.service.exception.RegraDeNegocio;
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
public class RegraDeNegocioException extends DefaultExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({RegraDeNegocio.class})
    public ResponseEntity<Object> handleRegraDeNegocio(RegraDeNegocio ex, WebRequest request, Locale loc) {
        String userMessage = "";
        try {
            userMessage = messageSource.getMessage(ex.getMessage(), null, loc); // procura a mensagem no arquivo de recurso
        } catch (Exception e) {
            userMessage = ex.getMessage();
        }

        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.BAD_REQUEST));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
