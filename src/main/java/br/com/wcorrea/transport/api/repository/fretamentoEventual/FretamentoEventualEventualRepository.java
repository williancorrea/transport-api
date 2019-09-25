package br.com.wcorrea.transport.api.repository.fretamentoEventual;

import br.com.wcorrea.transport.api.model.fretamento.FretamentoEventual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FretamentoEventualEventualRepository extends JpaRepository<FretamentoEventual, Long>, FretamentoEventualRepositoryQuery {

}
