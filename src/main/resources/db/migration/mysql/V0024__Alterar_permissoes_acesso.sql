-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ALTERAR_PERMISSOES_ACESSO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ALTERAR_PERMISSOES_ACESSO'));
