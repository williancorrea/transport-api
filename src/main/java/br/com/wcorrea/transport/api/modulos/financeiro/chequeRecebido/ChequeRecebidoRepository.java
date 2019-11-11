package br.com.wcorrea.transport.api.modulos.financeiro.chequeRecebido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRecebidoRepository extends JpaRepository<ChequeRecebido, Long>, ChequeRecebidoRepositoryQuery {

}
