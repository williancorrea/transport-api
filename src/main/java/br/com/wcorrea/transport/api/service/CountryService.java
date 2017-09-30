package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Country;
import br.com.wcorrea.transport.api.repository.CountryRepository;
import br.com.wcorrea.transport.api.service.exception.CountryUpdateNotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class responsible for performing the entire business rule by manipulating country information
 */
@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Update country
     *
     * @param id
     * @param country
     * @return
     */
    public Country update(Long id, Country country) {
        Country updateFound = countryRepository.save(findById(id));
        BeanUtils.copyProperties(country, updateFound, "country_id");
        return countryRepository.save(updateFound);
    }

    /**
     * Find country by id
     */
    private Country findById(Long id) {
        Country country = countryRepository.findOne(id);
        if (country == null) {
            throw new CountryUpdateNotFound();
        }
        return country;
    }
}
