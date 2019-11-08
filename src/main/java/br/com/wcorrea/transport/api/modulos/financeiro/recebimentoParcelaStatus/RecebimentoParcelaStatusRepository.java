package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus;

import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus.RecebimentoParcelaStatus;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus.RecebimentoParcelaStatusRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecebimentoParcelaStatusRepository extends JpaRepository<RecebimentoParcelaStatus, Long>, RecebimentoParcelaStatusRepositoryQuery {

}
