CREATE TABLE estado_civil (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT,
    inativo BOOLEAN DEFAULT FALSE,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
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
-- DADOS
-- -------------------------
insert into estado_civil (nome, descricao) values('Solteiro(a)','Quem nunca se casou, ou que teve o casamento anulado');
insert into estado_civil (nome, descricao) values('Casado(a)','Quem contraiu matrimônio, independente do regime de bens adotado');
insert into estado_civil (nome, descricao) values('Divorciado(a)','Após a homologação do divórcio pela justiça ou por uma escritura pública.');
insert into estado_civil (nome, descricao) values('Viúvo(a)','Pessoa cujo cônjuge faleceu');
insert into estado_civil (nome, descricao) values('Separado Judicialmente','Quando a vida conjugal tenha entrado em ruptura, a lei permite que, por decisão conjunta ou individual dos cônjuges, se ponha termo à vida em comum');
