

CREATE TABLE zip_code (
    zip_code VARCHAR(10) PRIMARY KEY,
    address VARCHAR(250),
    neighborhood VARCHAR(250),
    city VARCHAR(250),
    uf VARCHAR(2),
    ibge VARCHAR(20),
    ddd INTEGER,
    longitude VARCHAR(30),
    latitude VARCHAR(30),
    altitude INTEGER
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
