package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long>, BankRepositoryQuery {

}