package br.com.wcorrea.transport.api.repository.controleKm;

import br.com.wcorrea.transport.api.model.ControleKm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;


public interface ControleKmRepositoryQuery {
    Page<ControleKm> findAll(ControleKmFiltro filtro, Pageable paginacao);

    boolean validarPeriodoInvalidoKmSaida(Long controleKmId, Long veiculoId, String kmSaida);

    boolean validarPeriodoInvalidoKmChegada(Long controleKmId, Long veiculoId, String kmChegada);

    boolean validarPeriodoInvalidoDeEntradaDataChegada(ControleKm controleKm);

    boolean validarPeriodoInvalidoDeEntradaDataSaida(ControleKm controleKm);

    Long recuperarKmSaidaMinimo(Date dataSaida, Long veiculoId);

    Long recuperarKmChegadaMaximo(Date dataChegada, Long veiculoId);
}
