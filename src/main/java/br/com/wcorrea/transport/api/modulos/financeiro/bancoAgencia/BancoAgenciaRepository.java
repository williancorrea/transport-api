package br.com.wcorrea.transport.api.modulos.financeiro.bancoAgencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoAgenciaRepository extends JpaRepository<BancoAgencia, Long>, BancoAgenciaRepositoryQuery {

}
