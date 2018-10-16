CREATE TABLE pessoa_telefone (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  telefone_tipo varchar(250) NOT NULL,
  numero varchar(14) NOT NULL,
  observacao varchar(250),
  inativo BOOLEAN DEFAULT FALSE,

  pessoa_id BIGINT(20),
  FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
  ENGINE = InnoDB DEFAULT CHARSET = utf8;