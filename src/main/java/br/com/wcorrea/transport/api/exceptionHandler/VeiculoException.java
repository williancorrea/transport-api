package br.com.wcorrea.transport.api.exceptionHandler;

import br.com.wcorrea.transport.api.exceptionHandler.defaultException.DefaultExceptionHandler;
import br.com.wcorrea.transport.api.service.exception.veiculo.*;
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
public class VeiculoException extends DefaultExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({VeiculoNaoEncontrado.class})
    public ResponseEntity<Object> handleVeiculoUpdateNotFound(VeiculoNaoEncontrado ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.veiculo-nao-encontrado", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_FOUND));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ControleKmNaoEncontrado.class})
    public ResponseEntity<Object> handleConroleKmNaoEncontrado(ControleKmNaoEncontrado ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.controle_km-nao-encontrado", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_FOUND));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ControleKmSaidaInvalido.class})
    public ResponseEntity<Object> handleControleKmSaidaInvalido(ControleKmSaidaInvalido ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.controle_km_saida_invalido", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({ControleKmChegadaInvalido.class})
    public ResponseEntity<Object> handleControleKmChegadaInvalido(ControleKmChegadaInvalido ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.controle_km_chegada_invalido", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({ControleKmPeriodoEntreDatasInvalidos.class})
    public ResponseEntity<Object> handleControleKmPeriodoEntreDatasInvalidos(ControleKmPeriodoEntreDatasInvalidos ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.controle_km_periodo_entre_datas_invalidos", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({ControleKmKmSaidaNaoPodeSerMaiorKmChegada.class})
    public ResponseEntity<Object> handleControleKmPeriodoEntreDatasInvalidos(ControleKmKmSaidaNaoPodeSerMaiorKmChegada ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.controle_km_km_saida_nao_pode_ser_maior_km_chegada", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({ControleKmPeriodoInvalidoDeEntradaKmSaida.class})
    public ResponseEntity<Object> handleControleKmPeriodoInvalidoDeEntradaKmSaida(ControleKmPeriodoInvalidoDeEntradaKmSaida ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.controle_km_periodo_invalido_entrada_km_saida", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({ControleKmPeriodoInvalidoDeEntradaKmChegada.class})
    public ResponseEntity<Object> handleControleKmPeriodoInvalidoDeEntradaKmChegada(ControleKmPeriodoInvalidoDeEntradaKmChegada ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.controle_km_periodo_invalido_entrada_km_chegada", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({ControleKmPeriodoInvalidoDeEntradaDataSaida.class})
    public ResponseEntity<Object> handleControleKmPeriodoInvalidoDeEntradaDataSaida(ControleKmPeriodoInvalidoDeEntradaDataSaida ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.controle_km_periodo_invalido_entrada_data_saida", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler({ControleKmPeriodoInvalidoDeEntradaDataChegada.class})
    public ResponseEntity<Object> handleControleKmPeriodoInvalidoDeEntradaDataChegada(ControleKmPeriodoInvalidoDeEntradaDataChegada ex, WebRequest request, Locale loc) {
        String userMessage = messageSource.getMessage("recurso.controle_km_periodo_invalido_entrada_data_chegada", null, loc);
        String developerMessage = ex.toString();
        List<ApiError> errors = Arrays.asList(new ApiError(userMessage, developerMessage, HttpStatus.NOT_ACCEPTABLE));
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

}
