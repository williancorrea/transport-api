package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcela;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecebimentoParcelaRepository extends JpaRepository<RecebimentoParcela, Long>, RecebimentoParcelaRepositoryQuery {

}
