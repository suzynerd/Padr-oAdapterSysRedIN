package dominio.construtores;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.Conexao;
import dao.DaoTurma;
import dominio.Turma;

public class TurmaConstrutorTeste {

	static ResultSet result;
	
	@BeforeClass
	public static void configurar() {
		try {
			
			result = Conexao.getConnection().prepareStatement("select * from turma;").executeQuery();
			
			result.next();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void finalizar() {
		try {
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConstrutorResultSet() {
		assertNotNull( TurmaConstrutor.construir(result) );
	}

}
