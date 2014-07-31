-- phpMyAdmin SQL Dump
-- version 4.1.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 27/02/2014 às 16:34
-- Versão do servidor: 5.5.35
-- Versão do PHP: 5.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `mydb`
--
CREATE DATABASE IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `mydb`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `amigo`
--

DROP TABLE IF EXISTS `amigo`;
CREATE TABLE IF NOT EXISTS `amigo` (
  `idRelacao` int(11) NOT NULL AUTO_INCREMENT,
  `idPerfil` int(11) NOT NULL,
  `idPerfil1` int(11) NOT NULL,
  PRIMARY KEY (`idRelacao`),
  KEY `fk_table1_perfil1_idx` (`idPerfil`),
  KEY `fk_table1_perfil2_idx` (`idPerfil1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `arquivo`
--

DROP TABLE IF EXISTS `arquivo`;
CREATE TABLE IF NOT EXISTS `arquivo` (
  `idArquivo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `arquivo` longblob NOT NULL,
  `formato` varchar(45) NOT NULL,
  `idPerfil` int(11) NOT NULL,
  PRIMARY KEY (`idArquivo`),
  KEY `fk_arquivo_perfil1_idx` (`idPerfil`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `estado`
--

DROP TABLE IF EXISTS `estado`;
CREATE TABLE IF NOT EXISTS `estado` (
  `idEstado` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sigla` varchar(2) NOT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Fazendo dump de dados para tabela `estado`
--

INSERT INTO `estado` (`idEstado`, `nome`, `sigla`) VALUES(1, 'Rio Grande do Norte', 'RN');

-- --------------------------------------------------------

--
-- Estrutura para tabela `instituicao`
--

DROP TABLE IF EXISTS `instituicao`;
CREATE TABLE IF NOT EXISTS `instituicao` (
  `idInstituicao` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(120) NOT NULL,
  `sigla` varchar(10) NOT NULL,
  `idEstado` int(11) NOT NULL,
  PRIMARY KEY (`idInstituicao`),
  KEY `fk_instituicao_estado_idx` (`idEstado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Fazendo dump de dados para tabela `instituicao`
--

INSERT INTO `instituicao` (`idInstituicao`, `nome`, `sigla`, `idEstado`) VALUES(1, 'Instituto Federal de Educacao, Ciencia e Tecnologia', 'IFRN', 1);
INSERT INTO `instituicao` (`idInstituicao`, `nome`, `sigla`, `idEstado`) VALUES(4, 'Universidade Federal do Rio Grande do Norte', 'UFRN', 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `membros`
--

DROP TABLE IF EXISTS `membros`;
CREATE TABLE IF NOT EXISTS `membros` (
  `idTurma` int(11) NOT NULL,
  `idPerfil` int(11) NOT NULL,
  KEY `fk_membros_turma1_idx` (`idTurma`),
  KEY `fk_membros_perfil1_idx` (`idPerfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `notificacao`
--

DROP TABLE IF EXISTS `notificacao`;
CREATE TABLE IF NOT EXISTS `notificacao` (
  `idNotificacao` int(11) NOT NULL AUTO_INCREMENT,
  `idTurma` int(11) NOT NULL,
  `idPerfil` int(11) NOT NULL,
  `mensagem` varchar(120) NOT NULL,
  PRIMARY KEY (`idNotificacao`),
  KEY `fk_notificacao_turma_idx` (`idTurma`),
  KEY `fk_notificacao_perfil_idx` (`idPerfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `perfil`
--

DROP TABLE IF EXISTS `perfil`;
CREATE TABLE IF NOT EXISTS `perfil` (
  `idPerfil` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `idTipo` int(11) NOT NULL,
  `idInstituicao` int(11) NOT NULL,
  PRIMARY KEY (`idPerfil`),
  KEY `fk_perfil_tipo_perfil_idx` (`idTipo`),
  KEY `fk_perfil_instituicao_idx` (`idInstituicao`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Fazendo dump de dados para tabela `perfil`
--

INSERT INTO `perfil` (`idPerfil`, `nome`, `email`, `senha`, `idTipo`, `idInstituicao`) VALUES(1, 'aluno', 'aluno@email', '3663', 1, 1);
INSERT INTO `perfil` (`idPerfil`, `nome`, `email`, `senha`, `idTipo`, `idInstituicao`) VALUES(3, 'professor', 'professor@email', '3663', 2, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `idPost` int(11) NOT NULL,
  `conteudo` varchar(300) NOT NULL,
  `idPerfil` int(11) NOT NULL,
  `idTurma` int(11) NOT NULL,
  PRIMARY KEY (`idPost`),
  KEY `fk_post_perfil1_idx` (`idPerfil`),
  KEY `fk_post_turma1_idx` (`idTurma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `tipoperfil`
--

DROP TABLE IF EXISTS `tipoperfil`;
CREATE TABLE IF NOT EXISTS `tipoperfil` (
  `idTipo` int(11) NOT NULL AUTO_INCREMENT,
  `nomeTipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Fazendo dump de dados para tabela `tipoperfil`
--

INSERT INTO `tipoperfil` (`idTipo`, `nomeTipo`) VALUES(1, 'Aluno');
INSERT INTO `tipoperfil` (`idTipo`, `nomeTipo`) VALUES(2, 'Professor');

-- --------------------------------------------------------

--
-- Estrutura para tabela `turma`
--

DROP TABLE IF EXISTS `turma`;
CREATE TABLE IF NOT EXISTS `turma` (
  `idTurma` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `bio` varchar(200) DEFAULT NULL,
  `idPerfil` int(11) NOT NULL,
  PRIMARY KEY (`idTurma`),
  KEY `fk_turma_perfil1_idx` (`idPerfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `amigo`
--
ALTER TABLE `amigo`
  ADD CONSTRAINT `fk_table1_perfil1` FOREIGN KEY (`idPerfil`) REFERENCES `perfil` (`idPerfil`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_table1_perfil2` FOREIGN KEY (`idPerfil1`) REFERENCES `perfil` (`idPerfil`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `arquivo`
--
ALTER TABLE `arquivo`
  ADD CONSTRAINT `fk_arquivo_perfil1` FOREIGN KEY (`idPerfil`) REFERENCES `perfil` (`idPerfil`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `instituicao`
--
ALTER TABLE `instituicao`
  ADD CONSTRAINT `fk_instituicao_estado` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `membros`
--
ALTER TABLE `membros`
  ADD CONSTRAINT `fk_membros_perfil1` FOREIGN KEY (`idPerfil`) REFERENCES `perfil` (`idPerfil`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_membros_turma1` FOREIGN KEY (`idTurma`) REFERENCES `turma` (`idTurma`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `notificacao`
--
ALTER TABLE `notificacao`
  ADD CONSTRAINT `fk_notificacao_perfil` FOREIGN KEY (`idPerfil`) REFERENCES `perfil` (`idPerfil`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_notificacao_turma` FOREIGN KEY (`idTurma`) REFERENCES `turma` (`idTurma`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `perfil`
--
ALTER TABLE `perfil`
  ADD CONSTRAINT `fk_perfil_instituicao` FOREIGN KEY (`idInstituicao`) REFERENCES `instituicao` (`idInstituicao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_perfil_tipo_perfil` FOREIGN KEY (`idTipo`) REFERENCES `tipoperfil` (`idTipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `fk_post_perfil1` FOREIGN KEY (`idPerfil`) REFERENCES `perfil` (`idPerfil`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_post_turma1` FOREIGN KEY (`idTurma`) REFERENCES `turma` (`idTurma`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para tabelas `turma`
--
ALTER TABLE `turma`
  ADD CONSTRAINT `fk_turma_perfil1` FOREIGN KEY (`idPerfil`) REFERENCES `perfil` (`idPerfil`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
