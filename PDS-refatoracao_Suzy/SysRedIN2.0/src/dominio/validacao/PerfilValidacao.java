package dominio.validacao;

import dao.DaoPerfil;
import dominio.Perfil;

public abstract class PerfilValidacao {
	
	public static boolean validarNovo(Perfil perfil) {
		if (perfil.getId() != null) return false;
		if (DaoPerfil.existsEmail(perfil.getEmail())) return false;
		if (perfil.getInstituicao() == null) return false;
		if (perfil.getTipo() == null) return false;
		if (perfil.getNome() == null) return false;
		if (perfil.getSenha() == null) return false;
		
		return true;
	}
}
