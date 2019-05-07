package br.com.wcorrea.transport.api.repository;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

public class QueryFiltroPadrao {

    @Getter
    @Setter
    private String filtroGlobal;

    @Getter
    @Setter
    private String campoOrdenacao;

    @Setter
    private String ordemClassificacao;

    @Getter
    @Setter
    private boolean somenteAtivo;

    public String getOrdemClassificacao() {
        return StringUtils.isEmpty(ordemClassificacao) ? "ASC" : ordemClassificacao.toUpperCase();
    }
}
