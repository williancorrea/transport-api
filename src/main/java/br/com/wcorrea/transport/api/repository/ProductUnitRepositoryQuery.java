package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.ProductUnit;
import br.com.wcorrea.transport.api.repository.filter.ProductUnitFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductUnitRepositoryQuery {
    Page<ProductUnit> findAll(ProductUnitFilter productUnitFilter, Pageable pageable);
}
