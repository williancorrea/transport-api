package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.ProductUnit;
import br.com.wcorrea.transport.api.repository.ProductUnitRepository;
import br.com.wcorrea.transport.api.service.ProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Class responsible for providing all the resources needed to handle product unit trash
 */
@RestController
@RequestMapping("/product-units")
public class ProductUnitResource {

    @Autowired
    private ProductUnitRepository productUnitRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ProductUnitService productUnitService;

    /**
     * Retrieve the list of registered product units
     *
     * @return List of product units
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST_PRODUCT-UNIT') and #oauth2.hasScope('read')")
    public List<ProductUnit> listAllProductUnit() {
        return productUnitRepository.findAll();
    }

    /**
     * Retrieve a specific product unit
     *
     * @param id country id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_LIST_PRODUCT-UNIT') and #oauth2.hasScope('read')")
    public ResponseEntity<ProductUnit> findOneProductUnit(@Valid @PathVariable Long id) {
        ProductUnit productUnit = productUnitRepository.findOne(id);
        return productUnit != null ? ResponseEntity.ok(productUnit) : ResponseEntity.notFound().build();
    }

    /**
     * Save a new product unit
     *
     * @param productUnit  Product Unit
     * @param response HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_PRODUCT-UNIT') and #oauth2.hasScope('write')")
    public ResponseEntity<ProductUnit> save(@Valid @RequestBody ProductUnit productUnit, HttpServletResponse response) {
        ProductUnit salvedProductUnit = productUnitRepository.save(productUnit);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedProductUnit.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedProductUnit);
    }

    /**
     * Update product unit
     *
     * @param productUnit  ProductUnit
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_PRODUCT-UNIT') and #oauth2.hasScope('write')")
    public ResponseEntity<ProductUnit> update(@Valid @PathVariable Long id, @Valid @RequestBody ProductUnit productUnit) {
        ProductUnit updateProductUnit = productUnitService.update(id, productUnit);
        return ResponseEntity.status(HttpStatus.OK).body(updateProductUnit);
    }

    /**
     * Delete product unit
     *
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_PRODUCT-UNIT') and #oauth2.hasScope('write')")
    public void delete(@PathVariable Long id) {
        productUnitRepository.delete(id);
    }
}
