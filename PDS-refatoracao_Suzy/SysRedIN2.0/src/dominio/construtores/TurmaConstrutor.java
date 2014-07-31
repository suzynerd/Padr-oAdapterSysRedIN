package dominio.construtores;

import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Perfil;
import dominio.Turma;

public class TurmaConstrutor {
	
	public static Turma construir(ResultSet result) {
		
		
		try {
			Integer id = result.getInt(1);
			String nome = result.getString(2);
			String descricao = result.getString(3);
			Perfil perfil = new Perfil ();
			perfil.setId(result.getInt(4));
			return new Turma(id, nome , descricao, perfil);
		} catch (SQLException e) {
			return null;
		}
		
		
	}

}
