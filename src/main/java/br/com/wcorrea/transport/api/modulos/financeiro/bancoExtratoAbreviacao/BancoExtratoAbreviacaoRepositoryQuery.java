package br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao;

import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacao;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacaoRepositoryFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BancoExtratoAbreviacaoRepositoryQuery {
    Page<BancoExtratoAbreviacao> findAll(BancoExtratoAbreviacaoRepositoryFiltro filtro, Pageable pageable);
}
