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
