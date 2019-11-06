package br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao;

import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacao;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacaoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoExtratoAbreviacaoRepository extends JpaRepository<BancoExtratoAbreviacao, Long>, BancoExtratoAbreviacaoRepositoryQuery {

}
