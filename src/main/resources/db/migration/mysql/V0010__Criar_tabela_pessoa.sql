CREATE TABLE pessoa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(250) NOT NULL,
    fantasia VARCHAR(250),
    tipo VARCHAR(150) NOT NULL,
    email VARCHAR(250),
    site VARCHAR(250),
    imagem_base_64 LONGBLOB,

    telefone1 VARCHAR(20),
    telefone1Obs VARCHAR(100),
    telefone2 VARCHAR(20),
    telefone2Obs VARCHAR(100),

    cep VARCHAR(9),
    endereco VARCHAR(250),
    bairro VARCHAR(100),

#     estudante BOOLEAN DEFAULT FALSE,
#     cliente BOOLEAN DEFAULT FALSE,
#     fornecedor BOOLEAN DEFAULT FALSE,
#     colaborador BOOLEAN DEFAULT FALSE,
#     transportadora BOOLEAN DEFAULT FALSE,

    obs LONGBLOB,
    inativo BOOLEAN DEFAULT FALSE,

    representante_comercial_rosinha_transportes BOOLEAN DEFAULT FALSE,
    empresa_rosinha_transportes BOOLEAN DEFAULT FALSE,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    cidade_id BIGINT(20),
    FOREIGN KEY (cidade_id) REFERENCES cidade(id)
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
