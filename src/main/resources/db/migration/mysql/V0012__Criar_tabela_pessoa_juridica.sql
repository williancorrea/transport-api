CREATE TABLE pessoa_juridica (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

    cnpj VARCHAR(18) NOT NULL,

    fantasia VARCHAR(250),
    inscricao_municipal VARCHAR(50),
    inscricao_estadual VARCHAR(50),
    data_constituicao DATE,

    tipo_regime VARCHAR(20),

    -- CÓDIGO DE REGIME TRIBUTÁRIO: SIMPLES NACIONAL ....
    crt VARCHAR(50),
    suframa VARCHAR(50),

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    pessoa_id BIGINT(20),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
