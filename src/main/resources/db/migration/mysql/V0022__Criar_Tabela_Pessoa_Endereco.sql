CREATE TABLE pessoa_endereco (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  logradouro varchar(150) NOT NULL,
  numero varchar(15) NOT NULL,
  complemento varchar(80),
  bairro varchar(100),
  cep varchar(8),
  principal BOOLEAN DEFAULT FALSE,
  entrega BOOLEAN DEFAULT FALSE,
  cobranca BOOLEAN DEFAULT FALSE,
  correspondencia BOOLEAN DEFAULT FALSE,
  inativo BOOLEAN DEFAULT FALSE,

  pessoa_id BIGINT(20),
  FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),

  cidade_id BIGINT(20),
  FOREIGN KEY (cidade_id) REFERENCES cidade(id)
)
  ENGINE = InnoDB DEFAULT CHARSET = utf8;