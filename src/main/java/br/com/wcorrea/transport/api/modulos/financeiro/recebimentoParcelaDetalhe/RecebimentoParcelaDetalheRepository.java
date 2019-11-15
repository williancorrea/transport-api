package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaDetalhe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecebimentoParcelaDetalheRepository extends JpaRepository<RecebimentoParcelaDetalhe, Long>, RecebimentoParcelaDetalheRepositoryQuery {

}
