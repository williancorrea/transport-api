package br.com.wcorrea.transport.api.modulos.financeiro.chequeRecebido;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChequeRecebidoRepositoryFiltro extends QueryFiltroPadrao {
    private String inativo;
}
