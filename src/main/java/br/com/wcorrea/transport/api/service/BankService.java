package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Bank;
import br.com.wcorrea.transport.api.repository.BankRepository;
import br.com.wcorrea.transport.api.service.exception.BankNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

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
        Bank updateFound = findOne(id);
        bank.setId(updateFound.getId());
        bank.setProperties(updateFound.getProperties());
        bank.getProperties().setDataAlteracao(new Date());
        return bankRepository.save(bank);
    }

    /**
     * Find bank by id
     */
    public Bank findOne(Long id) {
        Bank bank = bankRepository.findOne(id);
        if (bank == null) {
            throw new BankNotFound();
        }
        return bank;
    }

}
