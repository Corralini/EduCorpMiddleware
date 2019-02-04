-- DML

CREATE SCHEMA IF NOT EXISTS `educorp` DEFAULT CHARACTER SET utf8 ;
USE `educorp` ;

-- -----------------------------------------------------
-- Table `educorp`.`PAIS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`PAIS` (
  `ID_PAIS` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`ID_PAIS`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`IDIOMA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`IDIOMA` (
  `ID_IDIOMA` VARCHAR(5) NOT NULL,
  `IDIOMA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_IDIOMA`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`GENERO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`GENERO` (
  `ID_GENERO` VARCHAR(4) NOT NULL,
  `GENERO` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_GENERO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`NIVEL_INGLES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`NIVEL_INGLES` (
  `ID_NIVEL` INT NOT NULL AUTO_INCREMENT,
  `NIVEL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_NIVEL`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`PROFESOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`PROFESOR` (
  `ID_PROFESOR` INT NOT NULL AUTO_INCREMENT,
  `EMAIL` VARCHAR(256) NOT NULL,
  `PSSWD` VARCHAR(255) NOT NULL,
  `ID_PAIS` VARCHAR(8) NOT NULL,
  `NOMBRE` VARCHAR(45) NOT NULL,
  `APELLIDO1` VARCHAR(45) NOT NULL,
  `APELLIDO2` VARCHAR(45) NULL,
  `ANO_NACIMIENTO` INT NOT NULL,
  `FECHA_SUBSCRIPCION` DATETIME NOT NULL,
  `PRECIO_SESION` DOUBLE NOT NULL,
  `ID_IDIOMA` VARCHAR(5) NOT NULL,
  `ID_GENERO` VARCHAR(4) NOT NULL,
  `ID_NIVEL` INT NOT NULL,
  `ACTIVADA` INT(1) NOT NULL DEFAULT 0,
  `DESCRIPCION` VARCHAR(255) NULL DEFAULT 'Sin descripcion',
  `CODIGO_DE_RECUPERACION` INT(6) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_PROFESOR`),
  INDEX `fk_PROFESOR_PAIS1_idx` (`ID_PAIS` ASC) VISIBLE,
  INDEX `fk_PROFESOR_Idioma1_idx` (`ID_IDIOMA` ASC) VISIBLE,
  INDEX `fk_PROFESOR_GENERO1_idx` (`ID_GENERO` ASC) VISIBLE,
  INDEX `fk_PROFESOR_NIVEL_INGLES1_idx` (`ID_NIVEL` ASC) VISIBLE,
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
  CONSTRAINT `fk_PROFESOR_PAIS1`
    FOREIGN KEY (`ID_PAIS`)
    REFERENCES `educorp`.`PAIS` (`ID_PAIS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROFESOR_Idioma1`
    FOREIGN KEY (`ID_IDIOMA`)
    REFERENCES `educorp`.`IDIOMA` (`ID_IDIOMA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROFESOR_GENERO1`
    FOREIGN KEY (`ID_GENERO`)
    REFERENCES `educorp`.`GENERO` (`ID_GENERO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROFESOR_NIVEL_INGLES1`
    FOREIGN KEY (`ID_NIVEL`)
    REFERENCES `educorp`.`NIVEL_INGLES` (`ID_NIVEL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`ESTUDIANTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`ESTUDIANTE` (
  `ID_ESTUDIANTE` INT NOT NULL AUTO_INCREMENT,
  `EMAIL` VARCHAR(256) NOT NULL,
  `ID_PAIS` VARCHAR(8) NOT NULL,
  `PSSWD` VARCHAR(255) NOT NULL,
  `NOMBRE` VARCHAR(45) NOT NULL,
  `APELLIDO1` VARCHAR(45) NOT NULL,
  `APELLIDO2` VARCHAR(45) NULL,
  `ANO_NACIMIENTO` INT NOT NULL,
  `FECHA_SUBSCRIPCION` DATETIME NOT NULL,
  `ID_NIVEL` INT NOT NULL,
  `ID_GENERO` VARCHAR(4) NOT NULL,
  `CODIGO_DE_RECUPERACION` INT(6) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_ESTUDIANTE`),
  INDEX `fk_ESTUDIANTE_PAIS1_idx` (`ID_PAIS` ASC) VISIBLE,
  INDEX `fk_ESTUDIANTE_NIVEL_INGLES1_idx` (`ID_NIVEL` ASC) VISIBLE,
  INDEX `fk_ESTUDIANTE_GENERO1_idx` (`ID_GENERO` ASC) VISIBLE,
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
  CONSTRAINT `fk_ESTUDIANTE_PAIS1`
    FOREIGN KEY (`ID_PAIS`)
    REFERENCES `educorp`.`PAIS` (`ID_PAIS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ESTUDIANTE_NIVEL_INGLES1`
    FOREIGN KEY (`ID_NIVEL`)
    REFERENCES `educorp`.`NIVEL_INGLES` (`ID_NIVEL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ESTUDIANTE_GENERO1`
    FOREIGN KEY (`ID_GENERO`)
    REFERENCES `educorp`.`GENERO` (`ID_GENERO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`DIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`DIA` (
  `ID_DIA` INT NOT NULL AUTO_INCREMENT,
  `DIA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_DIA`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`Hora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`Hora` (
  `ID_HORA` INT NOT NULL AUTO_INCREMENT,
  `HORA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_HORA`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`HORARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`HORARIO` (
  `ID_HORARIO` INT NOT NULL AUTO_INCREMENT,
  `ID_PROFESOR` INT NOT NULL,
  `ID_DIA` INT NOT NULL,
  `ID_HORA` INT NOT NULL,
  PRIMARY KEY (`ID_HORARIO`),
  INDEX `fk_HORARIO_PROFESOR1_idx` (`ID_PROFESOR` ASC) VISIBLE,
  INDEX `fk_HORARIO_DIA1_idx` (`ID_DIA` ASC) VISIBLE,
  INDEX `fk_HORARIO_Hora1_idx` (`ID_HORA` ASC) VISIBLE,
  UNIQUE INDEX `ID_PROFESOR_UNIQUE` (`ID_DIA` ASC, `ID_HORA` ASC, `ID_PROFESOR` ASC) VISIBLE,
  CONSTRAINT `fk_HORARIO_PROFESOR1`
    FOREIGN KEY (`ID_PROFESOR`)
    REFERENCES `educorp`.`PROFESOR` (`ID_PROFESOR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HORARIO_DIA1`
    FOREIGN KEY (`ID_DIA`)
    REFERENCES `educorp`.`DIA` (`ID_DIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HORARIO_Hora1`
    FOREIGN KEY (`ID_HORA`)
    REFERENCES `educorp`.`Hora` (`ID_HORA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`PROFESOR_PUNTUA_ESTUDIANTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`PROFESOR_PUNTUA_ESTUDIANTE` (
  `ID_PROFESOR` INT NOT NULL,
  `ID_ESTUDIANTE` INT NOT NULL,
  `FECHA_PUNTUACION` DATETIME NOT NULL,
  `PUNTUACION` DOUBLE NOT NULL,
  PRIMARY KEY (`ID_PROFESOR`, `ID_ESTUDIANTE`, `FECHA_PUNTUACION`),
  INDEX `fk_USUARIO_PUNTUA_USUARIO_PROFESOR1_idx` (`ID_PROFESOR` ASC) VISIBLE,
  INDEX `fk_USUARIO_PUNTUA_USUARIO_ESTUDIANTE1_idx` (`ID_ESTUDIANTE` ASC) VISIBLE,
  CONSTRAINT `fk_USUARIO_PUNTUA_USUARIO_PROFESOR1`
    FOREIGN KEY (`ID_PROFESOR`)
    REFERENCES `educorp`.`PROFESOR` (`ID_PROFESOR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_PUNTUA_USUARIO_ESTUDIANTE1`
    FOREIGN KEY (`ID_ESTUDIANTE`)
    REFERENCES `educorp`.`ESTUDIANTE` (`ID_ESTUDIANTE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`ESTADO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`ESTADO` (
  `ID_ESTADO` CHAR(1) NOT NULL,
  `ESTADO` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_ESTADO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`SESION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`SESION` (
  `ID_SESION` INT NOT NULL AUTO_INCREMENT,
  `ID_PROFESOR` INT NOT NULL,
  `ID_ESTUDIANTE` INT NOT NULL,
  `FECHA_SESION` DATETIME NOT NULL,
  `ID_HORARIO` INT NOT NULL,
  `FECHA_INICIO` DATETIME NULL,
  `FECHA_FIN` DATETIME NULL,
  `PRECIO` DOUBLE NOT NULL,
  `ID_ESTADO` CHAR(1) NOT NULL,
  `FECHA_CAMBIO_ESTADO` DATETIME NOT NULL,
  PRIMARY KEY (`ID_SESION`, `ID_PROFESOR`, `ID_ESTUDIANTE`),
  INDEX `fk_SESION_ESTADO1_idx` (`ID_ESTADO` ASC) VISIBLE,
  INDEX `fk_SESION_HORARIO1_idx` (`ID_HORARIO` ASC) VISIBLE,
  INDEX `fk_SESION_PROFESOR1_idx` (`ID_PROFESOR` ASC) VISIBLE,
  INDEX `fk_SESION_ESTUDIANTE1_idx` (`ID_ESTUDIANTE` ASC) VISIBLE,
  CONSTRAINT `fk_SESION_ESTADO1`
    FOREIGN KEY (`ID_ESTADO`)
    REFERENCES `educorp`.`ESTADO` (`ID_ESTADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SESION_HORARIO1`
    FOREIGN KEY (`ID_HORARIO`)
    REFERENCES `educorp`.`HORARIO` (`ID_HORARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SESION_PROFESOR1`
    FOREIGN KEY (`ID_PROFESOR`)
    REFERENCES `educorp`.`PROFESOR` (`ID_PROFESOR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SESION_ESTUDIANTE1`
    FOREIGN KEY (`ID_ESTUDIANTE`)
    REFERENCES `educorp`.`ESTUDIANTE` (`ID_ESTUDIANTE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`ESTUDIANTE_PUNTUA_PROFESOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`ESTUDIANTE_PUNTUA_PROFESOR` (
  `ID_ESTUDIANTE` INT NOT NULL,
  `ID_PROFESOR` INT NOT NULL,
  `FECHA_PUNTUACION` DATETIME NOT NULL,
  `PUNTUACION` DOUBLE NOT NULL,
  PRIMARY KEY (`ID_ESTUDIANTE`, `ID_PROFESOR`, `FECHA_PUNTUACION`),
  INDEX `fk_USUARIO_PUNTUA_USUARIO_PROFESOR1_idx` (`ID_PROFESOR` ASC) VISIBLE,
  INDEX `fk_USUARIO_PUNTUA_USUARIO_ESTUDIANTE1_idx` (`ID_ESTUDIANTE` ASC) VISIBLE,
  CONSTRAINT `fk_USUARIO_PUNTUA_USUARIO_PROFESOR10`
    FOREIGN KEY (`ID_PROFESOR`)
    REFERENCES `educorp`.`PROFESOR` (`ID_PROFESOR`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_PUNTUA_USUARIO_ESTUDIANTE10`
    FOREIGN KEY (`ID_ESTUDIANTE`)
    REFERENCES `educorp`.`ESTUDIANTE` (`ID_ESTUDIANTE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`IDIOMA_PAGINA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`IDIOMA_PAGINA` (
  `ID` VARCHAR(3) NOT NULL,
  `IDIOMA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `educorp`.`IDIOMA_PAGINA_PAIS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `educorp`.`IDIOMA_PAGINA_PAIS` (
  `ID_IDIOMA_PAGINA` VARCHAR(3) NOT NULL,
  `ID_PAIS` VARCHAR(2) NOT NULL,
  `PAIS` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_IDIOMA_PAGINA`, `ID_PAIS`),
  INDEX `fk_IDIOMA_PAGINA_has_PAIS_PAIS1_idx` (`ID_PAIS` ASC) VISIBLE,
  INDEX `fk_IDIOMA_PAGINA_has_PAIS_IDIOMA_PAGINA1_idx` (`ID_IDIOMA_PAGINA` ASC) VISIBLE,
  CONSTRAINT `fk_IDIOMA_PAGINA_has_PAIS_IDIOMA_PAGINA1`
    FOREIGN KEY (`ID_IDIOMA_PAGINA`)
    REFERENCES `educorp`.`IDIOMA_PAGINA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_IDIOMA_PAGINA_has_PAIS_PAIS1`
    FOREIGN KEY (`ID_PAIS`)
    REFERENCES `educorp`.`PAIS` (`ID_PAIS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

    
-- DDL

INSERT INTO DIA (DIA) VALUES
("Domingo"),
("Lunes"),
("Martes"),
("Miércoles"),
("Jueves"),
("Viernes"),
("Sábado");



INSERT INTO HORA ( HORA) VALUES
("0:00"),
("0:15"),
("0:30"),
("0:45"),
("1:00"),
("1:15"),
("1:30"),
("1:45"),
("2:00"),
("2:15"),
("2:30"),
("2:45"),
("3:00"),
("3:15"),
("3:30"),
("3:45"),
("4:00"),
("4:15"),
("4:30"),
("4:45"),
("5:00"),
("5:15"),
("5:30"),
("5:45"),
("6:00"),
("6:15"),
("6:30"),
("6:45"),
("7:00"),
("7:15"),
("7:30"),
("7:45"),
("8:00"),
("8:15"),
("8:30"),
("8:45"),
("9:00"),
("9:15"),
("9:30"),
("9:45"),
("10:00"),
("10:15"),
("10:30"),
("10:45"),
("11:00"),
("11:15"),
("11:30"),
("11:45"),
("12:00"),
("12:15"),
("12:30"),
("12:45"),
("13:00"),
("13:15"),
("13:30"),
("13:45"),
("14:00"),
("14:15"),
("14:30"),
("14:45"),
("15:00"),
("15:15"),
("15:30"),
("15:45"),
("16:00"),
("16:15"),
("16:30"),
("16:45"),
("17:00"),
("17:15"),
("17:30"),
("17:45"),
("18:00"),
("18:15"),
("18:30"),
("18:45"),
("19:00"),
("19:15"),
("19:30"),
("19:45"),
("20:00"),
("20:15"),
("20:30"),
("20:45"),
("21:00"),
("21:15"),
("21:30"),
("21:45"),
("22:00"),
("22:15"),
("22:30"),
("22:45"),
("23:00"),
("23:15"),
("23:30"),
("23:45");



INSERT INTO PAIS (ID_PAIS) VALUES
('AF'),
('AL'),
('DE'),
('DZ'),
('AD'),
('AO'),
('AI'),
('AQ'),
('AG'),
('AN'),
('SA'),
('AR'),
('AM'),
('AW'),
('AU'),
('AT'),
('AZ'),
('BE'),
('BS'),
('BH'),
('BD'),
('BB'),
('BZ'),
('BJ'),
('BT'),
('BY'),
('MM'),
('BO'),
('BA'),
('BW'),
('BR'),
('BN'),
('BG'),
('BF'),
('BI'),
('CV'),
('KH'),
('CM'),
('CA'),
('TD'),
('CL'),
('CN'),
('CY'),
('VA'),
('CO'),
('KM'),
('CG'),
('CD'),
('KP'),
('KR'),
('CI'),
('CR'),
('HR'),
('CU'),
('DK'),
('DM'),
('EC'),
('EG'),
('SV'),
('AE'),
('ER'),
('SK'),
('SI'),
('ES'),
('US'),
('EE'),
('ET'),
('PH'),
('FI'),
('FJ'),
('FR'),
('GA'),
('GM'),
('GE'),
('GH'),
('GI'),
('GD'),
('GR'),
('GL'),
('GP'),
('GU'),
('GT'),
('GF'),
('GG'),
('GN'),
('GQ'),
('GW'),
('GY'),
('HT'),
('HN'),
('HK'),
('HU'),
('IN'),
('ID'),
('IR'),
('IQ'),
('IE'),
('BV'),
('IM'),
('CX'),
('NF'),
('IS'),
('BM'),
('KY'),
('CC'),
('CK'),
('AX'),
('FO'),
('GS'),
('HM'),
('MV'),
('FK'),
('MP'),
('MH'),
('PN'),
('SB'),
('TC'),
('UM'),
('VG'),
('VI'),
('IL'),
('IT'),
('JM'),
('JP'),
('JE'),
('JO'),
('KZ'),
('KE'),
('KG'),
('KI'),
('KW'),
('LB'),
('LA'),
('LS'),
('LV'),
('LR'),
('LY'),
('LI'),
('LT'),
('LU'),
('MX'),
('MC'),
('MO'),
('MK'),
('MG'),
('MY'),
('MW'),
('ML'),
('MT'),
('MA'),
('MQ'),
('MU'),
('MR'),
('YT'),
('FM'),
('MD'),
('MN'),
('ME'),
('MS'),
('MZ'),
('NA'),
('NR'),
('NP'),
('NI'),
('NE'),
('NG'),
('NU'),
('NO'),
('NC'),
('NZ'),
('OM'),
('NL'),
('PK'),
('PW'),
('PS'),
('PA'),
('PG'),
('PY'),
('PE'),
('PF'),
('PL'),
('PT'),
('PR'),
('QA'),
('GB'),
('CF'),
('CZ'),
('DO'),
('RE'),
('RW'),
('RO'),
('RU'),
('EH'),
('WS'),
('AS'),
('BL'),
('KN'),
('SM'),
('MF'),
('PM'),
('VC'),
('SH'),
('LC'),
('ST'),
('SN'),
('RS'),
('SC'),
('SL'),
('SG'),
('SY'),
('SO'),
('LK'),
('ZA'),
('SD'),
('SE'),
('CH'),
('SR'),
('SJ'),
('SZ'),
('TJ'),
('TH'),
('TW'),
('TZ'),
('IO'),
('TF'),
('TL'),
('TG'),
('TK'),
('TO'),
('TT'),
('TN'),
('TM'),
('TR'),
('TV'),
('UA'),
('UG'),
('UY'),
('UZ'),
('VU'),
('VE'),
('VN'),
('WF'),
('YE'),
('DJ'),
('ZM'),
('ZW');

INSERT INTO IDIOMA_PAGINA (ID, IDIOMA) VALUES
('en', 'English'),
('es', 'Español');

INSERT INTO IDIOMA_PAGINA_PAIS (ID_IDIOMA_PAGINA, ID_PAIS, PAIS) VALUES
('en','AF', 'Afghanistan'),
('en','AL', 'Albania'),
('en','DE', 'Germany'),
('en','DZ', 'Algeria'),
('en','AD', 'Andorra'),
('en','AO', 'Angola'),
('en','AI', 'Anguilla'),
('en','AQ', 'Antarctica'),
('en','AG', 'Antigua and Barbuda'),
('en','AN', 'Netherlands Antilles'),
('en','SA', 'Saudi Arabia'),
('en','AR', 'Argentina'),
('en','AM', 'Armenia'),
('en','AW', 'Aruba'),
('en','AU', 'Australia'),
('en','AT', 'Austria'),
('en','AZ', 'Azerbaijan'),
('en','BE', 'Belgium'),
('en','BS', 'Bahamas'),
('en','BH', 'Bahrain'),
('en','BD', 'Bangladesh'),
('en','BB', 'Barbados'),
('en','BZ', 'Belize'),
('en','BJ', 'Benin'),
('en','BT', 'Bhutan'),
('en','BY', 'Belarus'),
('en','MM', 'Myanmar'),
('en','BO', 'Bolivia'),
('en','BA', 'Bosnia and Herzegovina'),
('en','BW', 'Botswana'),
('en','BR', 'Brazil'),
('en','BN', 'Brunei'),
('en','BG', 'Bulgaria'),
('en','BF', 'Burkina Faso'),
('en','BI', 'Burundi'),
('en','CV', 'Cape Verde'),
('en','KH', 'Cambodia'),
('en','CM', 'Cameroon'),
('en','CA', 'Canada'),
('en','TD', 'Chad'),
('en','CL', 'Chile'),
('en','CN', 'China'),
('en','CY', 'Cyprus'),
('en','VA', 'Vatican City State'),
('en','CO', 'Colombia'),
('en','KM', 'Comoros'),
('en','CG', 'Congo'),
('en','CD', 'Congo'),
('en','KP', 'North Korea'),
('en','KR', 'South Korea'),
('en','CI', 'Ivory Coast'),
('en','CR', 'Costa Rica'),
('en','HR', 'Croatia'),
('en','CU', 'Cuba'),
('en','DK', 'Denmark'),
('en','DM', 'Dominica'),
('en','EC', 'Ecuador'),
('en','EG', 'Egypt'),
('en','SV', 'El Salvador'),
('en','AE', 'United Arab Emirates'),
('en','ER', 'Eritrea'),
('en','SK', 'Slovakia'),
('en','SI', 'Slovenia'),
('en','ES', 'Spain'),
('en','US', 'United States of America'),
('en','EE', 'Estonia'),
('en','ET', 'Ethiopia'),
('en','PH', 'Philippines'),
('en','FI', 'Finland'),
('en','FJ', 'Fiji'),
('en','FR', 'France'),
('en','GA', 'Gabon'),
('en','GM', 'Gambia'),
('en','GE', 'Georgia'),
('en','GH', 'Ghana'),
('en','GI', 'Gibraltar'),
('en','GD', 'Grenada'),
('en','GR', 'Greece'),
('en','GL', 'Greenland'),
('en','GP', 'Guadeloupe'),
('en','GU', 'Guam'),
('en','GT', 'Guatemala'),
('en','GF', 'French Guiana'),
('en','GG', 'Guernsey'),
('en','GN', 'Guinea'),
('en','GQ', 'Equatorial Guinea'),
('en','GW', 'Guinea-Bissau'),
('en','GY', 'Guyana'),
('en','HT', 'Haiti'),
('en','HN', 'Honduras'),
('en','HK', 'Hong Kong'),
('en','HU', 'Hungary'),
('en','IN', 'India'),
('en','ID', 'Indonesia'),
('en','IR', 'Iran'),
('en','IQ', 'Iraq'),
('en','IE', 'Ireland'),
('en','BV', 'Bouvet Island'),
('en','IM', 'Isle of Man'),
('en','CX', 'Christmas Island'),
('en','NF', 'Norfolk Island'),
('en','IS', 'Iceland'),
('en','BM', 'Bermuda Islands'),
('en','KY', 'Cayman Islands'),
('en','CC', 'Cocos (Keeling) Islands'),
('en','CK', 'Cook Islands'),
('en','AX', 'Ã…land Islands'),
('en','FO', 'Faroe Islands'),
('en','GS', 'South Georgia and the South Sandwich Islands'),
('en','HM', 'Heard Island and McDonald Islands'),
('en','MV', 'Maldives'),
('en','FK', 'Falkland Islands (Malvinas)'),
('en','MP', 'Northern Mariana Islands'),
('en','MH', 'Marshall Islands'),
('en','PN', 'Pitcairn Islands'),
('en','SB', 'Solomon Islands'),
('en','TC', 'Turks and Caicos Islands'),
('en','UM', 'United States Minor Outlying Islands'),
('en','VG', 'Virgin Islands'),
('en','VI', 'United States Virgin Islands'),
('en','IL', 'Israel'),
('en','IT', 'Italy'),
('en','JM', 'Jamaica'),
('en','JP', 'Japan'),
('en','JE', 'Jersey'),
('en','JO', 'Jordan'),
('en','KZ', 'Kazakhstan'),
('en','KE', 'Kenya'),
('en','KG', 'Kyrgyzstan'),
('en','KI', 'Kiribati'),
('en','KW', 'Kuwait'),
('en','LB', 'Lebanon'),
('en','LA', 'Laos'),
('en','LS', 'Lesotho'),
('en','LV', 'Latvia'),
('en','LR', 'Liberia'),
('en','LY', 'Libya'),
('en','LI', 'Liechtenstein'),
('en','LT', 'Lithuania'),
('en','LU', 'Luxembourg'),
('en','MX', 'Mexico'),
('en','MC', 'Monaco'),
('en','MO', 'Macao'),
('en','MK', 'Macedonia'),
('en','MG', 'Madagascar'),
('en','MY', 'Malaysia'),
('en','MW', 'Malawi'),
('en','ML', 'Mali'),
('en','MT', 'Malta'),
('en','MA', 'Morocco'),
('en','MQ', 'Martinique'),
('en','MU', 'Mauritius'),
('en','MR', 'Mauritania'),
('en','YT', 'Mayotte'),
('en','FM', 'Estados Federados de'),
('en','MD', 'Moldova'),
('en','MN', 'Mongolia'),
('en','ME', 'Montenegro'),
('en','MS', 'Montserrat'),
('en','MZ', 'Mozambique'),
('en','NA', 'Namibia'),
('en','NR', 'Nauru'),
('en','NP', 'Nepal'),
('en','NI', 'Nicaragua'),
('en','NE', 'Niger'),
('en','NG', 'Nigeria'),
('en','NU', 'Niue'),
('en','NO', 'Norway'),
('en','NC', 'New Caledonia'),
('en','NZ', 'New Zealand'),
('en','OM', 'Oman'),
('en','NL', 'Netherlands'),
('en','PK', 'Pakistan'),
('en','PW', 'Palau'),
('en','PS', 'Palestine'),
('en','PA', 'Panama'),
('en','PG', 'Papua New Guinea'),
('en','PY', 'Paraguay'),
('en','PE', 'Peru'),
('en','PF', 'French Polynesia'),
('en','PL', 'Poland'),
('en','PT', 'Portugal'),
('en','PR', 'Puerto Rico'),
('en','QA', 'Qatar'),
('en','GB', 'United Kingdom'),
('en','CF', 'Central African Republic'),
('en','CZ', 'Czech Republic'),
('en','DO', 'Dominican Republic'),
('en','RE', 'RÃ©union'),
('en','RW', 'Rwanda'),
('en','RO', 'Romania'),
('en','RU', 'Russia'),
('en','EH', 'Western Sahara'),
('en','WS', 'Samoa'),
('en','AS', 'American Samoa'),
('en','BL', 'Saint BarthÃ©lemy'),
('en','KN', 'Saint Kitts and Nevis'),
('en','SM', 'San Marino'),
('en','MF', 'Saint Martin (French part)'),
('en','PM', 'Saint Pierre and Miquelon'),
('en','VC', 'Saint Vincent and the Grenadines'),
('en','SH', 'AscensiÃ³n y TristÃ¡n de AcuÃ±a'),
('en','LC', 'Saint Lucia'),
('en','ST', 'Sao Tome and Principe'),
('en','SN', 'Senegal'),
('en','RS', 'Serbia'),
('en','SC', 'Seychelles'),
('en','SL', 'Sierra Leone'),
('en','SG', 'Singapore'),
('en','SY', 'Syria'),
('en','SO', 'Somalia'),
('en','LK', 'Sri Lanka'),
('en','ZA', 'South Africa'),
('en','SD', 'Sudan'),
('en','SE', 'Sweden'),
('en','CH', 'Switzerland'),
('en','SR', 'Suriname'),
('en','SJ', 'Svalbard and Jan Mayen'),
('en','SZ', 'Swaziland'),
('en','TJ', 'Tajikistan'),
('en','TH', 'Thailand'),
('en','TW', 'Taiwan'),
('en','TZ', 'Tanzania'),
('en','IO', 'British Indian Ocean Territory'),
('en','TF', 'French Southern Territories'),
('en','TL', 'East Timor'),
('en','TG', 'Togo'),
('en','TK', 'Tokelau'),
('en','TO', 'Tonga'),
('en','TT', 'Trinidad and Tobago'),
('en','TN', 'Tunisia'),
('en','TM', 'Turkmenistan'),
('en','TR', 'Turkey'),
('en','TV', 'Tuvalu'),
('en','UA', 'Ukraine'),
('en','UG', 'Uganda'),
('en','UY', 'Uruguay'),
('en','UZ', 'Uzbekistan'),
('en','VU', 'Vanuatu'),
('en','VE', 'Venezuela'),
('en','VN', 'Vietnam'),
('en','WF', 'Wallis and Futuna'),
('en','YE', 'Yemen'),
('en','DJ', 'Djibouti'),
('en','ZM', 'Zambia'),
('en','ZW', 'Zimbabwe'),
('es','AF', 'Afganistan'),
('es','AL', 'Albania'),
('es','DE', 'Alemania'),
('es','DZ', 'Algeria'),
('es','AD', 'Andorra'),
('es','AO', 'Angola'),
('es','AI', 'Anguila'),
('es','AQ', 'Antartida'),
('es','AG', 'Antigua y Barbuda'),
('es','AN', 'Antillas Neerlandesas'),
('es','SA', 'Arabia Saudita'),
('es','AR', 'Argentina'),
('es','AM', 'Armenia'),
('es','AW', 'Aruba'),
('es','AU', 'Australia'),
('es','AT', 'Austria'),
('es','AZ', 'Azerbayan'),
('es','BE', 'Belgica'),
('es','BS', 'Bahamas'),
('es','BH', 'Bahrein'),
('es','BD', 'Bangladesh'),
('es','BB', 'Barbados'),
('es','BZ', 'Belice'),
('es','BJ', 'BenÃ­n'),
('es','BT', 'BhutÃ¡n'),
('es','BY', 'Bielorrusia'),
('es','MM', 'Birmania'),
('es','BO', 'Bolivia'),
('es','BA', 'Bosnia y Herzegovina'),
('es','BW', 'Botsuana'),
('es','BR', 'Brasil'),
('es','BN', 'BrunÃ©i'),
('es','BG', 'Bulgaria'),
('es','BF', 'Burkina Faso'),
('es','BI', 'Burundi'),
('es','CV', 'Cabo Verde'),
('es','KH', 'Camboya'),
('es','CM', 'CamerÃºn'),
('es','CA', 'CanadÃ¡'),
('es','TD', 'Chad'),
('es','CL', 'Chile'),
('es','CN', 'China'),
('es','CY', 'Chipre'),
('es','VA', 'Ciudad del Vaticano'),
('es','CO', 'Colombia'),
('es','KM', 'Comoras'),
('es','CG', 'Congo'),
('es','CD', 'Congo'),
('es','KP', 'Corea del Norte'),
('es','KR', 'Corea del Sur'),
('es','CI', 'Costa de Marfil'),
('es','CR', 'Costa Rica'),
('es','HR', 'Croacia'),
('es','CU', 'Cuba'),
('es','DK', 'Dinamarca'),
('es','DM', 'Dominica'),
('es','EC', 'Ecuador'),
('es','EG', 'Egipto'),
('es','SV', 'El Salvador'),
('es','AE', 'Emiratos Ãrabes Unidos'),
('es','ER', 'Eritrea'),
('es','SK', 'Eslovaquia'),
('es','SI', 'Eslovenia'),
('es','ES', 'EspaÃ±a'),
('es','US', 'Estados Unidos de AmÃ©rica'),
('es','EE', 'Estonia'),
('es','ET', 'EtiopÃ­a'),
('es','PH', 'Filipinas'),
('es','FI', 'Finlandia'),
('es','FJ', 'Fiyi'),
('es','FR', 'Francia'),
('es','GA', 'GabÃ³n'),
('es','GM', 'Gambia'),
('es','GE', 'Georgia'),
('es','GH', 'Ghana'),
('es','GI', 'Gibraltar'),
('es','GD', 'Granada'),
('es','GR', 'Grecia'),
('es','GL', 'Groenlandia'),
('es','GP', 'Guadalupe'),
('es','GU', 'Guam'),
('es','GT', 'Guatemala'),
('es','GF', 'Guayana Francesa'),
('es','GG', 'Guernsey'),
('es','GN', 'Guinea'),
('es','GQ', 'Guinea Ecuatorial'),
('es','GW', 'Guinea-Bissau'),
('es','GY', 'Guyana'),
('es','HT', 'HaitÃ­'),
('es','HN', 'Honduras'),
('es','HK', 'Hong kong'),
('es','HU', 'HungrÃ­a'),
('es','IN', 'India'),
('es','ID', 'Indonesia'),
('es','IR', 'IrÃ¡n'),
('es','IQ', 'Irak'),
('es','IE', 'Irlanda'),
('es','BV', 'Isla Bouvet'),
('es','IM', 'Isla de Man'),
('es','CX', 'Isla de Navidad'),
('es','NF', 'Isla Norfolk'),
('es','IS', 'Islandia'),
('es','BM', 'Islas Bermudas'),
('es','KY', 'Islas CaimÃ¡n'),
('es','CC', 'Islas Cocos (Keeling)'),
('es','CK', 'Islas Cook'),
('es','AX', 'Islas de Ã…land'),
('es','FO', 'Islas Feroe'),
('es','GS', 'Islas Georgias del Sur y Sandwich del Sur'),
('es','HM', 'Islas Heard y McDonald'),
('es','MV', 'Islas Maldivas'),
('es','FK', 'Islas Malvinas'),
('es','MP', 'Islas Marianas del Norte'),
('es','MH', 'Islas Marshall'),
('es','PN', 'Islas Pitcairn'),
('es','SB', 'Islas SalomÃ³n'),
('es','TC', 'Islas Turcas y Caicos'),
('es','UM', 'Islas Ultramarinas Menores de Estados Unidos'),
('es','VG', 'Islas VÃ­rgenes BritÃ¡nicas'),
('es','VI', 'Islas VÃ­rgenes de los Estados Unidos'),
('es','IL', 'Israel'),
('es','IT', 'Italia'),
('es','JM', 'Jamaica'),
('es','JP', 'JapÃ³n'),
('es','JE', 'Jersey'),
('es','JO', 'Jordania'),
('es','KZ', 'KazajistÃ¡n'),
('es','KE', 'Kenia'),
('es','KG', 'KirgizstÃ¡n'),
('es','KI', 'Kiribati'),
('es','KW', 'Kuwait'),
('es','LB', 'LÃ­bano'),
('es','LA', 'Laos'),
('es','LS', 'Lesoto'),
('es','LV', 'Letonia'),
('es','LR', 'Liberia'),
('es','LY', 'Libia'),
('es','LI', 'Liechtenstein'),
('es','LT', 'Lituania'),
('es','LU', 'Luxemburgo'),
('es','MX', 'MÃ©xico'),
('es','MC', 'MÃ³naco'),
('es','MO', 'Macao'),
('es','MK', 'MacedÃ´nia'),
('es','MG', 'Madagascar'),
('es','MY', 'Malasia'),
('es','MW', 'Malawi'),
('es','ML', 'Mali'),
('es','MT', 'Malta'),
('es','MA', 'Marruecos'),
('es','MQ', 'Martinica'),
('es','MU', 'Mauricio'),
('es','MR', 'Mauritania'),
('es','YT', 'Mayotte'),
('es','FM', 'Micronesia'),
('es','MD', 'Moldavia'),
('es','MN', 'Mongolia'),
('es','ME', 'Montenegro'),
('es','MS', 'Montserrat'),
('es','MZ', 'Mozambique'),
('es','NA', 'Namibia'),
('es','NR', 'Nauru'),
('es','NP', 'Nepal'),
('es','NI', 'Nicaragua'),
('es','NE', 'Niger'),
('es','NG', 'Nigeria'),
('es','NU', 'Niue'),
('es','NO', 'Noruega'),
('es','NC', 'Nueva Caledonia'),
('es','NZ', 'Nueva Zelanda'),
('es','OM', 'OmÃ¡n'),
('es','NL', 'PaÃ­ses Bajos'),
('es','PK', 'PakistÃ¡n'),
('es','PW', 'Palau'),
('es','PS', 'Palestina'),
('es','PA', 'PanamÃ¡'),
('es','PG', 'PapÃºa Nueva Guinea'),
('es','PY', 'Paraguay'),
('es','PE', 'PerÃº'),
('es','PF', 'Polinesia Francesa'),
('es','PL', 'Polonia'),
('es','PT', 'Portugal'),
('es','PR', 'Puerto Rico'),
('es','QA', 'Qatar'),
('es','GB', 'Reino Unido'),
('es','CF', 'RepÃºblica Centroafricana'),
('es','CZ', 'RepÃºblica Checa'),
('es','DO', 'RepÃºblica Dominicana'),
('es','RE', 'ReuniÃ³n'),
('es','RW', 'Ruanda'),
('es','RO', 'RumanÃ­a'),
('es','RU', 'Rusia'),
('es','EH', 'Sahara Occidental'),
('es','WS', 'Samoa'),
('es','AS', 'Samoa Americana'),
('es','BL', 'San BartolomÃ©'),
('es','KN', 'San CristÃ³bal y Nieves'),
('es','SM', 'San Marino'),
('es','MF', 'San MartÃ­n (Francia)'),
('es','PM', 'San Pedro y MiquelÃ³n'),
('es','VC', 'San Vicente y las Granadinas'),
('es','SH', 'Santa Elena'),
('es','LC', 'Santa LucÃ­a'),
('es','ST', 'Santo TomÃ© y PrÃ­ncipe'),
('es','SN', 'Senegal'),
('es','RS', 'Serbia'),
('es','SC', 'Seychelles'),
('es','SL', 'Sierra Leona'),
('es','SG', 'Singapur'),
('es','SY', 'Siria'),
('es','SO', 'Somalia'),
('es','LK', 'Sri lanka'),
('es','ZA', 'SudÃ¡frica'),
('es','SD', 'SudÃ¡n'),
('es','SE', 'Suecia'),
('es','CH', 'Suiza'),
('es','SR', 'SurinÃ¡m'),
('es','SJ', 'Svalbard y Jan Mayen'),
('es','SZ', 'Swazilandia'),
('es','TJ', 'TadjikistÃ¡n'),
('es','TH', 'Tailandia'),
('es','TW', 'TaiwÃ¡n'),
('es','TZ', 'Tanzania'),
('es','IO', 'Territorio BritÃ¡nico del OcÃ©ano Ãndico'),
('es','TF', 'Territorios Australes y AntÃ¡rticas Franceses'),
('es','TL', 'Timor Oriental'),
('es','TG', 'Togo'),
('es','TK', 'Tokelau'),
('es','TO', 'Tonga'),
('es','TT', 'Trinidad y Tobago'),
('es','TN', 'Tunez'),
('es','TM', 'Turkmenistan'),
('es','TR', 'Turquia'),
('es','TV', 'Tuvalu'),
('es','UA', 'Ucrania'),
('es','UG', 'Uganda'),
('es','UY', 'Uruguay'),
('es','UZ', 'Uzbekistan'),
('es','VU', 'Vanuatu'),
('es','VE', 'Venezuela'),
('es','VN', 'Vietnam'),
('es','WF', 'Wallis y Futuna'),
('es','YE', 'Yemen'),
('es','DJ', 'Yibuti'),
('es','ZM', 'Zambia'),
('es','ZW', 'Zimbabue');

INSERT INTO IDIOMA (ID_IDIOMA, IDIOMA)VALUES
('en', 'english');

INSERT INTO GENERO (ID_GENERO, GENERO) VALUES
('H', 'Hombre'),
('M', 'Mujer'),
('O', 'Otro');

INSERT INTO NIVEL_INGLES (NIVEL) VALUES 
("A1"),
("A2"),
("B1"),
("B2"),
("C1");

INSERT INTO PROFESOR (EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION,PRECIO_SESION, ID_IDIOMA, ID_GENERO, ID_NIVEL, ACTIVADA, DESCRIPCION) VALUES
('IsaacLindqvist@teleworm.us','SE','RGQuCTo6CuK+p9tq5ULXigpX7qWLPX6r5LEMMr0+G59Qx5/s372D3W2UULg7zw00','Isaac','Lindqvist',NULL,'1970',NOW(),7,'en','H',3,1,'Creative and comprensive'),
('IzakAndreasson@einrot.com','SE','cAERs3MDPh+8fjNl3ICUuQzvxBFi15+7Pfhab9WB3PFStxSBjs2SdZ96BbMSxynm','Izak','Andreasson',NULL,'1973',NOW(),5,'en','H',4,1,'a good teacher'),
('NoreaEk@armyspy.com','SE','482Czm1Y4sDy8KwRm/78ayxODjV/FE1FDj7Ep506gDr4KVHRwVGpBPTkEGo9k8CX','Norea','Ek',NULL,'1978',NOW(),5,'en','M',4,1,'The best in my class'),
('LiljaBjorklund@armyspy.com','SE','hCyQeWIAS4M8u7Di3MgX7l/ae5Sz/43UIX2rUzNSKphtQa1Zl2E8UUi5QSohG5Fp','Lilja','Björklund',NULL,'1993',NOW(),6,'en','M',4,0, 'I like teaching'),
('AylinNorberg@dayrep.com','SE','abUhyVuG1K7eFY0XhI7YIpQk/RUD4eOToGeQfBgkkn2Bzykec0R9E1i7NnYiBu61','Aylin','Norberg',NULL,'1981',NOW(),6,'en','M',3,1, 'I am fom a little city with a big heart'),
('MichaelAstrom@rhyta.com','SE','z7BaagK0gU+yXmAl6F/f+Tb+L51WebDsuDZ0JBaaFqQ3dzl6deLavkstssmkpaGO','Michael','Åström',NULL,'1982',NOW(),8,'en','M',3,1, 'I hope i am useful'),
('LeonardSjoberg@rhyta.com','SE','G8sTG/bfXSEtW3WfQ0PgzkZs/Y5njbRlMqYv9hK26FCBK1/fh6WeCv1NjypdfXMR','Leonard','Sjöberg',NULL,'1999',NOW(),10,'en','M',2,1, 'English rules'),
('ElisGustafsson@jourrapide.com','SE','yLE7oIo+oErMvSYmcFyc9wf34Wsj+eDtivkSWcg62UGFr6FzrDORMZQ8ZGXs2IXt','Elis','Gustafsson',NULL,'1989',NOW(),9,'en','M',1,1, 'Windespring'),
('BellaLundin@jourrapide.com','SE','3eJnnjSUHFXMcaMrXD6gTkL0h2DxD5952QltIjExYzOqBZ0k571ks8VVd827ewm9','Bella','Lundin',NULL,'1967',NOW(),6,'en','O',2,1, 'I like technician topics'),
('ChrisForsberg@jourrapide.com','SE','8ikguuiBrA/yDgZbOGWL7pmeULbSOWHF+GtzdBBO9Pe+tu5g8qpC8CScts2fYy+Z','Chris','Forsberg',NULL,'1996',NOW(),8,'en','H',2,1, 'I love cats and environment');


INSERT INTO ESTUDIANTE (EMAIL, ID_PAIS, PSSWD, NOMBRE, APELLIDO1, APELLIDO2, ANO_NACIMIENTO, FECHA_SUBSCRIPCION,ID_NIVEL, ID_GENERO) VALUES 
('MaryCejaVaca@jourrapide.com','ES','brCcQiwecAeWCZ7j5kzjQVl5ekdCWcUBvx/hTQEzj11l4Vym0lPm1u7S5aTIJR+P','Mary','Ceja',NULL,1948,NOW(),2,'M'),
('RomanceSaldivarGodoy@dayrep.com','ES','4zIVHj8zgjrQ9aYaVDQDqCE0lldjV17AdxvOQMsFG7pYnHgw07kG2xPc/qKPg0In','Romance','Saldivar',NULL,1993,NOW(),3,'H'),
('RaingardaBacaRuvalcaba@cuvox.de','ES','BzNuhXhShHXINnou/dd2hQxDgUY53oix05nkV8z3J1Vo5ZP0HscUu91sMpspfraw','Raingarda','Baca',NULL,1951,NOW(),1,'M'),
('NuriaSierraMercado@superrito.com','ES','+476Ebimpv+pjItMJ7Pqqltya+m9hG9M18Goph32k/M2esQ5ppJeu9MZp2/zptgu','Nuria','Sierra',NULL,1995,NOW(),2,'M'),
('MaitenaVelizAvalos@einrot.com','ES','SsngkKwqIIow3A5whfntrPsJZzgZVIDLGkGW1XjvlytYMQCG78BzlIkTpC2vjv4t','Maitena','Veliz', NULL,1953,NOW(),4,'M'),
('AmberReeves@jourrapide.com','US','MJOFMmjB0pVKFlVt7IuCqv0SSCbUbzukzdklxPtM9xjlL4LvlDTKEVJBN57ABhvB','Amber','Reeves',NULL,'1990',NOW(),2, 'M'),
('AlexNorton@dayrep.com','US','fokm9XnHHx5Q8P/nJ+AVDg9QPmYGW2Mqa8VgA2IMKZcffX5bNpJiHEs3TAF/Qbpj','Alex','Norton',NULL,'1987',NOW(),4,'H'),
('MollyBentley@jourrapide.com','US','HNhnky/wFKEC6PoZ2qGYOZkdZ8zPDcDne5D1fDevwfJPBR9EQ3Tjvs2gNFvd9aBC','Molly','Bentley',NULL,'1984',NOW(),3,'M'),
('BenBooth@gustr.com','US','K0AYrJ7x3QUQ2LHqRXzqaOMuQC8bUEEoc+17r3ieJNBE8jtV60rZcx7/x6lu+XFC','Ben','Booth',NULL,'1981',NOW(),3,'H'),
('TobyOSullivan@jourrapide.com','US','g+US2QB02FbSpAD81oTOlbq5ltWhgKypZ+RQZeQXoBZxKjE4p6fypahwWZe2UMSn','Toby','O\'Sullivan',NULL,'1982',NOW(),2,'H');
 


INSERT INTO HORARIO (ID_PROFESOR, ID_DIA, ID_HORA) VALUES 
(1,1,44),
(1,1,85),
(1,2,69),
(1,4,55),
(1,4,22),
(1,6,95),
(1,7,86),
(1,7,96),
(3,1,12),
(3,1,20),
(3,1,25),
(3,1,30),
(3,3,50),
(3,3,60),
(3,4,5),
(3,4,80);


INSERT INTO ESTADO (ID_ESTADO, ESTADO) VALUES
("A", "Aceptada"),
("C", "Cancelada"),
("S", "Solicitada"),
("T", "Rechazada"),
("R", "Rechazada");


INSERT INTO SESION (ID_PROFESOR, ID_ESTUDIANTE,FECHA_SESION, ID_HORARIO, FECHA_INICIO, FECHA_FIN, PRECIO, ID_ESTADO, FECHA_CAMBIO_ESTADO) VALUES
(1,2,STR_TO_DATE('12/26/2019', '%c/%e/%Y'),1,NULL,NULL, 5, "S", STR_TO_DATE('1/23/2019 23:25:26 ', '%c/%e/%Y %T')),
(3,4,STR_TO_DATE('1/30/2019', '%c/%e/%Y'),15,NULL,NULL, 7, "R", STR_TO_DATE('12/23/2018 8:25:26 ', '%c/%e/%Y %T')),
(1,7,STR_TO_DATE('1/23/2019', '%c/%e/%Y'),4,STR_TO_DATE('1/23/2019 10:25:17 PM', '%c/%e/%Y %r'),STR_TO_DATE('1/23/2019 23:25:26 ', '%c/%e/%Y %T'), 5.5, "A", STR_TO_DATE('1/13/2019 4:30:45 PM', '%c/%e/%Y %r')),
(1,5,STR_TO_DATE('1/25/2019', '%c/%e/%Y'),6,NULL,NULL, 4.3, "C", STR_TO_DATE('1/23/2019 23:32:26 ', '%c/%e/%Y %T')),
(1,9,STR_TO_DATE('5/10/2019', '%c/%e/%Y'),7,NULL,NULL, 6, "S", STR_TO_DATE('1/15/2019 17:46:56 ', '%c/%e/%Y %T'));


INSERT INTO PROFESOR_PUNTUA_ESTUDIANTE (ID_PROFESOR, ID_ESTUDIANTE, FECHA_PUNTUACION, PUNTUACION) VALUES 
(6,4,"2005/12/01", 5),
(4,1, "2006/05/20", 4.5),
(4,1, "2006/07,22", 2),
(1, 3, "2015/05/15", 5),
(1,5,"2005/12/02", 3.5);


INSERT INTO ESTUDIANTE_PUNTUA_PROFESOR (ID_ESTUDIANTE, ID_PROFESOR, FECHA_PUNTUACION, PUNTUACION) VALUES 
(3,2,"2015/06/12", 5),
(5,6, "2000/12/21", 4.5),
(5,6, "2001/12/01", 4),
(1,1,"2018/01/26", 2.5),
(4,3,"1999/05/26", 1),
(2,5,"2002/06/05", 3.5);