package br.com.wcorrea.transport.api.repository.pessoa;

import lombok.Getter;
import lombok.Setter;

public class PessoaFiltro {

    @Getter
    @Setter
    private String globalFilter;

    @Getter
    @Setter
    private String sortField;

    @Getter
    @Setter
    private String sortOrder;

    @Getter
    @Setter
    private String name;
}
