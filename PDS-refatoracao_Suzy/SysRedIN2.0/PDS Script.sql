SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`tipoPerfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tipoPerfil` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tipoPerfil` (
  `idTipo` INT NOT NULL AUTO_INCREMENT,
  `nomeTipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`estado` ;

CREATE TABLE IF NOT EXISTS `mydb`.`estado` (
  `idestado` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `sigla` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`idestado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`instituicao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`instituicao` ;

CREATE TABLE IF NOT EXISTS `mydb`.`instituicao` (
  `idInstituicao` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sigla` VARCHAR(10) NOT NULL,
  `idestado` INT NOT NULL,
  PRIMARY KEY (`idInstituicao`),
  INDEX `fk_instituicao_estado1_idx` (`idestado` ASC),
  CONSTRAINT `fk_instituicao_estado1`
    FOREIGN KEY (`idestado`)
    REFERENCES `mydb`.`estado` (`idestado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`perfil` ;

CREATE TABLE IF NOT EXISTS `mydb`.`perfil` (
  `idPerfil` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `idTipo` INT NOT NULL,
  `idInstituicao` INT NOT NULL,
  PRIMARY KEY (`idPerfil`),
  INDEX `fk_perfil_tipo_perfil_idx` (`idTipo` ASC),
  INDEX `fk_perfil_instituicao1_idx` (`idInstituicao` ASC),
  CONSTRAINT `fk_perfil_tipo_perfil`
    FOREIGN KEY (`idTipo`)
    REFERENCES `mydb`.`tipoPerfil` (`idTipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_perfil_instituicao1`
    FOREIGN KEY (`idInstituicao`)
    REFERENCES `mydb`.`instituicao` (`idInstituicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`arquivo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`arquivo` ;

CREATE TABLE IF NOT EXISTS `mydb`.`arquivo` (
  `idArquivo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `arquivo` LONGBLOB NOT NULL,
  `idPerfil` INT NOT NULL,
  PRIMARY KEY (`idArquivo`),
  INDEX `fk_arquivo_perfil1_idx` (`idPerfil` ASC),
  CONSTRAINT `fk_arquivo_perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `mydb`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`amigo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`amigo` ;

CREATE TABLE IF NOT EXISTS `mydb`.`amigo` (
  `idRelacao` INT NOT NULL AUTO_INCREMENT,
  `idPerfil` INT NOT NULL,
  `idPerfil1` INT NOT NULL,
  PRIMARY KEY (`idRelacao`),
  INDEX `fk_table1_perfil1_idx` (`idPerfil` ASC),
  INDEX `fk_table1_perfil2_idx` (`idPerfil1` ASC),
  CONSTRAINT `fk_table1_perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `mydb`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_perfil2`
    FOREIGN KEY (`idPerfil1`)
    REFERENCES `mydb`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`turma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`turma` ;

CREATE TABLE IF NOT EXISTS `mydb`.`turma` (
  `idTurma` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(200) NULL,
  `idPerfil` INT NOT NULL,
  PRIMARY KEY (`idTurma`),
  INDEX `fk_turma_perfil1_idx` (`idPerfil` ASC),
  CONSTRAINT `fk_turma_perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `mydb`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`membros`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`membros` ;

CREATE TABLE IF NOT EXISTS `mydb`.`membros` (
  `idTurma` INT NOT NULL,
  `idPerfil` INT NOT NULL,
  INDEX `fk_membros_turma1_idx` (`idTurma` ASC),
  INDEX `fk_membros_perfil1_idx` (`idPerfil` ASC),
  CONSTRAINT `fk_membros_turma1`
    FOREIGN KEY (`idTurma`)
    REFERENCES `mydb`.`turma` (`idTurma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_membros_perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `mydb`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`post` ;

CREATE TABLE IF NOT EXISTS `mydb`.`post` (
  `idPost` INT NOT NULL,
  `conteudo` VARCHAR(300) NOT NULL,
  `data` DATE NOT NULL,
  `idPerfil` INT NOT NULL,
  `idTurma` INT NOT NULL,
  PRIMARY KEY (`idPost`),
  INDEX `fk_post_perfil1_idx` (`idPerfil` ASC),
  INDEX `fk_post_turma1_idx` (`idTurma` ASC),
  CONSTRAINT `fk_post_perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `mydb`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_turma1`
    FOREIGN KEY (`idTurma`)
    REFERENCES `mydb`.`turma` (`idTurma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`notificacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`notificacao` ;

CREATE TABLE IF NOT EXISTS `mydb`.`notificacao` (
  `idnotificacao` INT NOT NULL,
  `idPerfil` INT NOT NULL,
  `idTurma` INT NOT NULL,
  PRIMARY KEY (`idnotificacao`),
  INDEX `fk_notificacao_perfil1_idx` (`idPerfil` ASC),
  INDEX `fk_notificacao_turma1_idx` (`idTurma` ASC),
  CONSTRAINT `fk_notificacao_perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `mydb`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notificacao_turma1`
    FOREIGN KEY (`idTurma`)
    REFERENCES `mydb`.`turma` (`idTurma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`tipoPerfil`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`tipoPerfil` (`idTipo`, `nomeTipo`) VALUES (1, 'Aluno');
INSERT INTO `mydb`.`tipoPerfil` (`idTipo`, `nomeTipo`) VALUES (2, 'Professor');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`estado`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`estado` (`idestado`, `nome`, `sigla`) VALUES (1, 'Rio Grande do Norte', 'RN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`instituicao`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`instituicao` (`idInstituicao`, `nome`, `sigla`, `idestado`) VALUES (1, 'Instituto Federal de Educacao, Ciencia e Tecnologia', 'IFRN', 1);
INSERT INTO `mydb`.`instituicao` (`idInstituicao`, `nome`, `sigla`, `idestado`) VALUES (2, 'Universidade Federal do Rio Grande do Norte', 'UFRN', 1);

COMMIT;

