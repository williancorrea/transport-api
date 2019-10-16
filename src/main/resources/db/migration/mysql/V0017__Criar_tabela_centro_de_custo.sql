CREATE TABLE centro_de_custo(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao varchar(250) NOT NULL,
    inativo BOOLEAN DEFAULT FALSE,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    classe_despesa_id BIGINT(20) NOT NULL,

    FOREIGN KEY (classe_despesa_id) REFERENCES classe_despesa(id)
)
    ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_CENTRO-DE-CUSTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_CENTRO-DE-CUSTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_CENTRO-DE-CUSTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_CENTRO-DE-CUSTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_CENTRO-DE-CUSTO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_CENTRO-DE-CUSTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_CENTRO-DE-CUSTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_CENTRO-DE-CUSTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_CENTRO-DE-CUSTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_CENTRO-DE-CUSTO'));
