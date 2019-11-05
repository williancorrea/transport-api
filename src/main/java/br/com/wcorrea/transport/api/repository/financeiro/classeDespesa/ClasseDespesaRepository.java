package br.com.wcorrea.transport.api.repository.financeiro.classeDespesa;

import br.com.wcorrea.transport.api.model.ClasseDespesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseDespesaRepository extends JpaRepository<ClasseDespesa, Long>, ClasseDespesaRepositoryQuery {

}
