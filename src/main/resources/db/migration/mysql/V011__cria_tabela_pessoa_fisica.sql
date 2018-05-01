CREATE TABLE pessoa_fisica (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

    cpf VARCHAR(15) NOT NULL,
    rg VARCHAR(15),

    orgao_rg VARCHAR(6),
    data_emissao_rg  DATE,
    data_nascimento DATE,
    sexo VARCHAR(1) NOT NULL,
    naturalidade VARCHAR(250),
    nacionalidade VARCHAR(250),
    raca VARCHAR(150),
    tipo_sangue VARCHAR(5),

    cnh_numero VARCHAR(30),
    cnh_categoria VARCHAR(2),
    cnh_vencimento DATE,

    titulo_eleitoral_numero VARCHAR(30),
    titulo_eleitoral_zona VARCHAR(3),
    titulo_eleitoral_secao VARCHAR(10),

    reservista_numero VARCHAR(30),
    reservista_categoria VARCHAR(50),

    nome_mae VARCHAR(250),
    nome_pai VARCHAR(250),

    estado_civil_id BIGINT(20),
    pessoa_id BIGINT(20),

    FOREIGN KEY (estado_civil_id) REFERENCES marital_status(id),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;