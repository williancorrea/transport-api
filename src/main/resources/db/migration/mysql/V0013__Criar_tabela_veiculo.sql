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



-- -------------------------
-- DADOS
-- -------------------------
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (1, 'BWB-5599', '1000', 0, 0, '', 1, 70, 3.30, 3.30, '2019-09-12 11:26:40', '2019-09-12 11:26:40');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (2, 'JOZ-6451', '3000', 0, 0, '', 1, 70, 3.30, 3.30, '2019-09-12 11:48:34', '2019-09-12 11:48:34');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (22, 'BUS-4436', '6000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:28', '2019-09-12 12:05:28');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (23, 'BTT-1439', '7000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:28', '2019-09-12 12:05:28');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (24, 'AAR-4132', '8000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:28', '2019-09-12 12:05:28');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (25, 'BTT-4393', '9000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:28', '2019-09-12 12:05:28');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (26, 'CQH-7646', '10000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:28', '2019-09-12 12:05:28');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (27, 'DMX-0096', '11000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (28, 'BUS-5061', '12000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (29, 'EKH-8252', '13000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (30, 'FVC-3969', '14000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (31, 'DTD-7217', '15000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (32, 'DJF-7852', '16000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (33, 'NFP-8302', '17000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (34, 'DJC-6798', '18000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (35, 'MMH-6155', '19000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (36, 'CVP-2635', '20000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (37, 'LYC-2050', '21000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (38, 'CPN-7802', '22000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (39, 'CPN-7805', '23000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (40, 'GVI-5633', '24000', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (41, 'EHS-1453', 'BIS 125', 1, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (42, 'BHZ-0605', 'CG 125', 1, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (43, 'BLB-3793', 'CORCEL II', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (44, 'CVX-1991', 'CORCEL I', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (45, 'BJQ-4067', 'D20', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (46, 'EGS-6828', 'FIESTA', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (47, 'ANO-4442', 'HILUX', 1, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (48, 'HRK-0071', 'SILVERADO', 0, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
INSERT INTO veiculo (id, placa, frota, particular, inativo, obs, odometro_inicial, velocidade_media, consumo_real, consumo_atual, data_criacao, data_alteracao) VALUES (49, 'DKJ-2893', 'TITAN 150', 1, 0, null, 0, 70, 3.30, 3.30, '2019-09-12 12:05:29', '2019-09-12 12:05:29');
