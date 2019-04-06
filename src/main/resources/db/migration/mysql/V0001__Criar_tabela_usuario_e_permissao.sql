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

-- ADICIONA O FILTRO PADRAO QUE SERA UTILIZADO NOS COMBOBOX
INSERT INTO permissao (nome, descricao) values ('ROLE_CMB_PADRAO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_CMB_PADRAO'));




