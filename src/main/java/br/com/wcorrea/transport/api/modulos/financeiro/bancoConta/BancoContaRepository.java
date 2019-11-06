package br.com.wcorrea.transport.api.modulos.financeiro.bancoConta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoContaRepository extends JpaRepository<BancoConta, Long>, BancoContaRepositoryQuery {

}
