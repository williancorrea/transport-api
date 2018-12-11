package br.com.wcorrea.transport.api.repository;

import lombok.Getter;
import lombok.Setter;

public class QueryFiltroPadrao {

    @Getter
    @Setter
    private String filtroGlobal;

    @Getter
    @Setter
    private String propriedadeOrdenacao;

    @Getter
    @Setter
    private String ordemClassificacao;
}
