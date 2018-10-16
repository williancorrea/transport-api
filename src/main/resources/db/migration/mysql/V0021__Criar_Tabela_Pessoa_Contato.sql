CREATE TABLE pessoa_contato (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome varchar(250) NOT NULL,
  email varchar(250) NOT NULL,
  fone_comercial varchar(14),
  fone_residencial varchar(14),
  fone_celular varchar(14),
  inativo BOOLEAN DEFAULT FALSE,

  pessoa_id BIGINT(20),
  FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
  ENGINE = InnoDB DEFAULT CHARSET = utf8;