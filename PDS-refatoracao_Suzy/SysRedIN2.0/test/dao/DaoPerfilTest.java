package dao;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dominio.Instituicao;
import dominio.Perfil;
import dominio.PerfilTipo;

public class DaoPerfilTest {
	static String email;
	static Instituicao ifrn;
	static PerfilTipo tipo;
	
	@BeforeClass
	public static void configurarTeste() {
		email = "teste@teste.edu";
		ifrn = new Instituicao(1, "Instituto Federal do Rio Grande do NorteInstituto Federal do Rio Grande do Norte", "IFRN");
		tipo = new PerfilTipo(1, "Aluno");
	}
	
	@AfterClass
	public static void limpar() {
		
		// Apaga informações do método testInsert
		try {
			PreparedStatement statement = Conexao.getConnection().prepareStatement("delete from perfil where email = ?");
			statement.setString(1, email);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void verificarMetodoInsert() {
		Perfil perfilBranco = new Perfil();
		assertFalse( DaoPerfil.insert(perfilBranco) );

		Perfil perfilValido = new Perfil();
		perfilValido.setEmail(email);
		perfilValido.setInstituicao(ifrn);
		// Paradigma relacional
		perfilValido.setTipo(tipo);
		perfilValido.setNome("teste");
		perfilValido.setSenha("teste");
		assertTrue( DaoPerfil.insert(perfilValido) );
		
		Perfil perfilInvalido = new Perfil();
		// Não posso alterar o identificador
		perfilInvalido.setId(3);
		perfilInvalido.setEmail(email + ".br");
		perfilInvalido.setInstituicao(ifrn);
		perfilInvalido.setIdTipo(1);
		perfilInvalido.setNome("teste");
		perfilInvalido.setSenha("teste");
		assertFalse( DaoPerfil.insert(perfilInvalido) );
	}
//
//	@Test
//	public void testGetList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetListInteger() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGet() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testExists() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLogar() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testExistsEmail() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateStringStringInteger() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateStringInteger() {
//		fail("Not yet implemented");
//	}
//
}
