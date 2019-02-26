CREATE TABLE pessoa_auditoria (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  objeto_alterado TEXT NOT NULL,
  data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  pessoa_id BIGINT(20),
  FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
)
  ENGINE = InnoDB DEFAULT CHARSET = utf8;