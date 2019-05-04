CREATE TABLE pessoa_telefone (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  telefone_tipo varchar(250) NOT NULL,
  numero varchar(14) NOT NULL,
  observacao varchar(250),
  inativo BOOLEAN DEFAULT FALSE,

  data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  pessoa_id BIGINT(20),
  FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
  ENGINE = InnoDB DEFAULT CHARSET = utf8;
