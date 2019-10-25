-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ALTERAR_PERMISSOES_ACESSO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ALTERAR_PERMISSOES_ACESSO'));


-- -------------------------
-- PERMISSOES - COMBUSTIVEL
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