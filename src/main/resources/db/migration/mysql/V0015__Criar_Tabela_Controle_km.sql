CREATE TABLE controle_km (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_hora_saida TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_hora_chegada TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    origem VARCHAR(150) NOT NULL,
    destino VARCHAR(150) NOT NULL,
    km_saida BIGINT(20) NOT NULL,
    km_chegada BIGINT(20) NOT NULL,
    obs TEXT,

    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    veiculo_id BIGINT(20),
    pessoa_id BIGINT(20),
    itinerario_id BIGINT(20),

    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    FOREIGN KEY (itinerario_id) REFERENCES itinerario(id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;