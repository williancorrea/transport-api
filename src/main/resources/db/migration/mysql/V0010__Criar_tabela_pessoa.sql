CREATE TABLE pessoa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(250) NOT NULL,
    tipo VARCHAR(150) NOT NULL,
    email VARCHAR(250),
    site VARCHAR(250),

#     estudante BOOLEAN DEFAULT FALSE,
#     cliente BOOLEAN DEFAULT FALSE,
#     fornecedor BOOLEAN DEFAULT FALSE,
#     colaborador BOOLEAN DEFAULT FALSE,
#     transportadora BOOLEAN DEFAULT FALSE,
#     inativo BOOLEAN DEFAULT FALSE,

    obs LONGBLOB,
    inativo BOOLEAN DEFAULT FALSE,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_PESSOA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LIST_PERSON', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETE_PERSON', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_UPDATE_PERSON', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SAVE_PERSON', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_PESSOA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LIST_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETE_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_UPDATE_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SAVE_PERSON'));
