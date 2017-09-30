package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Bank;
import br.com.wcorrea.transport.api.repository.BankRepository;
import br.com.wcorrea.transport.api.service.exception.BankUpdateNotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class responsible for performing the entire business rule by manipulating bank information
 */
@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    /**
     * Update bank
     *
     * @param id
     * @param bank
     * @return
     */
    public Bank update(Long id, Bank bank) {
        Bank updateFound = bankRepository.save(findById(id));
        BeanUtils.copyProperties(bank, updateFound, "id");
        return bankRepository.save(updateFound);
    }

    /**
     * Find bank by id
     */
    private Bank findById(Long id) {
        Bank bank = bankRepository.findOne(id);
        if (bank == null) {
            throw new BankUpdateNotFound();
        }
        return bank;
    }

}
