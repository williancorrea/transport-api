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
INSERT INTO transport_api.veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (1, 'FVC-3969', '14.000', null, '2018-06-19 14:54:22', '2018-06-19 14:54:22');
INSERT INTO transport_api.veiculo (id, placa, frota, obs, data_criacao, data_alteracao) VALUES (2, 'MMH-6155', '19.000', null, '2018-06-19 14:55:03', '2018-06-19 14:55:03');

-- ITINERARIO
INSERT INTO transport_api.itinerario (id, nome, codigo, descricao, valido_ate, ativo, data_criacao, data_alteracao) VALUES (1, 'Paraguaçu Paulista / Assis - Fema - Noturno', '001', '', '2018-12-20', 1, '2018-06-19 15:02:22', '2018-06-19 15:02:22');
INSERT INTO transport_api.itinerario (id, nome, codigo, descricao, valido_ate, ativo, data_criacao, data_alteracao) VALUES (2, 'Paraguaçu Paulista / Assis - UNIP - Noturno', '002', null, '2018-12-20', 1, '2018-06-19 15:02:54', '2018-06-19 15:02:54');

-- PESSOA
INSERT INTO transport_api.pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (1, 'WILLIAN VAGNER VICENTE CORRÊA', 'FISICA', 'willian.vag@gmail.com', null, 0, 0, 0, 0, 0, 0, '2018-06-19 15:04:44', '2018-06-19 15:04:44');
INSERT INTO transport_api.pessoa (id, nome, tipo, email, site, estudante, cliente, fornecedor, colaborador, transportadora, inativo, data_criacao, data_alteracao) VALUES (2, 'WASHINGTOM LUIS VICENTE CORRÊA', 'FISICA', 'rosinhaloca@gmail.com', null, 0, 0, 0, 0, 0, 0, '2018-06-19 15:08:09', '2018-06-19 15:08:09');
INSERT INTO transport_api.pessoa_fisica (id, cpf, rg, orgao_rg, data_emissao_rg, data_nascimento, sexo, naturalidade, nacionalidade, tipo_sangue, cnh_numero, cnh_categoria, cnh_vencimento, titulo_eleitoral_numero, titulo_eleitoral_zona, titulo_eleitoral_secao, reservista_numero, reservista_categoria, nome_mae, nome_pai, estado_civil_id, pessoa_id) VALUES (1, '330.669.088-01', null, null, null, null, 'M', null, null, null, null, null, null, null, null, null, null, null, null, null, 2, 1);
INSERT INTO transport_api.pessoa_fisica (id, cpf, rg, orgao_rg, data_emissao_rg, data_nascimento, sexo, naturalidade, nacionalidade, tipo_sangue, cnh_numero, cnh_categoria, cnh_vencimento, titulo_eleitoral_numero, titulo_eleitoral_zona, titulo_eleitoral_secao, reservista_numero, reservista_categoria, nome_mae, nome_pai, estado_civil_id, pessoa_id) VALUES (2, '423.783.928-18', null, null, null, null, 'M', null, null, null, null, null, null, null, null, null, null, null, null, null, 1, 2);

-- CONTROLE DE KM
INSERT INTO transport_api.controle_km (id, data_hora_saida, data_hora_chegada, origem, destino, km_saida, km_chegada, obs, veiculo_id, pessoa_id, itinerario_id) VALUES (2, '2018-06-23 12:30:00', '2018-06-23 13:00:00', 'Garagem', 'Assis', '1000', '1100', null, 1, 1, 1);