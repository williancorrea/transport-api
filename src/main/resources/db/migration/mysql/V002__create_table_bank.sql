-- -----------------------------------------------
-- (number of bank or bank code), the name and website of financial institutions associated with and not associated with the Brazilian Federation of Banks (FEBRABAN).
-- -----------------------------------------------


CREATE TABLE bank (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10),
    name VARCHAR(150) NOT NULL,
    url VARCHAR(250),
    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


INSERT INTO bank (code, name, url) VALUES ('1', 'Banco do Brasil S.A.', 'http://www.bb.com.br/');
INSERT INTO bank (code, name, url) VALUES ('3', 'Banco da Amazônia S.A.', 'http://www.bancoamazonia.com.br/');
INSERT INTO bank (code, name, url) VALUES ('4', 'Banco do Nordeste do Brasil S.A.', 'http://www.banconordeste.gov.br/');
INSERT INTO bank (code, name, url) VALUES ('8', 'Banco Santander Meridional S.A.', 'http://www.meridional.com.br/');
INSERT INTO bank (code, name, url) VALUES ('21', 'BANESTES S.A. Banco do Estado do Espírito Santo', 'http://www.banestes.com.br/');
INSERT INTO bank (code, name, url) VALUES ('24', 'Banco de Pernambuco S.A. - BANDEPE', 'http://www.bandepe.com.br/');
INSERT INTO bank (code, name, url) VALUES ('25', 'Banco Alfa S.A.', 'http://www.bancoalfa.com.br/');
INSERT INTO bank (code, name, url) VALUES ('27', 'Banco do Estado de Santa Catarina S.A.', 'http://www.besc.com.br/');
INSERT INTO bank (code, name, url) VALUES ('29', 'Banco Banerj S.A.', 'http://www.banerj.com.br/');
INSERT INTO bank (code, name, url) VALUES ('31', 'Banco Beg S.A.', 'http://www.itau.com.br/');
INSERT INTO bank (code, name, url) VALUES ('33', 'Banco do Estado de São Paulo S.A. - Banespa', 'http://www.banespa.com.br/');
INSERT INTO bank (code, name, url) VALUES ('36', 'Banco Bem S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('37', 'Banco do Estado do Pará S.A.', 'http://www.banparanet.com.br/');
INSERT INTO bank (code, name, url) VALUES ('38', 'Banco Banestado S.A.', 'http://www.banestado.com.br/');
INSERT INTO bank (code, name, url) VALUES ('40', 'Banco Cargill S.A.', 'http://www.bancocargill.com.br/');
INSERT INTO bank (code, name, url) VALUES ('41', 'Banco do Estado do Rio Grande do Sul S.A.', 'http://www.banrisul.com.br/');
INSERT INTO bank (code, name, url) VALUES ('45', 'Banco Opportunity S.A.', 'http://www.opportunity.com.br/');
INSERT INTO bank (code, name, url) VALUES ('47', 'Banco do Estado de Sergipe S.A.', 'http://www.banese.com.br/');
INSERT INTO bank (code, name, url) VALUES ('48', 'Banco Itaú BBA S.A.', 'http://www.itaubba.com.br/');
INSERT INTO bank (code, name, url) VALUES ('63', 'Banco Ibi S.A. Banco Múltiplo', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('72', 'Banco Rural Mais S.A.', 'http://www.rural.com.br/');
INSERT INTO bank (code, name, url) VALUES ('74', 'Banco J. Safra S.A.', 'http://www.jsafra.com.br/');
INSERT INTO bank (code, name, url) VALUES ('107', 'Banco BBM S.A.', 'http://www.bbmbank.com.br/');
INSERT INTO bank (code, name, url) VALUES ('116', 'Banco Único S.A.', 'http://www.unibanco.com.br/');
INSERT INTO bank (code, name, url) VALUES ('151', 'Banco Nossa Caixa S.A.', 'http://www.nossacaixa.com.br/');
INSERT INTO bank (code, name, url) VALUES ('175', 'Banco Finasa S.A.', 'http://www.finasa.com.br');
INSERT INTO bank (code, name, url) VALUES ('208', 'Banco Pactual S.A.', 'http://www.pactual.com.br/');
INSERT INTO bank (code, name, url) VALUES ('214', 'Banco Dibens S.A.', 'http://www.bancodibens.com.br/');
INSERT INTO bank (code, name, url) VALUES ('215', 'Banco Comercial e de Investimento Sudameris S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('222', 'Banco Calyon Brasil S.A.', 'http://www.calyon.com.br/');
INSERT INTO bank (code, name, url) VALUES ('224', 'Banco Fibra S.A.', 'http://www.bancofibra.com.br/');
INSERT INTO bank (code, name, url) VALUES ('225', 'Banco Brascan S.A.', 'http://www.bancobrascan.com.br/');
INSERT INTO bank (code, name, url) VALUES ('229', 'Banco Cruzeiro do Sul S.A.', 'http://www.bcsul.com.br/');
INSERT INTO bank (code, name, url) VALUES ('233', 'Banco GE Capital S.A.', 'http://www.bancoge.com.br/');
INSERT INTO bank (code, name, url) VALUES ('237', 'Banco Bradesco S.A.', 'http://www.bradesco.com.br/');
INSERT INTO bank (code, name, url) VALUES ('246', 'Banco ABC Brasil S.A.', 'http://www.abcbrasil.com.br/');
INSERT INTO bank (code, name, url) VALUES ('247', 'Banco UBS S.A.', 'http://www.ubsw.com/');
INSERT INTO bank (code, name, url) VALUES ('248', 'Banco Boavista Interatlântico S.A.', 'não possui site');
INSERT INTO bank (code, name, url) VALUES ('249', 'Banco Investcred Unibanco S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('250', 'Banco Schahin S.A.', 'http://www.bancoschahin.com.br/');
INSERT INTO bank (code, name, url) VALUES ('252', 'Banco Fininvest S.A.', 'http://www.fininvest.com.br/');
INSERT INTO bank (code, name, url) VALUES ('263', 'Banco Cacique S.A.', 'http://www.bancocacique.com.br/');
INSERT INTO bank (code, name, url) VALUES ('265', 'Banco Fator S.A.', 'http://www.bancofator.com.br/');
INSERT INTO bank (code, name, url) VALUES ('318', 'Banco BMG S.A.', 'http://www.bancobmg.com.br/');
INSERT INTO bank (code, name, url) VALUES ('320', 'Banco Industrial e Comercial S.A.', 'http://www.bicbanco.com.br/');
INSERT INTO bank (code, name, url) VALUES ('341', 'Banco Itaú S.A.', 'http://www.itau.com.br/');
INSERT INTO bank (code, name, url) VALUES ('347', 'Banco Sudameris Brasil S.A.', 'http://www.sudameris.com.br/');
INSERT INTO bank (code, name, url) VALUES ('351', 'Banco Santander S.A.', 'http://www.santander.com.br/');
INSERT INTO bank (code, name, url) VALUES ('353', 'Banco Santander Brasil S.A.', 'http://www.santander.com.br/');
INSERT INTO bank (code, name, url) VALUES ('356', 'Banco ABN AMRO Real S.A.', 'http://www.abnamro.com.br/');
INSERT INTO bank (code, name, url) VALUES ('366', 'Banco Société Générale Brasil S.A.', 'http://www.sgbrasil.com.br/');
INSERT INTO bank (code, name, url) VALUES ('370', 'Banco WestLB do Brasil S.A.', 'http://www.westlb.com.br/');
INSERT INTO bank (code, name, url) VALUES ('376', 'Banco J. P. Morgan S.A.', 'http://www.jpmorgan.com/');
INSERT INTO bank (code, name, url) VALUES ('389', 'Banco Mercantil do Brasil S.A.', 'http://www.mercantil.com.br/');
INSERT INTO bank (code, name, url) VALUES ('392', 'Banco Mercantil de São Paulo S.A.', 'não possui site');
INSERT INTO bank (code, name, url) VALUES ('394', 'Banco BMC S.A.', 'http://www.bmc.com.br/');
INSERT INTO bank (code, name, url) VALUES ('422', 'Banco Safra S.A.', 'http://www.safra.com.br/');
INSERT INTO bank (code, name, url) VALUES ('453', 'Banco Rural S.A.', 'http://www.rural.com.br/');
INSERT INTO bank (code, name, url) VALUES ('456', 'Banco de Tokyo-Mitsubishi Brasil S.A.', 'http://www.btm.com.br/');
INSERT INTO bank (code, name, url) VALUES ('464', 'Banco Sumitomo Mitsui Brasileiro S.A.', 'http://não%20possue%20site/');
INSERT INTO bank (code, name, url) VALUES ('479', 'BankBoston Banco Múltiplo S.A.', 'http://www.bankboston.com.br/');
INSERT INTO bank (code, name, url) VALUES ('505', 'Banco Credit Suisse (Brasil) S.A.', 'http://www.csfb.com/');
INSERT INTO bank (code, name, url) VALUES ('600', 'Banco Luso Brasileiro S.A.', 'http://www.lusobrasileiro.com.br/');
INSERT INTO bank (code, name, url) VALUES ('604', 'Banco Industrial do Brasil S.A.', 'http://www.bancoindustrial.com.br/');
INSERT INTO bank (code, name, url) VALUES ('610', 'Banco VR S.A.', 'http://www.vr.com.br/');
INSERT INTO bank (code, name, url) VALUES ('611', 'Banco Paulista S.A.', 'http://www.bancopaulista.com.br/');
INSERT INTO bank (code, name, url) VALUES ('612', 'Banco Guanabara S.A.', 'não possui site');
INSERT INTO bank (code, name, url) VALUES ('623', 'Banco Panamericano S.A.', 'http://www.panamericano.com.br/');
INSERT INTO bank (code, name, url) VALUES ('630', 'Banco Intercap S.A.', 'http://www.intercap.com.br/');
INSERT INTO bank (code, name, url) VALUES ('633', 'Banco Rendimento S.A.', 'http://www.rendimento.com.br/');
INSERT INTO bank (code, name, url) VALUES ('634', 'Banco Triângulo S.A.', 'http://www.tribanco.com.br/');
INSERT INTO bank (code, name, url) VALUES ('637', 'Banco Sofisa S.A.', 'http://www.sofisa.com.br/');
INSERT INTO bank (code, name, url) VALUES ('638', 'Banco Prosper S.A.', 'http://www.bancoprosper.com.br/');
INSERT INTO bank (code, name, url) VALUES ('641', 'Banco Alvorada S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('643', 'Banco Pine S.A.', 'http://www.bancopine.com.br/');
INSERT INTO bank (code, name, url) VALUES ('652', 'Banco Itaú Holding Financeira S.A.', 'http://www.itau.com.br/');
INSERT INTO bank (code, name, url) VALUES ('653', 'Banco Indusval S.A.', 'http://www.indusval.com.br/');
INSERT INTO bank (code, name, url) VALUES ('655', 'Banco Votorantim S.A.', 'http://www.bancovotorantim.com.br/');
INSERT INTO bank (code, name, url) VALUES ('707', 'Banco Daycoval S.A.', 'http://www.daycoval.com.br/');
INSERT INTO bank (code, name, url) VALUES ('719', 'Banif-Banco Internacional do Funchal (Brasil)S.A.', 'http://www.banif.com.br/');
INSERT INTO bank (code, name, url) VALUES ('734', 'Banco Gerdau S.A.', 'http://www.bancogerdau.com.br/');
INSERT INTO bank (code, name, url) VALUES ('740', 'Banco Barclays S.A.', 'http://www.barclays.com/');
INSERT INTO bank (code, name, url) VALUES ('744', 'BankBoston N.A.', 'http://www.bankboston.com.br/');
INSERT INTO bank (code, name, url) VALUES ('747', 'Banco Rabobank International Brasil S.A.', 'http://www.rabobank.com/');
INSERT INTO bank (code, name, url) VALUES ('748', 'Banco Cooperativo Sicredi S.A. - BANSICREDI', 'http://www.bansicredi.com.br/');
INSERT INTO bank (code, name, url) VALUES ('749', 'Banco Simples S.A.', 'http://www.bancosimples.com.br/');
INSERT INTO bank (code, name, url) VALUES ('745', 'Banco Citibank S.A.', 'http://www.citibank.com/brasil');
INSERT INTO bank (code, name, url) VALUES ('752', 'Banco BNP Paribas Brasil S.A.', 'http://www.bnpparibas.com.br/');
INSERT INTO bank (code, name, url) VALUES ('757', 'Banco KEB do Brasil S.A.', 'não possui site');
INSERT INTO bank (code, name, url) VALUES ('755', 'Banco Merrill Lynch de Investimentos S.A.', 'http://www.ml.com/');
INSERT INTO bank (code, name, url) VALUES ('756', 'Banco Cooperativo do Brasil S.A. - BANCOOB', 'http://www.bancoob.com.br/');

INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco American Express S.A.', 'http://www.aexp.com/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco de Lage Landen Brasil S.A.', 'http://www.delagelanden.com/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Fiat S.A.', 'http://www.bancofiat.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco General Motors S.A.', 'http://www.bancogm.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Itaucred Financiamentos S.A.', 'http://www.itaucred.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Itausaga S.A.', 'http://www.itau.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'BB Banco Popular do Brasil S.A.', 'http://www.bancopopulardobrasil.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'BES Investimento do Brasil S.A.-Banco de Investimento', 'http://www.besinvestimento.com.br/');
INSERT INTO bank (code, name, url) VALUES ('70', 'BRB - Banco de Brasília S.A.', 'http://www.brb.com.br/');
INSERT INTO bank (code, name, url) VALUES ('104', 'Caixa Econômica Federal', 'http://www.caixa.gov.br/');
INSERT INTO bank (code, name, url) VALUES ('477', 'Citibank N.A.', 'http://www.citibank.com/brasil');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Credicard Banco S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('487', 'Deutsche Bank S.A. - Banco Alemão', 'http://www.deutsche-bank.com.br/');
INSERT INTO bank (code, name, url) VALUES ('751', 'Dresdner Bank Brasil S.A. - Banco Múltiplo', 'http://www.drkw.net/');
INSERT INTO bank (code, name, url) VALUES ('210', 'Dresdner Bank Lateinamerika Aktiengesellschaft', 'http://www.dbla.com/');
INSERT INTO bank (code, name, url) VALUES ('62', 'Hipercard Banco Múltiplo S.A.', 'http://www.banco1.net/');
INSERT INTO bank (code, name, url) VALUES ('399', 'HSBC Bank Brasil S.A. - Banco Múltiplo', 'http://www.hsbc.com.br/');
INSERT INTO bank (code, name, url) VALUES ('492', 'ING Bank N.V.', 'http://www.ing.com/');
INSERT INTO bank (code, name, url) VALUES ('488', 'JPMorgan Chase Bank', 'http://www.jpmorganchase.com/');
INSERT INTO bank (code, name, url) VALUES ('65', 'Lemon Bank Banco Múltiplo S.A.', 'http://www.lemon.com/');
INSERT INTO bank (code, name, url) VALUES ('409', 'UNIBANCO - União de Bancos Brasileiros S.A.', 'http://www.unibanco.com.br/');
INSERT INTO bank (code, name, url) VALUES ('230', 'Unicard Banco Múltiplo S.A.', 'http://www.unicard.com.br/');
INSERT INTO bank (code, name, url) VALUES ('654', 'Banco A.J.Renner S.A.', 'http://www.bancorenner.com.br/');
INSERT INTO bank (code, name, url) VALUES ('213', 'Banco Arbi S.A.', 'http://www.arbi.com.br/');
INSERT INTO bank (code, name, url) VALUES ('739', 'Banco BGN S.A.', 'http://www.bgn.com.br/');
INSERT INTO bank (code, name, url) VALUES ('96', 'Banco BM&F de Serviços de Liquidação e Custódia S.A', 'http://www.bmf.com.br/');
INSERT INTO bank (code, name, url) VALUES ('218', 'Banco Bonsucesso S.A.', 'http://www.bancobonsucesso.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco BRJ S.A.', 'http://www.brjbank.com.br/');
INSERT INTO bank (code, name, url) VALUES ('44', 'Banco BVA S.A.', 'http://www.bancobva.com.br/');
INSERT INTO bank (code, name, url) VALUES ('412', 'Banco Capital S.A.', 'http://www.bancocapital.com.br/');
INSERT INTO bank (code, name, url) VALUES ('266', 'Banco Cédula S.A.', 'http://www.bancocedula.com.br/');
INSERT INTO bank (code, name, url) VALUES ('241', 'Banco Clássico S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco CNH Capital S.A.', 'http://www.bancocnh.com.br/');
INSERT INTO bank (code, name, url) VALUES ('753', 'Banco Comercial Uruguai S.A.', 'http://www.bancocomercial.com.br/');
INSERT INTO bank (code, name, url) VALUES ('75', 'Banco CR2 S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('721', 'Banco Credibel S.A.', 'http://www.credibel.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Daimlerchrysler S.A.', 'http://www.bancodaimlerchrysler.com.br/');
INSERT INTO bank (code, name, url) VALUES ('300', 'Banco de La Nacion Argentina', 'http://www.bna.com.ar/');
INSERT INTO bank (code, name, url) VALUES ('495', 'Banco de La Provincia de Buenos Aires', 'http://www.bapro.com.ar/');
INSERT INTO bank (code, name, url) VALUES ('494', 'Banco de La Republica Oriental del Uruguay', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('35', 'Banco do Estado do Ceará S.A. - BEC', 'http://www.bec.com.br/');
INSERT INTO bank (code, name, url) VALUES ('39', 'Banco do Estado do Piauí S.A. - BEP', 'http://www.bep.com.br/');
INSERT INTO bank (code, name, url) VALUES ('743', 'Banco Emblema S.A.', 'http://www.bancoemblema.com.br/');
INSERT INTO bank (code, name, url) VALUES ('626', 'Banco Ficsa S.A.', 'http://www.ficsa.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Ford S.A.', 'http://www.bancoford.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Honda S.A.', 'http://www.bancohonda.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco IBM S.A.', 'http://www.ibm.com/br/financing/');
INSERT INTO bank (code, name, url) VALUES ('217', 'Banco John Deere S.A.', 'http://www.johndeere.com.br/');
INSERT INTO bank (code, name, url) VALUES ('212', 'Banco Matone S.A.', 'http://www.bancomatone.com.br/');
INSERT INTO bank (code, name, url) VALUES ('243', 'Banco Máxima S.A.', 'http://www.bancomaxima.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Maxinvest S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('746', 'Banco Modal S.A.', 'http://www.bancomodal.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Moneo S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('738', 'Banco Morada S.A.', 'http://www.bancomorada.com.br/');
INSERT INTO bank (code, name, url) VALUES ('66', 'Banco Morgan Stanley Dean Witter S.A.', 'http://www.morganstanley.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Ourinvest S.A.', 'http://www.ourinvest.com.br/');
INSERT INTO bank (code, name, url) VALUES ('613', 'Banco Pecúnia S.A.', 'http://www.bancopecunia.com.br/');
INSERT INTO bank (code, name, url) VALUES ('724', 'Banco Porto Seguro S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('735', 'Banco Pottencial S.A.', 'http://www.pottencial.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco PSA Finance Brasil S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('741', 'Banco Ribeirão Preto S.A.', 'http://www.brp.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Rodobens', 'http://www.rodobens.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Toyota do Brasil S.A.', 'http://www.bancotoyota.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Tricury S.A.', 'http://www.tricury.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Volkswagen S.A.', 'http://www.bancovw.com.br/');
INSERT INTO bank (code, name, url) VALUES (NULL, 'Banco Volvo (Brasil) S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES (NULL, 'BPN Brasil Banco Mútiplo S.A.', 'Não possui site');
INSERT INTO bank (code, name, url) VALUES ('64', 'Goldman Sachs do Brasil Banco Múltiplo S.A.', 'http://www.goldmansachs.com/');
INSERT INTO bank (code, name, url) VALUES ('254', 'Paraná Banco S.A.', 'http://www.paranabanco.com.br/');
