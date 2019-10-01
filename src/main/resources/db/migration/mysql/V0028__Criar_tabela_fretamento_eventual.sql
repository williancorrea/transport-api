CREATE TABLE fretamento_eventual (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,

    situacao VARCHAR(100),
    situacao_data DATE NULL,

    nome VARCHAR(150),
    telefone1 VARCHAR(20),
    telefone2 VARCHAR(20),
    obs LONGBLOB,

    partida_cidade_id BIGINT(20) NOT NULL,
    FOREIGN KEY (partida_cidade_id) REFERENCES cidade(id),
    retorno_cidade_id  BIGINT(20) NOT NULL,
    FOREIGN KEY (retorno_cidade_id) REFERENCES cidade(id),
    partida TIMESTAMP NULL,
    previsao_chegada_partida TIMESTAMP NULL,
    retorno TIMESTAMP NULL,
    previsao_chegada_retorno TIMESTAMP NULL,
    obs_itineratio LONGBLOB,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    pessoa_cliente_id BIGINT(20),     FOREIGN KEY (pessoa_cliente_id) REFERENCES pessoa(id),
    pessoa_motorista1_id BIGINT(20),  FOREIGN KEY (pessoa_motorista1_id) REFERENCES pessoa(id),
    pessoa_motorista2_id BIGINT(20),  FOREIGN KEY (pessoa_motorista2_id) REFERENCES pessoa(id),
    veiculo_id BIGINT(20),  FOREIGN KEY (veiculo_id) REFERENCES veiculo(id),

    nota_fiscal_tipo VARCHAR(150) NOT NULL ,
    valor_motorista1_diaria     decimal(20,2) DEFAULT 0.00,
    valor_motorista2_diaria     decimal(20,2) DEFAULT 0.00,
    valor_estacionamento        decimal(20,2) DEFAULT 0.00,
    valor_agua                  decimal(20,2) DEFAULT 0.00,
    valor_gelo                  decimal(20,2) DEFAULT 0.00,
    valor_despesas_adicionais   decimal(20,2) DEFAULT 0.00,
    valor_dinheiro_reserva      decimal(20,2) DEFAULT 0.00,
    valor_km                    decimal(20,2) not null ,
    valor_hospedagem decimal(20,2) DEFAULT 0.00,
    valor_pedagio decimal(20,2) DEFAULT 0.00,

    valor_combustivel decimal(20,2) DEFAULT 0.00,
    combustivel_lts decimal(20,2) DEFAULT 0.00,
    combustivel_total decimal(20,2) DEFAULT 0.00,

    nota_fiscal_imposto decimal(20,2) DEFAULT 0.00,

    valor_total_despesas decimal(20,2) DEFAULT 0.00,
    viagem_preco_final decimal(20,2) DEFAULT 0.00,

    cobranca_automatica BOOLEAN DEFAULT FALSE,
    km_percorrido_quantidade int not null ,

    empresa_id BIGINT(20), FOREIGN KEY (empresa_id) REFERENCES pessoa(id),
    empresa_representante_id BIGINT(20), FOREIGN KEY (empresa_representante_id) REFERENCES pessoa(id),

    data_impressao_contratato DATE NULL,
    numero_contrato VARCHAR(15),

    obs_custo LONGBLOB

)
ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_FRETAMENTO_EVENTUAL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_FRETAMENTO_EVENTUAL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_FRETAMENTO_EVENTUAL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_FRETAMENTO_EVENTUAL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_FRETAMENTO_EVENTUAL', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_FRETAMENTO_EVENTUAL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_FRETAMENTO_EVENTUAL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_FRETAMENTO_EVENTUAL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_FRETAMENTO_EVENTUAL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_FRETAMENTO_EVENTUAL'));
