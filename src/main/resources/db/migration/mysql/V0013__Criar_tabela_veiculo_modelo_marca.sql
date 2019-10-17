CREATE TABLE veiculo_marca (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    inativo BOOLEAN DEFAULT FALSE,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
    ENGINE = InnoDB DEFAULT CHARSET = utf8;



CREATE TABLE veiculo_modelo (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    inativo BOOLEAN DEFAULT FALSE,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
    ENGINE = InnoDB DEFAULT CHARSET = utf8;



CREATE TABLE veiculo (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(8) NOT NULL,
    frota VARCHAR(15) NOT NULL,
    particular BOOLEAN DEFAULT FALSE not null,
    inativo BOOLEAN DEFAULT FALSE,

    obs TEXT,
    odometro_inicial BIGINT(20) DEFAULT 0,
    velocidade_media INT(20) DEFAULT 70,
    consumo_real decimal(20,2) DEFAULT 1.0,
    consumo_atual decimal(20,2) DEFAULT 1.0,
    quantidade_lugares int default 0,
    capacidade_tanque_combustivel_lts int default 0,

    veiculo_marca_id BIGINT(20), FOREIGN KEY (veiculo_marca_id) REFERENCES veiculo_marca(id),
    veiculo_modelo_id BIGINT(20), FOREIGN KEY (veiculo_modelo_id) REFERENCES veiculo_modelo(id),

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
    ENGINE = InnoDB DEFAULT CHARSET = utf8;


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

