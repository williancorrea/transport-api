package br.com.wcorrea.transport.api.repository.controleKm;

import br.com.wcorrea.transport.api.model.ControleKm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControleKmRepository extends JpaRepository<ControleKm, Long>, ControleKmRepositoryQuery {
}
