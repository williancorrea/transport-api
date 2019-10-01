CREATE TABLE pessoa_fisica (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

    cpf VARCHAR(15) NOT NULL,
    rg VARCHAR(15),

    orgao_rg VARCHAR(6),
    data_emissao_rg  DATE,
    data_nascimento DATE,
    sexo VARCHAR(1),
    naturalidade VARCHAR(250),
    nacionalidade VARCHAR(250),
    tipo_sangue VARCHAR(5),

    cnh_numero VARCHAR(30),
    cnh_categoria VARCHAR(2),
    cnh_vencimento DATE NULL,
    cnh_primeira_habilitacao DATE NULL,
    cnh_emissao_data DATE NULL,
    cnh_emissao_cidade_id BIGINT(20), FOREIGN KEY (cnh_emissao_cidade_id) REFERENCES cidade(id),
    inativo_motorista BOOLEAN DEFAULT TRUE,

    titulo_eleitoral_numero VARCHAR(30),
    titulo_eleitoral_zona VARCHAR(3),
    titulo_eleitoral_secao VARCHAR(10),

    reservista_numero VARCHAR(30),
    reservista_categoria VARCHAR(50),

    nome_mae VARCHAR(250),
    nome_pai VARCHAR(250),

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    estado_civil_id BIGINT(20),
    pessoa_id BIGINT(20),

    FOREIGN KEY (estado_civil_id) REFERENCES estado_civil(id),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
