package br.com.wcorrea.transport.api.modulos.financeiro.banco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long>, BancoRepositoryQuery {

}
