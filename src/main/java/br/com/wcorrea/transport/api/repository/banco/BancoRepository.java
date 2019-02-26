package br.com.wcorrea.transport.api.repository.banco;

import br.com.wcorrea.transport.api.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long>, BancoRepositoryQuery {

}