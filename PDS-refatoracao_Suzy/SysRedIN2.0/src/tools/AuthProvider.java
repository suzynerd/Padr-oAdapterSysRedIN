package tools;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import dao.DaoPerfil;
import dominio.Perfil;

public class AuthProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		String login = auth.getName();
		String senha = auth.getCredentials().toString();
		List<GrantedAuthority> auto = new ArrayList<>();
		Perfil p = null;
		
		boolean existe = !DaoPerfil.exists(login, senha);
		
		if(existe)
			return null;
		else{
			p = DaoPerfil.logar(login, senha);
			if(p == null) return null;
			if(p.getIdTipo() == 1)
				auto.add(new SimpleGrantedAuthority("ALUNO"));
			else
				auto.add(new SimpleGrantedAuthority("PROFESSOR"));
		}
		
		UsernamePasswordAuthenticationToken main =
				new UsernamePasswordAuthenticationToken
				(login, senha, auto);
		
		main.setDetails(p);
		return main;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0));
	}

}
