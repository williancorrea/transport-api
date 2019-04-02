package br.com.wcorrea.transport.api.repository.combustivel;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
public class CombustivelFiltro extends QueryFiltroPadrao {

    private String nome;
}
