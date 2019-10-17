CREATE TABLE tipo_pagamento (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  descricao varchar(150) NOT NULL,
  inativo BOOLEAN DEFAULT FALSE,
  nao_pode_ser_alterado BOOLEAN DEFAULT FALSE,

  data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ------------------
-- PERMISSOES
-- ------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_TIPO-PAGAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_TIPO-PAGAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_TIPO-PAGAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_TIPO-PAGAMENTO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_TIPO-PAGAMENTO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_TIPO-PAGAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_TIPO-PAGAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_TIPO-PAGAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_TIPO-PAGAMENTO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_TIPO-PAGAMENTO'));

-- DADOS
INSERT INTO tipo_pagamento (descricao, nao_pode_ser_alterado) VALUES ('DINHEIRO', true );
INSERT INTO tipo_pagamento (descricao, nao_pode_ser_alterado) VALUES ('CHEQUE', true );
INSERT INTO tipo_pagamento (descricao) VALUES ('CARTÃO DE CREDITO');
INSERT INTO tipo_pagamento (descricao) VALUES ('CARTÃO DE DEBITO');
INSERT INTO tipo_pagamento (descricao) VALUES ('TRANSFERENCIA BANCARIA');
INSERT INTO tipo_pagamento (descricao) VALUES ('DEPOSITO BANCARIO');
INSERT INTO tipo_pagamento (descricao) VALUES ('OUTROS');


