CREATE TABLE user (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(250) NOT NULL,
	email VARCHAR(200) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_permission (
	id_user BIGINT(20) NOT NULL,
	id_permission BIGINT(20) NOT NULL,
	PRIMARY KEY (id_user, id_permission),
	FOREIGN KEY (id_user) REFERENCES user(id),
	FOREIGN KEY (id_permission) REFERENCES permission(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ADICIONA O USUARIO MASTER DA APLICACAO
INSERT INTO user (name, email, password) VALUES ('Willian Vagner Vicente Corrêa', 'willian.vag@gmail.com', '$2a$10$LfoVSB5m5A5Fey2Y90.owOYvYaSFaWwMmGB9N7qSSdeHwcyhAA2R6');

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permission (name, description) values ('ROLE_LISTAR_ESTADO', '');
INSERT INTO permission (name, description) values ('ROLE_LISTAR_CIDADE', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_ESTADO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_CIDADE'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- UNIDADE DE MEDIDA
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_UNIDADE_MEDIDA', '');
INSERT INTO permission (name, description) values ('ROLE_LIST_PRODUCT-UNIT', '');
INSERT INTO permission (name, description) values ('ROLE_DELETE_PRODUCT-UNIT', '');
INSERT INTO permission (name, description) values ('ROLE_UPDATE_PRODUCT-UNIT', '');
INSERT INTO permission (name, description) values ('ROLE_SAVE_PRODUCT-UNIT', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_UNIDADE_MEDIDA'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_PRODUCT-UNIT'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETE_PRODUCT-UNIT'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_UPDATE_PRODUCT-UNIT'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SAVE_PRODUCT-UNIT'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- TIPO DE RELACIONAMENTO
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_TIPO_RELACIONAMENTO', '');
INSERT INTO permission (name, description) values ('ROLE_LIST_TYPE-RELATIONSHIP', '');
INSERT INTO permission (name, description) values ('ROLE_DELETE_TYPE-RELATIONSHIP', '');
INSERT INTO permission (name, description) values ('ROLE_UPDATE_TYPE-RELATIONSHIP', '');
INSERT INTO permission (name, description) values ('ROLE_SAVE_TYPE-RELATIONSHIP', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_TIPO_RELACIONAMENTO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_TYPE-RELATIONSHIP'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETE_TYPE-RELATIONSHIP'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_UPDATE_TYPE-RELATIONSHIP'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SAVE_TYPE-RELATIONSHIP'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- NIVEL DE FORMACAO
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_NIVEL_FORMACAO', '');
INSERT INTO permission (name, description) values ('ROLE_LIST_LEVEL-OF-EDUCATION', '');
INSERT INTO permission (name, description) values ('ROLE_DELETE_LEVEL-OF-EDUCATION', '');
INSERT INTO permission (name, description) values ('ROLE_UPDATE_LEVEL-OF-EDUCATION', '');
INSERT INTO permission (name, description) values ('ROLE_SAVE_LEVEL-OF-EDUCATION', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_NIVEL_FORMACAO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_LEVEL-OF-EDUCATION'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETE_LEVEL-OF-EDUCATION'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_UPDATE_LEVEL-OF-EDUCATION'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SAVE_LEVEL-OF-EDUCATION'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- ESTADO CIVIL
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_ESTADO_CIVIL', '');
INSERT INTO permission (name, description) values ('ROLE_LISTAR_ESTADO_CIVIL', '');
INSERT INTO permission (name, description) values ('ROLE_DELETAR_ESTADO_CIVIL', '');
INSERT INTO permission (name, description) values ('ROLE_ATUALIZAR_ESTADO_CIVIL', '');
INSERT INTO permission (name, description) values ('ROLE_SALVAR_ESTADO_CIVIL', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_ESTADO_CIVIL'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_ESTADO_CIVIL'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETAR_ESTADO_CIVIL'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ATUALIZAR_ESTADO_CIVIL'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SALVAR_ESTADO_CIVIL'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- PESSOA
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_PESSOA', '');
INSERT INTO permission (name, description) values ('ROLE_LIST_PERSON', '');
INSERT INTO permission (name, description) values ('ROLE_DELETE_PERSON', '');
INSERT INTO permission (name, description) values ('ROLE_UPDATE_PERSON', '');
INSERT INTO permission (name, description) values ('ROLE_SAVE_PERSON', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_PESSOA'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_PERSON'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETE_PERSON'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_UPDATE_PERSON'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SAVE_PERSON'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- CEPs
INSERT INTO permission (name, description) values ('ROLE_LIST_ZIP-CODE', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_ZIP-CODE'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- VEICULO
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_VEICULO', '');
INSERT INTO permission (name, description) values ('ROLE_LISTAR_VEICULO', '');
INSERT INTO permission (name, description) values ('ROLE_DELETAR_VEICULO', '');
INSERT INTO permission (name, description) values ('ROLE_ATUALIZAR_VEICULO', '');
INSERT INTO permission (name, description) values ('ROLE_SALVAR_VEICULO', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_VEICULO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_VEICULO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETAR_VEICULO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ATUALIZAR_VEICULO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SALVAR_VEICULO'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- ITINERARIO
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_ITINERARIO', '');
INSERT INTO permission (name, description) values ('ROLE_LISTAR_ITINERARIO', '');
INSERT INTO permission (name, description) values ('ROLE_DELETAR_ITINERARIO', '');
INSERT INTO permission (name, description) values ('ROLE_ATUALIZAR_ITINERARIO', '');
INSERT INTO permission (name, description) values ('ROLE_SALVAR_ITINERARIO', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_ITINERARIO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_ITINERARIO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETAR_ITINERARIO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ATUALIZAR_ITINERARIO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SALVAR_ITINERARIO'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- CONTROLE DE QUILOMETRAGEM
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_CONTROLE-KM', '');
INSERT INTO permission (name, description) values ('ROLE_LISTAR_CONTROLE-KM', '');
INSERT INTO permission (name, description) values ('ROLE_DELETAR_CONTROLE-KM', '');
INSERT INTO permission (name, description) values ('ROLE_ATUALIZAR_CONTROLE-KM', '');
INSERT INTO permission (name, description) values ('ROLE_SALVAR_CONTROLE-KM', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_CONTROLE-KM'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_CONTROLE-KM'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETAR_CONTROLE-KM'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ATUALIZAR_CONTROLE-KM'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SALVAR_CONTROLE-KM'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- CLASSE DE DESPESAS
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_CLASSE-DESPESA', '');
INSERT INTO permission (name, description) values ('ROLE_LISTAR_CLASSE-DESPESA', '');
INSERT INTO permission (name, description) values ('ROLE_DELETAR_CLASSE-DESPESA', '');
INSERT INTO permission (name, description) values ('ROLE_ATUALIZAR_CLASSE-DESPESA', '');
INSERT INTO permission (name, description) values ('ROLE_SALVAR_CLASSE-DESPESA', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_CLASSE-DESPESA'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_CLASSE-DESPESA'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETAR_CLASSE-DESPESA'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ATUALIZAR_CLASSE-DESPESA'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SALVAR_CLASSE-DESPESA'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- CENTRO DE CUSTO
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_CENTRO-DE-CUSTO', '');
INSERT INTO permission (name, description) values ('ROLE_LISTAR_CENTRO-DE-CUSTO', '');
INSERT INTO permission (name, description) values ('ROLE_DELETAR_CENTRO-DE-CUSTO', '');
INSERT INTO permission (name, description) values ('ROLE_ATUALIZAR_CENTRO-DE-CUSTO', '');
INSERT INTO permission (name, description) values ('ROLE_SALVAR_CENTRO-DE-CUSTO', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_CENTRO-DE-CUSTO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_CENTRO-DE-CUSTO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETAR_CENTRO-DE-CUSTO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ATUALIZAR_CENTRO-DE-CUSTO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SALVAR_CENTRO-DE-CUSTO'));
