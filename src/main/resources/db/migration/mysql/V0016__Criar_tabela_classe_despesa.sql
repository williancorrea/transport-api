CREATE TABLE classe_despesa(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao varchar(150) NOT NULL,
    inativo BOOLEAN DEFAULT FALSE,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
    ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_CLASSE-DESPESA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_CLASSE-DESPESA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_CLASSE-DESPESA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_CLASSE-DESPESA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_CLASSE-DESPESA', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_CLASSE-DESPESA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_CLASSE-DESPESA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_CLASSE-DESPESA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_CLASSE-DESPESA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_CLASSE-DESPESA'));
