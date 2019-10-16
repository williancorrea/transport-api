CREATE TABLE controle_km (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_hora_saida TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_hora_chegada TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    origem VARCHAR(150) NOT NULL,
    destino VARCHAR(150) NOT NULL,
    km_saida BIGINT(20) NOT NULL,
    km_chegada BIGINT(20) NOT NULL,
    obs TEXT,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    veiculo_id BIGINT(20),
    pessoa_id BIGINT(20),
    itinerario_id BIGINT(20),

    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    FOREIGN KEY (itinerario_id) REFERENCES itinerario(id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_CONTROLE-KM', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_CONTROLE-KM', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_CONTROLE-KM', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_CONTROLE-KM', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_CONTROLE-KM', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_CONTROLE-KM'));
