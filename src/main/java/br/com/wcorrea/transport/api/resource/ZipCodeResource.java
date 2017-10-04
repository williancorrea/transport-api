package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.model.ZipCode;
import br.com.wcorrea.transport.api.repository.ZipCodeRepository;
import br.com.wcorrea.transport.api.service.ZipCodeService;
import br.com.wcorrea.transport.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Class responsible for providing all the resources needed to handle type of zip code
 */
@RestController
@RequestMapping("/zip-code")
public class ZipCodeResource {
    @Autowired
    private ZipCodeRepository zipCodeRepository;

    @Autowired
    private ZipCodeService zipCodeService;

    /**
     * Retrieve a specific zip code
     *
     * @param cep type of relationship id
     * @return
     */
    @GetMapping("/{cep}")
    @PreAuthorize("hasAuthority('ROLE_LIST_ZIP-CODE') and #oauth2.hasScope('read')")
    public ResponseEntity<ZipCode> findOne(@Valid @PathVariable String cep) {
        ZipCode zipCode = zipCodeService.findByZipCode(cep);
        return zipCode != null ? ResponseEntity.ok(zipCode) : ResponseEntity.notFound().build();
    }


    //TODO: REMOVER DEPOIS DO CLONE DO VIA CEP
    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_LIST_ZIP-CODE') and #oauth2.hasScope('read')")
    public ResponseEntity<ZipCode> findViaCEP() {
        String cont = zipCodeRepository.findByLastCep();
        Integer contador = 0;
        if (cont == null) {
            contador = 1001000;
        }else{
            contador = Integer.parseInt(cont.replace("-", ""));
        }
        for (int i = contador; i < 99999999; i++) {
            String item = new Utils().StrZeroEsquerda("" + i, 8);
            System.out.println("Consultando cep: " + item.substring(0, 5) + "-" + item.substring(5, 8));
            ZipCode z = zipCodeService.findByZipCode(item.substring(0, 5) + "-" + item.substring(5, 8));
            if(z==null){
                System.out.println("NAO ACHOU --- YEH YEH");
            }else{
                System.out.println("\n\n\nACHOUUUUUU\n\n\n");
            }
        }

        return ResponseEntity.notFound().build();
    }
}