package br.com.wcorrea.transport.api.repository.filter.padrao;

import lombok.Getter;
import lombok.Setter;

public class QueryFiltroPadrao {

    @Getter
    @Setter
    private String filtroGlobal;

    @Getter
    @Setter
    private String sortField;

    @Getter
    @Setter
    private String sortOrder;
}
