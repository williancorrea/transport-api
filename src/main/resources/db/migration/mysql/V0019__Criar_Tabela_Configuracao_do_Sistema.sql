CREATE TABLE configuracao_sistema (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome varchar(250) NOT NULL,
  descricao varchar(250) NOT NULL,
  valor varchar(250) NOT NULL,
  inativo BOOLEAN DEFAULT FALSE
)
  ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- PERMISSOES
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_CONFIG', '');
INSERT INTO permission (name, description) values ('ROLE_LISTAR_CONFIG', '');
INSERT INTO permission (name, description) values ('ROLE_ATUALIZAR_CONFIG', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_CONFIG'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_CONFIG'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ATUALIZAR_CONFIG'));

-- DADOS
INSERT INTO configuracao_sistema (descricao, valor, nome) VALUES ('BOLETO_QTDE_DIAS_PARA_PROTESTO', '5', 'Quantidade de dias para enviar para protesto após a data de vencimento');

