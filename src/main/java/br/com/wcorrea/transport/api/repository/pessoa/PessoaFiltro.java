package br.com.wcorrea.transport.api.repository.pessoa;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PessoaFiltro extends QueryFiltroPadrao {

    private String nome;
    private boolean motorista;
    private boolean representanteComercialRosinhaTransportes;
    private boolean empresaRosinhaTransportes;
}
