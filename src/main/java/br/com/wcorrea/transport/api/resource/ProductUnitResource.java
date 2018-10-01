package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.ProductUnit;
import br.com.wcorrea.transport.api.repository.ProductUnitRepository;
import br.com.wcorrea.transport.api.repository.filter.ProductUnitFilter;
import br.com.wcorrea.transport.api.service.ProductUnitService;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    public Page<ProductUnit> findAll(ProductUnitFilter productUnitFilter, Pageable pageable) {
        return productUnitRepository.findAll(productUnitFilter, pageable);
    }

    /**
     * Retrieve a specific product unit
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LIST_PRODUCT-UNIT') and #oauth2.hasScope('read')")
    public ResponseEntity<ProductUnit> findOne(@Valid @PathVariable String key) {
        ProductUnit productUnitFound = productUnitRepository.findOne(productUnitService.buscarPorKey(key));
        return productUnitFound != null ? ResponseEntity.ok(productUnitFound) : ResponseEntity.notFound().build();
    }

    /**
     * Save a new product unit
     *
     * @param productUnit Product Unit
     * @param response    HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_PRODUCT-UNIT') and #oauth2.hasScope('write')")
    public ResponseEntity<ProductUnit> save(@Valid @RequestBody ProductUnit productUnit, HttpServletResponse response) {
        ProductUnit salvedProductUnit = productUnitRepository.saveAndFlush(productUnit);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedProductUnit.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedProductUnit);
    }

    /**
     * Update product unit
     *
     * @param productUnit ProductUnit
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_PRODUCT-UNIT') and #oauth2.hasScope('write')")
    public ResponseEntity<ProductUnit> update(@Valid @PathVariable String key, @Valid @RequestBody ProductUnit productUnit) {
        return ResponseEntity.status(HttpStatus.OK).body(productUnitService.update(productUnitService.buscarPorKey(key), productUnit));
    }

    /**
     * Delete product unit
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_PRODUCT-UNIT') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        productUnitRepository.delete(productUnitService.buscarPorKey(key));
    }
}
