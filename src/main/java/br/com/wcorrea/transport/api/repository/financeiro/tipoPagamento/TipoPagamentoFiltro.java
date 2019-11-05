package br.com.wcorrea.transport.api.repository.financeiro.tipoPagamento;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;


public class TipoPagamentoFiltro extends QueryFiltroPadrao{

    @Getter
    @Setter
    private String descricao;
}
