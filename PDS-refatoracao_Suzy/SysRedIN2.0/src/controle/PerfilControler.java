package controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.DaoPerfil;
import tools.Tool;

@Controller
public class PerfilControler {

	@RequestMapping("/perfil")
	public ModelAndView perfil(){
		ModelAndView model = new ModelAndView("user/main");
		return model;
	}
	
	@RequestMapping("/perfil/preferencias")
	public ModelAndView preferencias(){
		ModelAndView model = new ModelAndView("user/preferencias");
		model.addObject("perfil", Tool.getSessionObject());
		return model;
	}
	
	@RequestMapping(value="/perfil/alterarDados", method=RequestMethod.GET)
	public String alterarDados(@RequestParam("nome") String nome, @RequestParam("email") String email){
		DaoPerfil.update(nome, email, Tool.getSessionID());
		Tool.getSessionObject().setEmail(email);
		Tool.getSessionObject().setNome(nome);
		return "redirect:/perfil";
	}
	
	@RequestMapping(value="/perfil/mudarSenha", method=RequestMethod.GET)
	public String mudarSenha(@RequestParam("senhaAtual") String senhaAtual, @RequestParam("novaSenha") String novaSenha){
		if(DaoPerfil.exists(Tool.getSessionObject().getEmail(), senhaAtual))
			DaoPerfil.update(novaSenha, Tool.getSessionID());
		return "redirect:/perfil";
	}
	
	@RequestMapping(value="/perfil/excluirPerfil")
	public String excluirPerfil(){
		DaoPerfil.delete(Tool.getSessionID());
		return "redirect:/j_spring_security_logout";
	}
	
	

}
