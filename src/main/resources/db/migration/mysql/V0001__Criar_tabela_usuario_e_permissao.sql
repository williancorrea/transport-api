CREATE TABLE usuario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(250) NOT NULL,
	email VARCHAR(200) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	descricao VARCHAR(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ADICIONA O USUARIO MASTER DA APLICACAO
INSERT INTO usuario (nome, email, senha) VALUES ('Willian Vagner Vicente Corrêa', 'willian.vag@gmail.com', '$2a$10$LfoVSB5m5A5Fey2Y90.owOYvYaSFaWwMmGB9N7qSSdeHwcyhAA2R6');

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_ESTADO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_CIDADE', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_ESTADO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_CIDADE'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- UNIDADE DE MEDIDA
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_UNIDADE_MEDIDA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LIST_PRODUCT-UNIT', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETE_PRODUCT-UNIT', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_UPDATE_PRODUCT-UNIT', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SAVE_PRODUCT-UNIT', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_UNIDADE_MEDIDA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LIST_PRODUCT-UNIT'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETE_PRODUCT-UNIT'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_UPDATE_PRODUCT-UNIT'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SAVE_PRODUCT-UNIT'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- TIPO DE RELACIONAMENTO
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_TIPO_RELACIONAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LIST_TYPE-RELATIONSHIP', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETE_TYPE-RELATIONSHIP', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_UPDATE_TYPE-RELATIONSHIP', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SAVE_TYPE-RELATIONSHIP', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_TIPO_RELACIONAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LIST_TYPE-RELATIONSHIP'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETE_TYPE-RELATIONSHIP'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_UPDATE_TYPE-RELATIONSHIP'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SAVE_TYPE-RELATIONSHIP'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- NIVEL DE FORMACAO
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
-- PERMISSOES
-- -------------------------
-- ESTADO CIVIL
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_ESTADO_CIVIL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_ESTADO_CIVIL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_ESTADO_CIVIL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_ESTADO_CIVIL', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_ESTADO_CIVIL', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_ESTADO_CIVIL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_ESTADO_CIVIL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_ESTADO_CIVIL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_ESTADO_CIVIL'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_ESTADO_CIVIL'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- PESSOA
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_PESSOA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LIST_PERSON', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETE_PERSON', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_UPDATE_PERSON', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SAVE_PERSON', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_PESSOA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LIST_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETE_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_UPDATE_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SAVE_PERSON'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- CEPs
INSERT INTO permissao (nome, descricao) values ('ROLE_LIST_ZIP-CODE', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LIST_ZIP-CODE'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- VEICULO
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

-- -------------------------
-- PERMISSOES
-- -------------------------
-- ITINERARIO
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_ITINERARIO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_ITINERARIO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_ITINERARIO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_ITINERARIO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_ITINERARIO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_ITINERARIO'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- CONTROLE DE QUILOMETRAGEM
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_CONTROLE-KM', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_CONTROLE-KM', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_CONTROLE-KM', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_CONTROLE-KM', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_CONTROLE-KM', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_CONTROLE-KM'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- CLASSE DE DESPESAS
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_CLASSE-DESPESA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_CLASSE-DESPESA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_CLASSE-DESPESA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_CLASSE-DESPESA', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_CLASSE-DESPESA', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_CLASSE-DESPESA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_CLASSE-DESPESA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_CLASSE-DESPESA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_CLASSE-DESPESA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_CLASSE-DESPESA'));

-- -------------------------
-- PERMISSOES
-- -------------------------
-- CENTRO DE CUSTO
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_CENTRO-DE-CUSTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_CENTRO-DE-CUSTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_CENTRO-DE-CUSTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_CENTRO-DE-CUSTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_CENTRO-DE-CUSTO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_CENTRO-DE-CUSTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_CENTRO-DE-CUSTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_CENTRO-DE-CUSTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_CENTRO-DE-CUSTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_CENTRO-DE-CUSTO'));
