CREATE TABLE marital_status (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    descricao TEXT,

    date_creation  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;



insert into marital_status (name, descricao) values('Solteiro(a)','Quem nunca se casou, ou que teve o casamento anulado');
insert into marital_status (name, descricao) values('Casado(a)','Quem contraiu matrimônio, independente do regime de bens adotado');
insert into marital_status (name, descricao) values('Divorciado(a)','Após a homologação do divórcio pela justiça ou por uma escritura pública.');
insert into marital_status (name, descricao) values('Viúvo(a)','Pessoa cujo cônjuge faleceu');
insert into marital_status (name, descricao) values('Separado Judicialmente','Quando a vida conjugal tenha entrado em ruptura, a lei permite que, por decisão conjunta ou individual dos cônjuges, se ponha termo à vida em comum');