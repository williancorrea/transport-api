CREATE TABLE centro_de_custo(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao varchar(250) NOT NULL,
    inativo BOOLEAN DEFAULT FALSE,

    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    classe_despeza_id BIGINT(20) NOT NULL,

    FOREIGN KEY (classe_despeza_id) REFERENCES classe_despeza(id)
)
    ENGINE = InnoDB DEFAULT CHARSET = utf8;