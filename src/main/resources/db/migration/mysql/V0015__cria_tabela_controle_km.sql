CREATE TABLE controle_km (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_hora_saida TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_hora_chegada TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    origem VARCHAR(150) NOT NULL,
    destino VARCHAR(150) NOT NULL,
    km_saida VARCHAR(30) NOT NULL,
    km_chegada VARCHAR(30) NOT NULL,
    obs TEXT,

    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    veiculo_id BIGINT(20),
    pessoa_id BIGINT(20),
    itinerario_id BIGINT(20),

    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    FOREIGN KEY (itinerario_id) REFERENCES itinerario(id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;



-- VEICULO
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (1, 'FVC-3969', '14.000', null, '2018-06-19 14:54:22', '2018-06-19 14:54:22');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (2, 'MMH-6155', '19.000', null, '2018-06-19 14:55:03', '2018-06-19 14:55:03');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (3, 'EGS-6828', 'FIESTA', null, '2018-06-25 19:59:47', '2018-06-25 19:59:47');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (4, 'BWB-5599', '1.000', null, '2018-06-25 20:47:43', '2018-06-25 20:48:22');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (5, 'JOZ-6451', '3.000', null, '2018-06-25 20:48:48', '2018-06-25 20:48:48');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (6, 'BUS-4436', '6.000', null, '2018-06-25 20:49:54', '2018-06-25 20:49:54');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (7, 'BTT-1439', '7.000', null, '2018-06-25 20:50:12', '2018-06-25 20:50:12');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (8, 'AAR-4132', '8.000', null, '2018-06-25 20:50:32', '2018-06-25 20:50:32');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (9, 'BTT-4393', '9.000', null, '2018-06-25 20:50:56', '2018-06-25 20:50:56');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (10, 'CQH-7646', '10.000', null, '2018-06-25 20:51:18', '2018-06-25 20:51:18');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (11, 'DMX-0096', '11.000', null, '2018-06-25 20:51:42', '2018-06-25 20:51:42');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (12, 'BUS-5061', '12.000', null, '2018-06-25 20:52:08', '2018-06-25 20:52:08');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (13, 'EKH-8252', '13.000', null, '2018-06-25 20:52:29', '2018-06-25 20:52:29');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (14, 'DTD-7217', '15.000', null, '2018-06-25 20:53:04', '2018-06-25 20:53:04');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (15, 'DJF-7852', '16.000', null, '2018-06-25 20:53:31', '2018-06-25 20:53:31');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (16, 'NFP-8302', '17.000', null, '2018-06-25 20:53:55', '2018-06-25 20:53:55');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (17, 'DJC-6798', '18.000', null, '2018-06-25 20:54:17', '2018-06-25 20:54:17');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (18, 'CVP-2635', '20.000', null, '2018-06-25 20:54:48', '2018-06-25 20:54:48');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (19, 'LYC-2050', '21.000', null, '2018-06-25 20:55:15', '2018-06-25 20:55:15');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (20, 'EHS-1453', 'BIS DA MÃE', null, '2018-06-25 20:56:04', '2018-06-25 20:56:04');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (21, 'BLB-3793', 'CORCEL', null, '2018-06-25 20:56:25', '2018-06-25 20:56:25');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (22, 'BJQ-4067', 'D20', null, '2018-06-25 20:56:45', '2018-06-25 20:56:45');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (23, 'ANO-4442', 'HILUX', null, '2018-06-25 20:57:22', '2018-06-25 20:57:22');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (24, 'JYH-4626', 'TOMARAM', null, '2018-06-25 20:58:17', '2018-06-25 20:58:17');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (25, 'BXC-6253', 'ROUBARÃO', null, '2018-06-25 20:59:01', '2018-06-25 20:59:01');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (26, 'HRK-0071', 'SILVERADO', null, '2018-06-25 20:59:30', '2018-06-25 20:59:30');
INSERT INTO veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (27, 'DKJ-2893', 'TITAN 150', null, '2018-06-25 20:59:57', '2018-06-25 20:59:57');

-- ITINERARIO
INSERT INTO itinerario (id, nome, codigo, descricao, valido_ate, ativo, data_criacao, data_alteracao) VALUES (1, 'Paraguaçu Paulista / Assis - Fema - Noturno', '001', '', '2018-12-20', 1, '2018-06-19 15:02:22', '2018-06-19 15:02:22');
INSERT INTO itinerario (id, nome, codigo, descricao, valido_ate, ativo, data_criacao, data_alteracao) VALUES (2, 'Paraguaçu Paulista / Assis - UNIP - Noturno', '002', null, '2018-12-20', 1, '2018-06-19 15:02:54', '2018-06-19 15:02:54');
INSERT INTO itinerario (id, nome, codigo, descricao, valido_ate, ativo, data_criacao, data_alteracao) VALUES (3, 'ADMINISTRATIVO / MANUTENÇÃO', '000', 'Utilizado para marcar as utilizações administrativas', '2018-12-30', 1, '2018-06-25 20:00:35', '2018-06-25 20:01:07');
INSERT INTO itinerario (id, nome, codigo, descricao, valido_ate, ativo, data_criacao, data_alteracao) VALUES (4, 'SAPORE (Zilor)', '004', 'Transporte de funcionários da empresa Sapore para usina Zilor', '2018-07-17', 1, '2018-06-25 20:24:35', '2018-06-25 21:06:33');
INSERT INTO itinerario (id, nome, codigo, descricao, valido_ate, ativo, data_criacao, data_alteracao) VALUES (5, 'CRECHE/ESCOLINHA', '005', null, '2018-11-30', 1, '2018-06-25 21:06:22', '2018-06-25 21:06:22');
INSERT INTO itinerario (id, nome, codigo, descricao, valido_ate, ativo, data_criacao, data_alteracao) VALUES (6, 'PALMITAL / ASSIS', '007', null, '2018-12-31', 1, '2018-06-26 09:25:20', '2018-06-26 09:25:20');

-- PESSOA
INSERT INTO pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (1, 'WILLIAN VAGNER VICENTE CORRÊA', 'FISICA', 'willian.vag@gmail.com', null, 0, 0, 0, 0, 0, 0, '2018-06-19 15:04:44', '2018-06-19 15:04:44');
INSERT INTO pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (2, 'WASHINGTOM LUIS VICENTE CORRÊA', 'FISICA', 'rosinhaloca@gmail.com', null, 0, 0, 0, 0, 0, 0, '2018-06-19 15:08:09', '2018-06-19 15:08:09');
INSERT INTO pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (3, 'HERCILIO MARQUES', 'FISICA', null, null, 0, 0, 0, 1, 0, 0, '2018-06-25 20:40:31', '2018-06-25 20:40:31');
INSERT INTO pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (4, 'EMERSON FIAZI DE CARVALHO', 'FISICA', null, null, 0, 0, 0, 1, 0, 0, '2018-06-25 20:45:36', '2018-06-25 20:45:36');
INSERT INTO pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (5, 'AVERALDO PATRICIO DA SILVA', 'FISICA', null, null, 0, 0, 0, 0, 0, 0, '2018-06-25 21:39:10', '2018-06-25 21:39:10');
INSERT INTO pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (6, 'JOSÉ REIS DOS SANTOS', 'FISICA', null, null, 0, 0, 0, 0, 0, 0, '2018-06-25 21:41:54', '2018-06-25 21:41:54');
INSERT INTO pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (7, 'PAULO RODRIGUES DIAS', 'FISICA', null, null, 0, 0, 0, 0, 0, 0, '2018-06-26 09:17:03', '2018-06-26 09:17:03');
INSERT INTO pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (8, 'ANTONIO DAS GRAÇAS DE SOUZA', 'FISICA', null, null, 0, 0, 0, 0, 0, 0, '2018-06-26 09:20:50', '2018-06-26 09:20:50');

-- CONTROLE DE KM
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (3, '2018-06-25 10:30:00', '2018-06-25 15:06:00', 'Garagem', 'Assis', '242524', '242594', 'Comprar tintas p/ pintura (Chuveirão das Tintas)', '2018-06-25 19:54:13', '2018-06-25 20:18:11', 3, 2, 3);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (4, '2018-06-25 13:00:00', '2018-06-25 14:00:00', 'Garagem', 'Quatá', '168294', '168350', 'Buscar funcionário (Cristiane)', '2018-06-25 20:42:29', '2018-06-25 20:44:30', 2, 3, 4);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (5, '2018-06-25 16:40:00', '2018-06-25 17:22:00', 'GARAGEM', 'CRECHE', '734597', '734611', null, '2018-06-25 21:08:20', '2018-06-25 21:08:20', 15, 4, 5);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (6, '2018-06-25 14:30:00', '2018-06-25 17:14:00', 'Garagem', 'Quatá', '168350', '168429', null, '2018-06-25 21:14:55', '2018-06-25 21:14:55', 2, 3, 4);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (7, '2018-06-25 06:00:00', '2018-06-25 08:00:00', 'Garagem', 'CRECHE', '734551', '734573', null, '2018-06-25 21:17:54', '2018-06-25 21:17:54', 15, 4, 5);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (8, '2018-06-24 06:00:00', '2018-06-24 08:13:00', 'GARAGEM', 'QUATÁ', '167985', '168058', null, '2018-06-25 21:19:50', '2018-06-25 21:19:50', 2, 3, 4);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (9, '2018-06-24 22:30:00', '2018-06-25 00:33:00', 'GARAGEM', 'QUATÁ', '168133', '168207', null, '2018-06-25 21:21:34', '2018-06-25 21:21:34', 2, 3, 4);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (10, '2018-06-24 14:30:00', '2018-06-24 16:45:00', 'GARAGEM', 'QUATÁ', '168058', '168133', null, '2018-06-25 21:23:39', '2018-06-25 21:23:39', 2, 3, 4);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (11, '2018-06-25 12:00:00', '2018-06-25 13:50:00', 'GARAGEM', 'CRECHE', '734573', '734597', null, '2018-06-25 21:26:23', '2018-06-25 21:26:23', 15, 4, 5);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (12, '2018-06-18 10:50:00', '2018-06-18 13:15:00', 'PARAGUAÇU PTA', 'PALMITAL', '634945', '635021', null, '2018-06-26 09:24:26', '2018-06-26 09:26:54', 11, 8, 6);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (13, '2018-06-18 06:20:00', '2018-06-18 12:11:00', 'PALMITAL', 'ASSIS', '635021', '635096', null, '2018-06-26 09:30:58', '2018-06-26 09:30:58', 11, 8, 6);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (14, '2018-06-20 06:20:00', '2018-06-20 13:00:00', 'PALMITAL', 'ASSIS', '635096', '635176', null, '2018-06-26 09:34:07', '2018-06-26 09:34:07', 11, 8, 6);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (15, '2018-06-21 06:20:00', '2018-06-21 12:41:00', 'PALMITAL', 'ASSIS', '635176', '635265', null, '2018-06-26 09:35:40', '2018-06-26 09:35:40', 11, 8, 6);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (16, '2018-06-25 06:20:00', '2018-06-25 13:10:00', 'PALMITAL', 'ASSIS', '635265', '635355', 'FOI COLOCAR LIMPADOR DE PARABRISA NA GETULIO VARGAS ACESSORIO AS 11:15', '2018-06-26 09:38:50', '2018-06-26 09:38:50', 11, 8, 6);
INSERT INTO controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, data_criacao, data_alteracao, veiculo_id, pessoa_id, itinerario_id) VALUES (17, '2018-06-25 22:30:00', '2018-06-26 00:40:00', 'GARAGEM', 'QUATÁ', '168429', '168535', null, '2018-06-26 09:43:28', '2018-06-26 09:43:28', 2, 3, 4);