CREATE TABLE pessoa_juridica (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

    cnpj VARCHAR(18) NOT NULL,
    rg VARCHAR(15),

    fantasia VARCHAR(250),
    inscricao_municipal VARCHAR(50),
    inscricao_estadual VARCHAR(50),
    dataConstituicao DATE,

    tipoRegime VARCHAR(20),

    -- CÓDIGO DE REGIME TRIBUTÁRIO: SIMPLES NACIONAL ....
    crt VARCHAR(50),
    suframa VARCHAR(50),

    pessoa_id BIGINT(20),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;