-- -------------------------------------
-- Create table to TYPE OF RELATIONSHIP
-- -------------------------------------

CREATE TABLE type_of_relationship (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    code CHAR(3),
    name VARCHAR(150) NOT NULL,
    description TEXT,
    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


INSERT INTO type_of_relationship (code, name, description) VALUES ('101', 'Conjugue ou Companheiro', 'Companheiro(a) com quem o contribuinte tenha filho ou viva há mais de 5 anos, ou cônjuge');
INSERT INTO type_of_relationship (code, name, description) VALUES ('102', 'Filho ou enteado ate 21 anos', ' Filho(a) ou enteado(a), até 21 anos de idade, ou, em qualquer idade, quando incapacitado física ou mentalmente para o trabalho');
INSERT INTO type_of_relationship (code, name, description) VALUES ('103', 'Filho ou enteado ate 24 anos estudante', ' Filho(a) ou enteado(a), se ainda estiverem cursando estabelecimento de ensino superior ou escola técnica de segundo grau, até 24 anos de idade');
INSERT INTO type_of_relationship (code, name, description) VALUES ('104', 'Irmão, Neto ou Bisneto até 21 anos', ' Irmão(ã), neto(a) ou bisneto(a), sem arrimo dos pais, de quem o contribuinte detenha a guarda judicial, até 21 anos, ou em qualquer idade, quando incapacitado física ou mentalmente para o trabalho');
INSERT INTO type_of_relationship (code, name, description) VALUES ('105', 'Irmão, Neto ou Bisneto até 24 anos', ' Irmão(ã), neto(a) ou bisneto(a), sem arrimo dos pais, com idade de 21 anos até 24 anos, se ainda estiver cursando estabelecimento de ensino superior ou escola técnica de segundo grau, desde que o contribuinte tenha detido sua guarda judicial até os 21 anos');
INSERT INTO type_of_relationship (code, name, description) VALUES ('106', 'Pais, avós e bisavós', ' Pais, avós e bisavós que, em 2010, tenham recebido rendimentos, tributáveis ou não, até R$ 17.989,80');
INSERT INTO type_of_relationship (code, name, description) VALUES ('107', 'Menor pobre com Guarda Judicial', ' Menor pobre até 21 anos que o contribuinte crie e eduque e de quem detenha a guarda judicial');
INSERT INTO type_of_relationship (code, name, description) VALUES ('108', 'Absolutamente incapaz', ' Pessoa absolutamente incapaz, da qual o contribuinte seja tutor ou curador');
