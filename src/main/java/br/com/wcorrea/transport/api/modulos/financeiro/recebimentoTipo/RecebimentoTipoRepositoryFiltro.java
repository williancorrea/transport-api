package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoTipo;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecebimentoTipoRepositoryFiltro extends QueryFiltroPadrao {
    private String inativo;
}
