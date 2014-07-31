package dominio.validacao;

import dao.DaoPerfil;
import dominio.Turma;

public abstract class TurmaValidacao {
	
	public static boolean validarNovo(Turma turma) {
		if (turma.getId() != null) return false;
		//if (DaoPerfil.existsEmail(perfil.getEmail())) return false;
		if (turma.getPerfil() == null) return false;
		if (turma.getDescricao() == null) return false;
		return true;
	}

}
