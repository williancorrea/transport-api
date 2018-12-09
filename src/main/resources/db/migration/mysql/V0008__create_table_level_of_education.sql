CREATE TABLE level_of_education (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    descricao TEXT,
    degree_of_instruction_caged BIGINT(20),
    degree_of_instruction_sefip BIGINT(20),
    degree_of_instruction_rais BIGINT(20),

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_NIVEL_FORMACAO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LIST_LEVEL-OF-EDUCATION', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETE_LEVEL-OF-EDUCATION', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_UPDATE_LEVEL-OF-EDUCATION', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SAVE_LEVEL-OF-EDUCATION', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_NIVEL_FORMACAO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LIST_LEVEL-OF-EDUCATION'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETE_LEVEL-OF-EDUCATION'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_UPDATE_LEVEL-OF-EDUCATION'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SAVE_LEVEL-OF-EDUCATION'));

-- -------------------------
-- DADOS
-- -------------------------
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('1','Analfabeto','Analfabeto','1','1','1');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('2','Até 4ª série incomplleto','Até 4ª série incompleta do 1º grau (ensino fundamental)','2','2','2');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('3','4ª série completa','4ª série completa do 1º grau (ensino fundamental)','3','3','3');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('4','5ª a 8ª série incompleta','5ª a 8ª série incompleta do 1º grau (ensino fundamental)','4','4','4');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('5','1º grau completo','1º grau completo (ensino fundamental)','5','5','5');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('6','2º grau incompleto','2º grau incompleto (ensino médio)','6','6','6');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('7','2º grau completo','2º grau completo (ensino médio)','7','7','7');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('8','Superior Incompleto','Superior Incompleto','8','8','8');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('9','Superior Completo','Superior Completo','9','9','9');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('10','Pós-Graduação/Especialização','Pós-Graduação/Especialização','9','10','9');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('11','Mestrado','Mestrado','10','11','9');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('12','Doutorado','Doutorado','11','12','9');
insert into level_of_education (id, name, descricao, degree_of_instruction_caged, degree_of_instruction_sefip, degree_of_instruction_rais) values('13','Pós-Doutorado','Pós-Doutorado','11','13','9');