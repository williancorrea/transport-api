package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Pessoa;
import br.com.wcorrea.transport.api.repository.filter.PersonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PessoaRepositoryQuery {
    Page<Pessoa> findAll(PersonFilter personFilter, Pageable pageable);
    Pessoa findOneByCPF(String cpf);
    Pessoa findOneByCNPJ(String cnpj);
}
