CREATE TABLE fretamento_eventual (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

    situacao VARCHAR(100),

    nome VARCHAR(150),
    telefone1 VARCHAR(20),
    telefone2 VARCHAR(20),
    obs LONGBLOB,

    partida_cidade_id BIGINT(20),
    FOREIGN KEY (partida_cidade_id) REFERENCES cidade(id),
    retorno_cidade_id  BIGINT(20),
    FOREIGN KEY (retorno_cidade_id) REFERENCES cidade(id),
    partida TIMESTAMP NOT NULL,
    partida_obs LONGBLOB,
    retorno TIMESTAMP NOT NULL,
    retorno_obs LONGBLOB,


    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    pessoa_id BIGINT(20),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_FRETAMENTO_EVENTUAL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_FRETAMENTO_EVENTUAL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_FRETAMENTO_EVENTUAL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_FRETAMENTO_EVENTUAL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_FRETAMENTO_EVENTUAL', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_FRETAMENTO_EVENTUAL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_FRETAMENTO_EVENTUAL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_FRETAMENTO_EVENTUAL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_FRETAMENTO_EVENTUAL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_FRETAMENTO_EVENTUAL'));
