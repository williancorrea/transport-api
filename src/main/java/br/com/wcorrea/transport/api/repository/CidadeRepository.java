package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("SELECT t FROM cidade t WHERE t.estado.id = ?1 order by t.descricao")
    List<Cidade> findByEstadoId(Long id);

}
