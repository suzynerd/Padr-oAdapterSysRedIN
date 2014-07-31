package dominio.construtores;

import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Perfil;

public class PerfilConstrutor {

	public static Perfil construir(ResultSet result) {
		try {
			Integer id = result.getInt("idPerfil");
			String nome = result.getString("nome");
			String email = result.getString("email");
			// FIXME recuperar o objeto no lugar do nome e id
			// this.idInstituicao = rs.getInt("idInstituicao");
			return new Perfil(id, nome, email);
		} catch (SQLException e) {
			return null;
		}
	}

}
