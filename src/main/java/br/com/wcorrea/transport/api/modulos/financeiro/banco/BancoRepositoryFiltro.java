package br.com.wcorrea.transport.api.modulos.financeiro.banco;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BancoRepositoryFiltro extends QueryFiltroPadrao {

    private String codigo;
    private String nome;
    private String url;
    private String inativo;
}
