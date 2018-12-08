-- -----------------------------------------------
-- (number of banco or banco codigo), the nome and website of financial institutions associated with and not associated with the Brazilian Federation of Banks (FEBRABAN).
-- -----------------------------------------------


CREATE TABLE banco (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(10),
    nome VARCHAR(150) NOT NULL,
    url VARCHAR(250),
    inativo BOOLEAN DEFAULT FALSE,

    data_criacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- -------------------------
-- PERMISSOES
-- -------------------------
INSERT INTO permissao (nome, descricao) values ('ROLE_ACESSAR_URI_BANCO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_LISTAR_BANCO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_DELETAR_BANCO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_ATUALIZAR_BANCO', '');
INSERT INTO permissao (nome, descricao) values ('ROLE_SALVAR_BANCO', '');
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ACESSAR_URI_BANCO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_LISTAR_BANCO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_DELETAR_BANCO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_ATUALIZAR_BANCO'));
INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES ((select id from usuario where email='willian.vag@gmail.com'), (select id from permissao where nome='ROLE_SALVAR_BANCO'));

-- -------------------------
-- BANCOS
-- -------------------------
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('1', 'Banco do Brasil S.A.', 'http://www.bb.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('3', 'Banco da Amazônia S.A.', 'http://www.bancoamazonia.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('4', 'Banco do Nordeste do Brasil S.A.', 'http://www.banconordeste.gov.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('8', 'Banco Santander Meridional S.A.', 'http://www.meridional.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('21', 'BANESTES S.A. Banco do Estado do Espírito Santo', 'http://www.banestes.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('24', 'Banco de Pernambuco S.A. - BANDEPE', 'http://www.bandepe.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('25', 'Banco Alfa S.A.', 'http://www.bancoalfa.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('27', 'Banco do Estado de Santa Catarina S.A.', 'http://www.besc.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('29', 'Banco Banerj S.A.', 'http://www.banerj.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('31', 'Banco Beg S.A.', 'http://www.itau.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('33', 'Banco do Estado de São Paulo S.A. - Banespa', 'http://www.banespa.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('36', 'Banco Bem S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('37', 'Banco do Estado do Pará S.A.', 'http://www.banparanet.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('38', 'Banco Banestado S.A.', 'http://www.banestado.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('40', 'Banco Cargill S.A.', 'http://www.bancocargill.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('41', 'Banco do Estado do Rio Grande do Sul S.A.', 'http://www.banrisul.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('45', 'Banco Opportunity S.A.', 'http://www.opportunity.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('47', 'Banco do Estado de Sergipe S.A.', 'http://www.banese.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('48', 'Banco Itaú BBA S.A.', 'http://www.itaubba.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('63', 'Banco Ibi S.A. Banco Múltiplo', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('72', 'Banco Rural Mais S.A.', 'http://www.rural.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('74', 'Banco J. Safra S.A.', 'http://www.jsafra.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('107', 'Banco BBM S.A.', 'http://www.bbmbank.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('116', 'Banco Único S.A.', 'http://www.unibanco.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('151', 'Banco Nossa Caixa S.A.', 'http://www.nossacaixa.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('175', 'Banco Finasa S.A.', 'http://www.finasa.com.br', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('208', 'Banco Pactual S.A.', 'http://www.pactual.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('214', 'Banco Dibens S.A.', 'http://www.bancodibens.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('215', 'Banco Comercial e de Investimento Sudameris S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('222', 'Banco Calyon Brasil S.A.', 'http://www.calyon.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('224', 'Banco Fibra S.A.', 'http://www.bancofibra.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('225', 'Banco Brascan S.A.', 'http://www.bancobrascan.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('229', 'Banco Cruzeiro do Sul S.A.', 'http://www.bcsul.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('233', 'Banco GE Capital S.A.', 'http://www.bancoge.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('237', 'Banco Bradesco S.A.', 'http://www.bradesco.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('246', 'Banco ABC Brasil S.A.', 'http://www.abcbrasil.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('247', 'Banco UBS S.A.', 'http://www.ubsw.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('248', 'Banco Boavista Interatlântico S.A.', 'não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('249', 'Banco Investcred Unibanco S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('250', 'Banco Schahin S.A.', 'http://www.bancoschahin.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('252', 'Banco Fininvest S.A.', 'http://www.fininvest.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('263', 'Banco Cacique S.A.', 'http://www.bancocacique.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('265', 'Banco Fator S.A.', 'http://www.bancofator.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('318', 'Banco BMG S.A.', 'http://www.bancobmg.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('320', 'Banco Industrial e Comercial S.A.', 'http://www.bicbanco.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('341', 'Banco Itaú S.A.', 'http://www.itau.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('347', 'Banco Sudameris Brasil S.A.', 'http://www.sudameris.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('351', 'Banco Santander S.A.', 'http://www.santander.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('353', 'Banco Santander Brasil S.A.', 'http://www.santander.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('356', 'Banco ABN AMRO Real S.A.', 'http://www.abnamro.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('366', 'Banco Société Générale Brasil S.A.', 'http://www.sgbrasil.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('370', 'Banco WestLB do Brasil S.A.', 'http://www.westlb.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('376', 'Banco J. P. Morgan S.A.', 'http://www.jpmorgan.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('389', 'Banco Mercantil do Brasil S.A.', 'http://www.mercantil.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('392', 'Banco Mercantil de São Paulo S.A.', 'não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('394', 'Banco BMC S.A.', 'http://www.bmc.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('422', 'Banco Safra S.A.', 'http://www.safra.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('453', 'Banco Rural S.A.', 'http://www.rural.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('456', 'Banco de Tokyo-Mitsubishi Brasil S.A.', 'http://www.btm.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('464', 'Banco Sumitomo Mitsui Brasileiro S.A.', 'http://não%20possue%20site/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('479', 'BankBoston Banco Múltiplo S.A.', 'http://www.bankboston.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('505', 'Banco Credit Suisse (Brasil) S.A.', 'http://www.csfb.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('600', 'Banco Luso Brasileiro S.A.', 'http://www.lusobrasileiro.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('604', 'Banco Industrial do Brasil S.A.', 'http://www.bancoindustrial.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('610', 'Banco VR S.A.', 'http://www.vr.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('611', 'Banco Paulista S.A.', 'http://www.bancopaulista.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('612', 'Banco Guanabara S.A.', 'não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('623', 'Banco Panomericano S.A.', 'http://www.panomericano.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('630', 'Banco Intercap S.A.', 'http://www.intercap.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('633', 'Banco Rendimento S.A.', 'http://www.rendimento.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('634', 'Banco Triângulo S.A.', 'http://www.tribanco.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('637', 'Banco Sofisa S.A.', 'http://www.sofisa.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('638', 'Banco Prosper S.A.', 'http://www.bancoprosper.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('641', 'Banco Alvorada S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('643', 'Banco Pine S.A.', 'http://www.bancopine.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('652', 'Banco Itaú Holding Financeira S.A.', 'http://www.itau.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('653', 'Banco Indusval S.A.', 'http://www.indusval.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('655', 'Banco Votorantim S.A.', 'http://www.bancovotorantim.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('707', 'Banco Daycoval S.A.', 'http://www.daycoval.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('719', 'Banif-Banco Internacional do Funchal (Brasil)S.A.', 'http://www.banif.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('734', 'Banco Gerdau S.A.', 'http://www.bancogerdau.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('740', 'Banco Barclays S.A.', 'http://www.barclays.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('744', 'BankBoston N.A.', 'http://www.bankboston.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('747', 'Banco Rabobank International Brasil S.A.', 'http://www.rabobank.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('748', 'Banco Cooperativo Sicredi S.A. - BANSICREDI', 'http://www.bansicredi.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('749', 'Banco Simples S.A.', 'http://www.bancosimples.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('745', 'Banco Citibank S.A.', 'http://www.citibank.com/brasil', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('752', 'Banco BNP Paribas Brasil S.A.', 'http://www.bnpparibas.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('757', 'Banco KEB do Brasil S.A.', 'não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('755', 'Banco Merrill Lynch de Investimentos S.A.', 'http://www.ml.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('756', 'Banco Cooperativo do Brasil S.A. - BANCOOB', 'http://www.bancoob.com.br/', true);

INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco American Express S.A.', 'http://www.aexp.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco de Lage Landen Brasil S.A.', 'http://www.delagelanden.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Fiat S.A.', 'http://www.bancofiat.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco General Motors S.A.', 'http://www.bancogm.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Itaucred Financiamentos S.A.', 'http://www.itaucred.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Itausaga S.A.', 'http://www.itau.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'BB Banco Popular do Brasil S.A.', 'http://www.bancopopulardobrasil.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'BES Investimento do Brasil S.A.-Banco de Investimento', 'http://www.besinvestimento.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('70', 'BRB - Banco de Brasília S.A.', 'http://www.brb.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('104', 'Caixa Econômica Federal', 'http://www.caixa.gov.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('477', 'Citibank N.A.', 'http://www.citibank.com/brasil', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Credicard Banco S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('487', 'Deutsche Banco S.A. - Banco Alemão', 'http://www.deutsche-banco.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('751', 'Dresdner Banco Brasil S.A. - Banco Múltiplo', 'http://www.drkw.net/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('210', 'Dresdner Banco Lateinomerika Aktiengesellschaft', 'http://www.dbla.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('62', 'Hipercard Banco Múltiplo S.A.', 'http://www.banco1.net/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('399', 'HSBC Banco Brasil S.A. - Banco Múltiplo', 'http://www.hsbc.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('492', 'ING Banco N.V.', 'http://www.ing.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('488', 'JPMorgan Chase Banco', 'http://www.jpmorganchase.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('65', 'Lemon Banco Banco Múltiplo S.A.', 'http://www.lemon.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('409', 'UNIBANCO - União de Bancos Brasileiros S.A.', 'http://www.unibanco.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('230', 'Unicard Banco Múltiplo S.A.', 'http://www.unicard.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('654', 'Banco A.J.Renner S.A.', 'http://www.bancorenner.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('213', 'Banco Arbi S.A.', 'http://www.arbi.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('739', 'Banco BGN S.A.', 'http://www.bgn.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('96', 'Banco BM&F de Serviços de Liquidação e Custódia S.A', 'http://www.bmf.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('218', 'Banco Bonsucesso S.A.', 'http://www.bancobonsucesso.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco BRJ S.A.', 'http://www.brjbank.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('44', 'Banco BVA S.A.', 'http://www.bancobva.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('412', 'Banco Capital S.A.', 'http://www.bancocapital.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('266', 'Banco Cédula S.A.', 'http://www.bancocedula.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('241', 'Banco Clássico S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco CNH Capital S.A.', 'http://www.bancocnh.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('753', 'Banco Comercial Uruguai S.A.', 'http://www.bancocomercial.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('75', 'Banco CR2 S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('721', 'Banco Credibel S.A.', 'http://www.credibel.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Daimlerchrysler S.A.', 'http://www.bancodaimlerchrysler.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('300', 'Banco de La Nacion Argentina', 'http://www.bna.com.ar/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('495', 'Banco de La Provincia de Buenos Aires', 'http://www.bapro.com.ar/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('494', 'Banco de La Republica Oriental del Uruguay', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('35', 'Banco do Estado do Ceará S.A. - BEC', 'http://www.bec.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('39', 'Banco do Estado do Piauí S.A. - BEP', 'http://www.bep.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('743', 'Banco Emblema S.A.', 'http://www.bancoemblema.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('626', 'Banco Ficsa S.A.', 'http://www.ficsa.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Ford S.A.', 'http://www.bancoford.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Honda S.A.', 'http://www.bancohonda.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco IBM S.A.', 'http://www.ibm.com/br/financing/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('217', 'Banco John Deere S.A.', 'http://www.johndeere.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('212', 'Banco Matone S.A.', 'http://www.bancomatone.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('243', 'Banco Máxima S.A.', 'http://www.bancomaxima.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Maxinvest S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('746', 'Banco Modal S.A.', 'http://www.bancomodal.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Moneo S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('738', 'Banco Morada S.A.', 'http://www.bancomorada.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('66', 'Banco Morgan Stanley Dean Witter S.A.', 'http://www.morganstanley.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Ourinvest S.A.', 'http://www.ourinvest.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('613', 'Banco Pecúnia S.A.', 'http://www.bancopecunia.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('724', 'Banco Porto Seguro S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('735', 'Banco Pottencial S.A.', 'http://www.pottencial.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco PSA Finance Brasil S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('741', 'Banco Ribeirão Preto S.A.', 'http://www.brp.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Rodobens', 'http://www.rodobens.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Toyota do Brasil S.A.', 'http://www.bancotoyota.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Tricury S.A.', 'http://www.tricury.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Volkswagen S.A.', 'http://www.bancovw.com.br/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'Banco Volvo (Brasil) S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES (NULL, 'BPN Brasil Banco Mútiplo S.A.', 'Não possui site', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('64', 'Goldman Sachs do Brasil Banco Múltiplo S.A.', 'http://www.goldmansachs.com/', true);
INSERT INTO banco (codigo, nome, url, inativo) VALUES ('254', 'Paraná Banco S.A.', 'http://www.paranabanco.com.br/', true);
