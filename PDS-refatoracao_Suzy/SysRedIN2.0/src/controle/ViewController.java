package controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoAmigo;
import dao.DaoPerfil;

@Controller
public class ViewController {
	
	@RequestMapping("/v")
	public ModelAndView viewPerfil(@RequestParam("idPerfil") Integer idPerfil){
		ModelAndView model = new  ModelAndView("userview/perfil");
		model.addObject("perfil", DaoPerfil.get(idPerfil));
		model.addObject("amigo", DaoAmigo.isAmigo(Tool.getSessionID(), idPerfil));
		model.addObject("infoAmigos", DaoAmigo.count(idPerfil));
		
		return model;
	}
}
