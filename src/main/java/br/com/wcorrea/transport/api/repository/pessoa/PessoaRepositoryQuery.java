package br.com.wcorrea.transport.api.repository.pessoa;

import br.com.wcorrea.transport.api.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PessoaRepositoryQuery {
    Page<Pessoa> findAll(PessoaFiltro pessoaFiltro, Pageable pageable);
    Pessoa findOneByCPF(String cpf);
    Pessoa findOneByCNPJ(String cnpj);
}
