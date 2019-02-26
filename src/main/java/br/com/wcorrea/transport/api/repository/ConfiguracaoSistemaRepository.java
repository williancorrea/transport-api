package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.ConfiguracaoSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConfiguracaoSistemaRepository extends JpaRepository<ConfiguracaoSistema, Long> {

    @Query("SELECT t FROM configuracao_sistema t order by nome")
    List<ConfiguracaoSistema> listarTudoPorNome();

}
