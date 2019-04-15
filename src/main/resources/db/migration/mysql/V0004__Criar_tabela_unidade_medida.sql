CREATE TABLE unidade_medida (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    sigla VARCHAR(10),
    
    pode_fracionar BOOLEAN DEFAULT FALSE,
    inativo BOOLEAN DEFAULT FALSE,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_UNIDADE-MEDIDA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_UNIDADE-MEDIDA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETE_UNIDADE-MEDIDA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_UNIDADE-MEDIDA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_UNIDADE-MEDIDA', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_UNIDADE-MEDIDA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_UNIDADE-MEDIDA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETE_UNIDADE-MEDIDA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_UNIDADE-MEDIDA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_UNIDADE-MEDIDA'));

-- ------------------------
-- DADOS
-- ------------------------
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('AR', 'ARROBA', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('BA', 'BARRICA', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('BB', 'BOMBONA', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('BD', 'BALDE', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('BG', 'Bisnaga', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('BJ', 'Botjão', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('BL', 'Bloco', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('BR', 'Barra', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('CB', 'Cabeça', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('CC', 'Centimetro Cúbico', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('CL', 'Centimetro Cilindro', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('CM', 'Centimetro', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('CT', 'Centro', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('CX', 'Caixa', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('DM', 'Decímetro', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('DZ', 'Duzia', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('FC', 'Frasco', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('FL', 'Folhas', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('FT', 'Pés', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('G', 'Grama', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('GL', 'Galão', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('GZ', 'Groza', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('HR', 'Hora', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('JG', 'Jogo', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('KL', 'Quilograma', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('KT', 'Kit', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('L', 'Litro', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('LB', 'Libra', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('LT', 'Lata', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('M2', 'Metro Quadrado', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('M3', 'Metro Cúbico', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('MC', 'Metro Linear', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('ML', 'Milimetro', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('MM', 'Milimetro', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('MT', 'Metro', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('OZ', 'Onca', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('P', 'Par', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('PC', 'Peça', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('PL', 'Polegadas', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('PO', 'Pote', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('PT', 'Pacote', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('RL', 'Rolo', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('RS', 'Resma', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('SC', 'Saco', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('SP', 'Spray', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('TL', 'Tonelada Liquida', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('TN', 'Tonelada', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('UN', 'Unidade', 0);
INSERT INTO unidade_medida (sigla, nome, pode_fracionar) VALUES ('YD', 'Jarda', 0);






