
INSERT INTO usuario (nome, email, senha) VALUES ('Washington Luiz Vicente Correa', 'rosinhalocadora@gmail.com', '$2a$10$jLNcTb25E2Six5TcN8QXOO/CdbwCbZpIf2awkAGdoKYd5QUdb7GJa');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_CMB-PADRAO'));

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_ITINERARIO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_ITINERARIO'));

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_VEICULO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_VEICULO'));

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_PESSOA'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_LIST_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_DELETE_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_UPDATE_PERSON'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_SAVE_PERSON'));

INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_CONTROLE-KM'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='rosinhalocadora@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_CONTROLE-KM'));





alter table veiculo add ano_modelo varchar(9) null;
alter table veiculo add tipo_veiculo varchar(50) null;
alter table veiculo add pode_ser_fretado boolean default 1 null;
alter table veiculo add combustivel_id bigint null;
alter table veiculo add cor varchar(50) null;
alter table veiculo add renavam varchar(50) null;
alter table veiculo add chassi varchar(50) null;
alter table veiculo add motor varchar(50) null;
alter table veiculo add motor_modelo varchar(100) null;
alter table veiculo add quantidade_pneus int(20) null;
alter table veiculo add cambio_tipo varchar(100) null;
alter table veiculo add cambio_modelo varchar(100) null;
alter table veiculo add capacidade_oleo_motor_lts decimal(20,2) default 0.0 null;
alter table veiculo add capacidade_oleo_cambio_lts decimal(20,2) default 0.0 null;
alter table veiculo add capacidade_oleo_diferencial_lts decimal(20,2) default 0.0 null;

create index veiculo_combustivel_id on veiculo (combustivel_id);
alter table veiculo add constraint veiculo_ibfk_3 foreign key (combustivel_id) references combustivel (id);

