package br.com.wcorrea.transport.api.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Embeddable
public class PropriedadesComuns implements Serializable {
    private static final long serialVersionUID = 4575371763430219942L;

    @Column(name = "data_criacao")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    private Date dataCriacao;

    @Column(name = "data_alteracao")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    private Date dataAlteracao;

    public PropriedadesComuns() {
        Date agora = new Date();
        this.dataAlteracao = agora;
        this.dataCriacao = agora;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
