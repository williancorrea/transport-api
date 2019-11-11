package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecebimentoLancamentoRepository extends JpaRepository<RecebimentoLancamento, Long>, RecebimentoLancamentoRepositoryQuery {

}
