CREATE TABLE tanque_combustivel (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    inativo BOOLEAN DEFAULT FALSE,

    quantidade_lts  INTEGER,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    combustivel_id BIGINT(20) NOT NULL,
    FOREIGN KEY (combustivel_id) REFERENCES combustivel(id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_TANQUE-COMBUSTIVEL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_TANQUE-COMBUSTIVEL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_TANQUE-COMBUSTIVEL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_TANQUE-COMBUSTIVEL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_TANQUE-COMBUSTIVEL', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_TANQUE-COMBUSTIVEL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_TANQUE-COMBUSTIVEL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_TANQUE-COMBUSTIVEL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_TANQUE-COMBUSTIVEL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_TANQUE-COMBUSTIVEL'));
