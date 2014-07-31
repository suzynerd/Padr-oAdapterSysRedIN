package tools;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;

import dominio.Perfil;
import dominio.Turma;

public class Tool {
	
	public static Perfil getSessionObject(){
		return (Perfil) SecurityContextHolder.getContext().getAuthentication().getDetails();
	}
	
	public static Integer getSessionID(){
		return getSessionObject().getId();
	}
	
	public static void updateSessionObject(Perfil perfil){
		Tool.getSessionObject().setEmail(perfil.getEmail());
		Tool.getSessionObject().setNome(perfil.getNome());
	}
	
	public static Integer getTurmaId(HttpSession session){
		return ((Turma) session.getAttribute("turma")).getId();
	}
}
