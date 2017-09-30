package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ProductUnit;
import br.com.wcorrea.transport.api.repository.ProductUnitRepository;
import br.com.wcorrea.transport.api.service.exception.ProductUnitUpdateNotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ProductUnit updateFound = productUnitRepository.save(findById(id));
        BeanUtils.copyProperties(productUnit, updateFound, "id");
        return productUnitRepository.save(updateFound);
    }

    /**
     * Find product unit by id
     */
    private ProductUnit findById(Long id) {
        ProductUnit productUnit = productUnitRepository.findOne(id);
        if (productUnit == null) {
            throw new ProductUnitUpdateNotFound();
        }
        return productUnit;
    }
}
