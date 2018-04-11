CREATE TABLE product_unit (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    initials VARCHAR(10),
    name VARCHAR(150) NOT NULL,
    can_fraction INT DEFAULT 0,
    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

INSERT INTO product_unit (initials, name, can_fraction) VALUES ('AR', 'ARROBA', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('BA', 'BARRICA', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('BB', 'BOMBONA', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('BD', 'BALDE', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('BG', 'Bisnaga', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('BJ', 'Botjão', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('BL', 'Bloco', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('BR', 'Barra', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('CB', 'Cabeça', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('CC', 'Centimetro Cúbico', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('CL', 'Centimetro Cilindro', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('CM', 'Centimetro', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('CT', 'Centro', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('CX', 'Caixa', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('DM', 'Decímetro', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('DZ', 'Duzia', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('FC', 'Frasco', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('FL', 'Folhas', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('FT', 'Pés', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('G', 'Grama', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('GL', 'Galão', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('GZ', 'Groza', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('HR', 'Hora', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('JG', 'Jogo', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('KL', 'Quilograma', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('KT', 'Kit', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('L', 'Litro', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('LB', 'Libra', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('LT', 'Lata', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('M2', 'Metro Quadrado', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('M3', 'Metro Cúbico', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('MC', 'Metro Linear', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('ML', 'Milimetro', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('MM', 'Milimetro', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('MT', 'Metro', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('OZ', 'Onca', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('P', 'Par', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('PC', 'Peça', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('PL', 'Polegadas', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('PO', 'Pote', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('PT', 'Pacote', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('RL', 'Rolo', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('RS', 'Resma', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('SC', 'Saco', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('SP', 'Spray', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('TL', 'Tonelada Liquida', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('TN', 'Tonelada', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('UN', 'Unidade', 0);
INSERT INTO product_unit (initials, name, can_fraction) VALUES ('YD', 'Jarda', 0);






