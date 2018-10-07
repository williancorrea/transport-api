package br.com.wcorrea.transport.api.exceptionHandler;

import br.com.wcorrea.transport.api.exceptionHandler.defaultException.DefaultExceptionHandler;
import br.com.wcorrea.transport.api.service.exception.CentroDeCustoNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.ClasseDespezaNaoEncontrada;
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
 * ClasseDespesa responsavel por manipular todas as excessoes que ocorrerem nas regras de negocio da contabilidade
 */
@ControllerAdvice
public class ContabilidadeException extends DefaultExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ClasseDespezaNaoEncontrada.class})
    public ResponseEntity<Object> handleClasseNotFound(ClasseDespezaNaoEncontrada ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.classe_despeza_nao_encontrada", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_FOUND));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({CentroDeCustoNaoEncontrado.class})
    public ResponseEntity<Object> handleCentroDeCustoNotFound(CentroDeCustoNaoEncontrado ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.centro_de_custo_nao_encontrado", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_FOUND));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
