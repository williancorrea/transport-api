package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus;

import br.com.wcorrea.transport.api.exceptionHandler.defaultException.ApiError;
import br.com.wcorrea.transport.api.exceptionHandler.defaultException.DefaultExceptionHandler;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus.RecebimentoParcelaStatusExceptionNaoEncontrado;
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
 * Classe responsavel por manipular todos os erros da classe de RecebimentoParcelaStatuss
 */
@ControllerAdvice
public class RecebimentoParcelaStatusExceptionHandler extends DefaultExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({RecebimentoParcelaStatusExceptionNaoEncontrado.class})
    public ResponseEntity<Object> handleBankUpdateNotFound(RecebimentoParcelaStatusExceptionNaoEncontrado ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.recebimento-parcela-status-nao-encontrado", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_FOUND));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
