package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ZipCodeRepository extends JpaRepository<ZipCode, String> {

    //TODO REMOVER DEPOIS DO CLONE DO VIA CEP
    @Query(value = "select z.cep from zip_code z ORDER BY z.cep DESC LIMIT 1", nativeQuery = true)
    String findByLastCep();
}
