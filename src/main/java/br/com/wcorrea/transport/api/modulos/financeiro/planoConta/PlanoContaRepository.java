package br.com.wcorrea.transport.api.modulos.financeiro.planoConta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoContaRepository extends JpaRepository<PlanoConta, Long>, PlanoContaRepositoryQuery {

}
