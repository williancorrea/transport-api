package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaDetalhe;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecebimentoParcelaDetalheRepositoryFiltro extends QueryFiltroPadrao {
    private String inativo;
}
