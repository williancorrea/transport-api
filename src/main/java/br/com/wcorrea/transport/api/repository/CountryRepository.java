package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
