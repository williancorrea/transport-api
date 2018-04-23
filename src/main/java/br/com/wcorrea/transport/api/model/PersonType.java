package br.com.wcorrea.transport.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PersonType {
    PHYSICAL("PHYSICAL"),
    LEGAL("LEGAL");

    private String description;

    PersonType(String descricao) {
        this.description = descricao;
    }

    public String getDescription() {
        return description;
    }
}
