CREATE TABLE tipo_pagamento (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  descricao varchar(150) NOT NULL,
  inativo BOOLEAN DEFAULT FALSE,

  data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- PERMISSOES
INSERT INTO permission (name, description) values ('ROLE_ACESSAR_URI_TIPO-PAGAMENTO', '');
INSERT INTO permission (name, description) values ('ROLE_LISTAR_TIPO-PAGAMENTO', '');
INSERT INTO permission (name, description) values ('ROLE_DELETAR_TIPO-PAGAMENTO', '');
INSERT INTO permission (name, description) values ('ROLE_ATUALIZAR_TIPO-PAGAMENTO', '');
INSERT INTO permission (name, description) values ('ROLE_SALVAR_TIPO-PAGAMENTO', '');
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ACESSAR_URI_TIPO-PAGAMENTO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LISTAR_TIPO-PAGAMENTO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETAR_TIPO-PAGAMENTO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_ATUALIZAR_TIPO-PAGAMENTO'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SALVAR_TIPO-PAGAMENTO'));

-- DADOS
INSERT INTO tipo_pagamento (descricao) VALUES ('DINHEIRO');
INSERT INTO tipo_pagamento (descricao) VALUES ('CHEQUE');
INSERT INTO tipo_pagamento (descricao) VALUES ('CARTÃO DE CREDITO');
INSERT INTO tipo_pagamento (descricao) VALUES ('CARTÃO DE DEBITO');
INSERT INTO tipo_pagamento (descricao) VALUES ('TRANSFERENCIA BANCARIA');
INSERT INTO tipo_pagamento (descricao) VALUES ('DEPOSITO BANCARIO');
INSERT INTO tipo_pagamento (descricao) VALUES ('OUTROS');