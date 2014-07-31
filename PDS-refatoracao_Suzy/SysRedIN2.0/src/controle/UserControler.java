package controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.DaoInstituicao;
import dao.DaoPerfil;
import dao.DaoTipo;
import dominio.Perfil;

@Controller
public class UserControler {
	@RequestMapping("/")
	public ModelAndView home(){
		ModelAndView model = new ModelAndView("index/home");
		return model;
	}
	
	@RequestMapping("/cadastro")
	public ModelAndView cadastro(){
		ModelAndView model = new ModelAndView("index/cadastro");
		model.addObject("Perfil", new Perfil());
		model.addObject("tipos", DaoTipo.getList());
		model.addObject("instituicoes", DaoInstituicao.getList());
		
		return model;
	}
	
	@RequestMapping("/cadastro/salvar")
	public String salvar(Perfil perfil, @RequestParam("tipoPerfil") Integer idTipo,
			@RequestParam("idInstituicao") Integer idInstituicao){
		
		perfil.setIdTipo(idTipo); perfil.setIdInstituicao(idInstituicao);
		DaoPerfil.insert(perfil);
		
		return "redirect:/";
	}
}
