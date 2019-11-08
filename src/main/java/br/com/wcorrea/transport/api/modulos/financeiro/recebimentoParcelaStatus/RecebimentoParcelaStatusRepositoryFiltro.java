package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecebimentoParcelaStatusRepositoryFiltro extends QueryFiltroPadrao {
    private String inativo;
}
