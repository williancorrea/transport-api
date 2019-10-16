-- -------------------------------------
-- Create table to TYPE OF RELATIONSHIP
-- -------------------------------------

CREATE TABLE tipo_relacionamento (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo CHAR(3),
    nome VARCHAR(150) NOT NULL,
    descricao TEXT,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_TIPO-RELACIONAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_TIPO-RELACIONAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETE_TIPO-RELACIONAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_TIPO-RELACIONAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_TIPO-RELACIONAMENTO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_TIPO-RELACIONAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_TIPO-RELACIONAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETE_TIPO-RELACIONAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_TIPO-RELACIONAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_TIPO-RELACIONAMENTO'));

-- -----------------------
-- DADOS
-- -----------------------
INSERT INTO tipo_relacionamento (codigo, nome, descricao) VALUES ('101', 'Conjugue ou Companheiro', 'Companheiro(a) com quem o contribuinte tenha filho ou viva há mais de 5 anos, ou cônjuge');
INSERT INTO tipo_relacionamento (codigo, nome, descricao) VALUES ('102', 'Filho ou enteado ate 21 anos', ' Filho(a) ou enteado(a), até 21 anos de idade, ou, em qualquer idade, quando incapacitado física ou mentalmente para o trabalho');
INSERT INTO tipo_relacionamento (codigo, nome, descricao) VALUES ('103', 'Filho ou enteado ate 24 anos estudante', ' Filho(a) ou enteado(a), se ainda estiverem cursando estabelecimento de ensino superior ou escola técnica de segundo grau, até 24 anos de idade');
INSERT INTO tipo_relacionamento (codigo, nome, descricao) VALUES ('104', 'Irmão, Neto ou Bisneto até 21 anos', ' Irmão(ã), neto(a) ou bisneto(a), sem arrimo dos pais, de quem o contribuinte detenha a guarda judicial, até 21 anos, ou em qualquer idade, quando incapacitado física ou mentalmente para o trabalho');
INSERT INTO tipo_relacionamento (codigo, nome, descricao) VALUES ('105', 'Irmão, Neto ou Bisneto até 24 anos', ' Irmão(ã), neto(a) ou bisneto(a), sem arrimo dos pais, com idade de 21 anos até 24 anos, se ainda estiver cursando estabelecimento de ensino superior ou escola técnica de segundo grau, desde que o contribuinte tenha detido sua guarda judicial até os 21 anos');
INSERT INTO tipo_relacionamento (codigo, nome, descricao) VALUES ('106', 'Pais, avós e bisavós', ' Pais, avós e bisavós que, em 2010, tenham recebido rendimentos, tributáveis ou não, até R$ 17.989,80');
INSERT INTO tipo_relacionamento (codigo, nome, descricao) VALUES ('107', 'Menor pobre com Guarda Judicial', ' Menor pobre até 21 anos que o contribuinte crie e eduque e de quem detenha a guarda judicial');
INSERT INTO tipo_relacionamento (codigo, nome, descricao) VALUES ('108', 'Absolutamente incapaz', ' Pessoa absolutamente incapaz, da qual o contribuinte seja tutor ou curador');
