CREATE TABLE colaborador (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    matricula VARCHAR(10) NOT NULL,
    data_cadastro DATE,
    data_admissao DATE,
    data_demissao DATE,
    observacao TEXT NOT NULL,

    pessoa_id BIGINT(20) NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES combustivel(id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_COLABORADOR', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_COLABORADOR', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_COLABORADOR', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_COLABORADOR', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_COLABORADOR', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_COLABORADOR'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_COLABORADOR'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_COLABORADOR'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_COLABORADOR'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_COLABORADOR'));
