package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoTipo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecebimentoTipoRepository extends JpaRepository<RecebimentoTipo, Long>, RecebimentoTipoRepositoryQuery {

}
