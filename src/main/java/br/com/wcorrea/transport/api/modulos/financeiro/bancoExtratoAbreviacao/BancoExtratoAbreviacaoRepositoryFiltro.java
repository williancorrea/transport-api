package br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BancoExtratoAbreviacaoRepositoryFiltro extends QueryFiltroPadrao {

    private String inativo;
}
