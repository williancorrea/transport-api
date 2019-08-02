CREATE TABLE fretamento (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

    data_cadastro  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_contrato DATE,
    observacao longblob,

    pessoa_id BIGINT(20),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_FRETAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_FRETAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_FRETAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_FRETAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_FRETAMENTO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_FRETAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_FRETAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_FRETAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_FRETAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_FRETAMENTO'));
