package dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dominio.Perfil;
import dominio.Turma;

public class DaoTurmaTest {

	static String nome, descricao;
	static Perfil perfil;
	
	@BeforeClass
	public static void configurarTeste() {
		nome = "Nome da turma";
		descricao = "descricao da turma";
		
		perfil = new Perfil();
		perfil.setId(1);
	}
	
	@AfterClass
	public static void limpar() {
		
		// Apaga informações do método testInsert
		try {
			PreparedStatement statement = Conexao.getConnection().prepareStatement("delete from turma where nome = ?");
			statement.setString(1, nome);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void verificarMetodoInsert() {
		Turma turmaBranco = new Turma();
		assertFalse( DaoTurma.insert(turmaBranco) );

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

}
