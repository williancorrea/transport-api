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
    altitude INTEGER,
    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01001-000', 'Praça da Sé, lado ímpar', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.636', '-23.5479099981', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01001-001', 'Praça da Sé, lado par', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6359018159', '-23.5479099922', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01001-010', 'Rua Filipe de Oliveira', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6359018226', '-23.548', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01001-900', 'UNESP - Universidade Estadual Júlio de Mesquita Filho, Praça da Sé, 108', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.636', '-23.5478199994', null);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01001-901', 'Edifício Santa Lídia, Praça da Sé, 371', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6358036472', '-23.5478199984', null);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01001-902', 'OAB - Ordem dos Advogados do Brasil., Praça da Sé, 385', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6359018161', '-23.5480900077', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01002-000', 'Rua Direita, lado par', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.636', '-23.5480900029', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01002-001', 'Rua Direita, lado ímpar', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6360981784', '-23.5480900027', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01002-010', 'Praça do Patriarca', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6360981849', '-23.548', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01002-020', 'Viaduto do Chá', 'Centro', 'São Paulo', 'SP', '3550308', 11, '-46.6360981795', '-23.5479099964', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01002-900', 'Prefeitura do Município de São Paulo, Viaduto do Chá, 15', 'Centro', 'São Paulo', 'SP', '3550308', 11, '-46.635803646', '-23.548', null);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01002-901', 'Edifício Piratininga, Rua Direita, 32', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6358036449', '-23.5481800038', null);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01002-902', 'Edifício Zogbi, Rua Direita, 191', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.636', '-23.547819994', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01002-903', 'Edifício Barão de Iguape, Rua Direita, 250', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.636', '-23.5481800044', null);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01003-000', 'Rua José Bonifácio, lado par', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6358036424', '-23.547819994', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01003-001', 'Rua José Bonifácio, lado ímpar', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6358036481', '-23.548', 760);
INSERT INTO transport_api.zip_code (zip_code, address, neighborhood, city, uf, ibge, ddd, longitude, latitude, altitude) VALUES ('01003-010', 'Praça Ouvidor Pacheco e Silva', 'Sé', 'São Paulo', 'SP', '3550308', 11, '-46.6358036455', '-23.5481800032', 760);