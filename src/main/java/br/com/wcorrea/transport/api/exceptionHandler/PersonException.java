package br.com.wcorrea.transport.api.exceptionHandler;

import br.com.wcorrea.transport.api.exceptionHandler.defaultException.ApiError;
import br.com.wcorrea.transport.api.exceptionHandler.defaultException.DefaultExceptionHandler;
import br.com.wcorrea.transport.api.service.exception.*;
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
public class PersonException extends DefaultExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({PessoaNaoEncontrada.class})
    public ResponseEntity<Object> handlePessoaNaoEncontrada(PessoaNaoEncontrada ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.pessoa-nao-encontrada", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_FOUND));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler({PessoaFisicaNaoEncontrada.class})
    public ResponseEntity<Object> handlePessoaFisicaNaoEncontrada(PessoaFisicaNaoEncontrada ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.pessoa-fisica-cpf-nao-encontrado", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_FOUND));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({PessoaFisicaJaCadastrada.class})
    public ResponseEntity<Object> handlePessoaFisicaJaCadastrada(PessoaFisicaJaCadastrada ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.pessoa-fisica-ja-cadastrada", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.CONFLICT));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({PessoaFisicaCPFInvalido.class})
    public ResponseEntity<Object> handlePessoaFisicaCPFInvalido(PessoaFisicaCPFInvalido ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.pessoa-fisica-cpf-invalido", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({PessoaJuridicaNaoEncontrada.class})
    public ResponseEntity<Object> handlePessoaJuridicaNaoEncontrada(PessoaJuridicaNaoEncontrada ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.recuros.handlePessoaJuridicaNaoEncontrada", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_FOUND));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({PessoaJuridicaJaCadastrada.class})
    public ResponseEntity<Object> handlePessoaJuridicaJaCadastrada(PessoaJuridicaJaCadastrada ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.pessoa-juridica-ja-cadastrada", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.CONFLICT));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({PessoaJuridicaCNPJInvalido.class})
    public ResponseEntity<Object> handlePessoaFisicaCPFInvalido(PessoaJuridicaCNPJInvalido ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.pessoa-juridica-cnpj-invalido", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({PessoaFisicaNaoPodeAlterarValorCPF.class})
    public ResponseEntity<Object> handlePessoaFisicaNaoPodeAlterarValorCPF(PessoaFisicaNaoPodeAlterarValorCPF ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.pessoa-fisica-cpf-nao-pode-ser-alterado", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({PessoaJuridicaNaoPodeAlterarValorCNPJ.class})
    public ResponseEntity<Object> handlePessoaJuridicaNaoEncontrada(PessoaJuridicaNaoPodeAlterarValorCNPJ ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.pessoa-juridica-cnpj-nao-pode-ser-alterado", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }
}
