package br.com.wcorrea.transport.api.repository.banco;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
public class BancoFiltro extends QueryFiltroPadrao {

    private String codigo;
    private String nome;
    private String url;
    private String inativo;
}
