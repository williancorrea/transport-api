package br.com.wcorrea.transport.api.repository.pessoa;

import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PessoaRepositoryQuery {
    Page<Pessoa> findAll(PessoaFiltro pessoaFiltro, Pageable pageable);

    Pessoa findOneByCPF(String cpf);

    Pessoa findOneByCNPJ(String cnpj);

    Boolean verificarCPFJaCadastrado(String cpf, Long id);

    Boolean verificarCNPJJaCadastrado(String cnpj, Long id);
}
