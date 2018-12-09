package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ProductUnit;
import br.com.wcorrea.transport.api.repository.ProductUnitRepository;
import br.com.wcorrea.transport.api.service.exception.UnidadeMedidaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Class responsible for performing the entire business rule by manipulating product unit information
 */
@Service
public class ProductUnitService {

    @Autowired
    private ProductUnitRepository productUnitRepository;

    /**
     * Update product unit
     *
     * @param id
     * @param productUnit
     * @return
     */
    public ProductUnit update(Long id, ProductUnit productUnit) {
        ProductUnit updateFound = findOne(id);
        productUnit.setId(updateFound.getId());
        return productUnitRepository.save(productUnit);
    }

    /**
     * Find product unit by id
     */
    private ProductUnit findOne(Long id) {
        ProductUnit productUnit = productUnitRepository.findOne(id);
        if (productUnit == null) {
            throw new UnidadeMedidaNaoEncontrada();
        }
        return productUnit;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new UnidadeMedidaNaoEncontrada();
        }
    }
}
