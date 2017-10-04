

CREATE TABLE zip_code (
    zip_code VARCHAR(10) PRIMARY KEY,
    address VARCHAR(250),
    complement VARCHAR(150),
    neighborhood VARCHAR(250),
    locality VARCHAR(250),
    uf VARCHAR(2),
    unit VARCHAR(250),
    ibge INTEGER,
    gia VARCHAR(250)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


# INSERT INTO zip_code (zip_code, address, complement, neighborhood, locality, uf, unit, ibge, gia) VALUES ('80330-190', 'Rua Joaquim Caetano da Silva', '', 'Santa Quitéria', 'Curitiba', 'PR', '', 4106902, '');'