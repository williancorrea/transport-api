CREATE TABLE nivel_formacao (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT,
    grau_de_instrucao_caged BIGINT(20),
    grau_de_instrucao_sefip BIGINT(20),
    grau_de_instrucao_rais BIGINT(20),

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_NIVEL_FORMACAO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_NIVEL-FORMACAO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETE_NIVEL-FORMACAO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_NIVEL-FORMACAO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_NIVEL-FORMACAO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_NIVEL_FORMACAO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_NIVEL-FORMACAO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETE_NIVEL-FORMACAO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_NIVEL-FORMACAO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_NIVEL-FORMACAO'));

-- -------------------------
-- DADOS
-- -------------------------
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('1','Analfabeto','Analfabeto','1','1','1');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('2','Até 4ª série incomplleto','Até 4ª série incompleta do 1º grau (ensino fundamental)','2','2','2');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('3','4ª série completa','4ª série completa do 1º grau (ensino fundamental)','3','3','3');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('4','5ª a 8ª série incompleta','5ª a 8ª série incompleta do 1º grau (ensino fundamental)','4','4','4');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('5','1º grau completo','1º grau completo (ensino fundamental)','5','5','5');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('6','2º grau incompleto','2º grau incompleto (ensino médio)','6','6','6');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('7','2º grau completo','2º grau completo (ensino médio)','7','7','7');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('8','Superior Incompleto','Superior Incompleto','8','8','8');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('9','Superior Completo','Superior Completo','9','9','9');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('10','Pós-Graduação/Especialização','Pós-Graduação/Especialização','9','10','9');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('11','Mestrado','Mestrado','10','11','9');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('12','Doutorado','Doutorado','11','12','9');
insert into nivel_formacao (id, nome, descricao, grau_de_instrucao_caged, grau_de_instrucao_sefip, grau_de_instrucao_rais) values('13','Pós-Doutorado','Pós-Doutorado','11','13','9');
