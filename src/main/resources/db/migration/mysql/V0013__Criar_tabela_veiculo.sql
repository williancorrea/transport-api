CREATE TABLE veiculo (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(8) NOT NULL,
    frota VARCHAR(15) NOT NULL,

    obs TEXT,
    odometro_inicial BIGINT(20) DEFAULT 0,

    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_VEICULO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_VEICULO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_VEICULO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_VEICULO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_VEICULO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_VEICULO'));

