INSERT INTO usuario (nome, email, senha) VALUES ('Washington Luiz Vicente Correa', 'rosinhalocadora@gmail.com', '$2a$10$jLNcTb25E2Six5TcN8QXOO/CdbwCbZpIf2awkAGdoKYd5QUdb7GJa');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_CMB-PADRAO'));

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_ITINERARIO'));

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_VEICULO'));

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_PESSOA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_LIST_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_DELETE_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_UPDATE_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_SAVE_PERSON'));

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_CONTROLE-KM'));


INSERT INTO pessoa (id, nome, fantasia, tipo, email, site, imagem_base_64, telefone1, telefone1Obs, telefone2, telefone2Obs, cep, endereco, bairro, obs, inativo, data_criacao, data_alteracao, cidade_id) VALUES (1, 'Willian Vagner Vicente Correa', null, 'FISICA', 'willian.vag@gmail.com', null, null, '18998203575', 'Vivo', '', null, '19700-000', 'Guimarães Rosa, 201', 'Vila Galdino', '', 0, '2019-09-05 22:10:18', '2019-09-05 22:10:18', 1965);
INSERT INTO pessoa_fisica (id, cpf, rg, orgao_rg, data_emissao_rg, data_nascimento, sexo, naturalidade, nacionalidade, tipo_sangue, cnh_numero, cnh_categoria, cnh_vencimento, titulo_eleitoral_numero, titulo_eleitoral_zona, titulo_eleitoral_secao, reservista_numero, reservista_categoria, nome_mae, nome_pai, data_criacao, data_alteracao, estado_civil_id, pessoa_id) VALUES (1, '330.669.088-01', '40.586.432-2', null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, '2019-09-05 22:10:18', '2019-09-05 22:10:18', null, 1);
