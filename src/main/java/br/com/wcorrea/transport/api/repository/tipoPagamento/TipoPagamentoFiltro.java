package br.com.wcorrea.transport.api.repository.tipoPagamento;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;


public class TipoPagamentoFiltro extends QueryFiltroPadrao{

    @Getter
    @Setter
    private String descricao;
}
