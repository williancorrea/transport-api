package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.ProductUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductUnitRepository extends JpaRepository<ProductUnit, Long>, ProductUnitRepositoryQuery {
}
