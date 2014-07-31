package dominio.construtores;

import java.sql.ResultSet;
import java.sql.SQLException;
import dominio.Post;


public class PostConstrutor {
	public static Post construir(ResultSet result) {
		try {
			Integer id = result.getInt("idPost"),
					idPerfil = result.getInt("idPerfil"),
					idTurma = result.getInt("idTurma"); // FIXME : trocar pelo objeto da post
			String 	conteudo = result.getString("conteudo");
			
			// FIXME modificar de IDs para Objetos
			return new Post(id, conteudo, null, null);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static Post construir(String conteudo, Integer idPerfil, Integer idTurma) {
		// FIXME falta implementação do teste e do corpo do método
		return null;
	}
}
