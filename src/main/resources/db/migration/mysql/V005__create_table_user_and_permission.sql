CREATE TABLE user (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(250) NOT NULL,
	email VARCHAR(200) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_permission (
	id_user BIGINT(20) NOT NULL,
	id_permission BIGINT(20) NOT NULL,
	PRIMARY KEY (id_user, id_permission),
	FOREIGN KEY (id_user) REFERENCES user(id),
	FOREIGN KEY (id_permission) REFERENCES permission(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# USER
#password admin
INSERT INTO user (name, email, password) VALUES ('Administrator', 'willian.vag@gmail.com', '$2a$10$LfoVSB5m5A5Fey2Y90.owOYvYaSFaWwMmGB9N7qSSdeHwcyhAA2R6');

# PERMISSION
INSERT INTO permission (name, description) values ('ROLE_LIST_BANK', '');
INSERT INTO permission (name, description) values ('ROLE_DELETE_BANK', ' ');
INSERT INTO permission (name, description) values ('ROLE_UPDATE_BANK', ' ');
INSERT INTO permission (name, description) values ('ROLE_SAVE_BANK', ' ');

INSERT INTO permission (name, description) values ('ROLE_LIST_COUNTRY', '');
INSERT INTO permission (name, description) values ('ROLE_DELETE_COUNTRY', ' ');
INSERT INTO permission (name, description) values ('ROLE_UPDATE_COUNTRY', ' ');
INSERT INTO permission (name, description) values ('ROLE_SAVE_COUNTRY', ' ');

INSERT INTO permission (name, description) values ('ROLE_LIST_PRODUCT-UNIT', '');
INSERT INTO permission (name, description) values ('ROLE_DELETE_PRODUCT-UNIT', ' ');
INSERT INTO permission (name, description) values ('ROLE_UPDATE_PRODUCT-UNIT', ' ');
INSERT INTO permission (name, description) values ('ROLE_SAVE_PRODUCT-UNIT', ' ');

INSERT INTO permission (name, description) values ('ROLE_LIST_TYPE-RELATIONSHIP', '');
INSERT INTO permission (name, description) values ('ROLE_DELETE_TYPE-RELATIONSHIP', ' ');
INSERT INTO permission (name, description) values ('ROLE_UPDATE_TYPE-RELATIONSHIP', ' ');
INSERT INTO permission (name, description) values ('ROLE_SAVE_TYPE-RELATIONSHIP', ' ');

INSERT INTO permission (name, description) values ('ROLE_LIST_LEVEL-OF-EDUCATION', '');
INSERT INTO permission (name, description) values ('ROLE_DELETE_LEVEL-OF-EDUCATION', ' ');
INSERT INTO permission (name, description) values ('ROLE_UPDATE_LEVEL-OF-EDUCATION', ' ');
INSERT INTO permission (name, description) values ('ROLE_SAVE_LEVEL-OF-EDUCATION', ' ');

INSERT INTO permission (name, description) values ('ROLE_LIST_ZIP-CODE', ' ');



INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_BANK'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETE_BANK'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_UPDATE_BANK'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SAVE_BANK'));

INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_COUNTRY'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETE_COUNTRY'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_UPDATE_COUNTRY'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SAVE_COUNTRY'));

INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_PRODUCT-UNIT'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETE_PRODUCT-UNIT'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_UPDATE_PRODUCT-UNIT'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SAVE_PRODUCT-UNIT'));

INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_TYPE-RELATIONSHIP'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETE_TYPE-RELATIONSHIP'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_UPDATE_TYPE-RELATIONSHIP'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SAVE_TYPE-RELATIONSHIP'));

INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_LEVEL-OF-EDUCATION'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_DELETE_LEVEL-OF-EDUCATION'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_UPDATE_LEVEL-OF-EDUCATION'));
INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_SAVE_LEVEL-OF-EDUCATION'));

INSERT INTO user_permission (id_user, id_permission) VALUES ((select id from user where email='willian.vag@gmail.com'), (select id from permission where name='ROLE_LIST_ZIP-CODE'));

