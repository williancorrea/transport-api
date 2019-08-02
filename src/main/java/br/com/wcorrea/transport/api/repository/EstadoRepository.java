package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.pessoa.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("SELECT t FROM estado t order by descricao")
    List<Estado> listarEstadorPorDescricao();

}
