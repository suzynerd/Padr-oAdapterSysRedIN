package controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoAmigo;

@Controller
public class AmigoController {

	@RequestMapping("/adicionarAmigo")
	public String AdicionarAmigo(@RequestParam("idAmigo") Integer idAmigo){
		DaoAmigo.insert(Tool.getSessionID(), idAmigo);
		return "redirect:/v?idPerfil=" + idAmigo;
	}
	
	@RequestMapping("/removerAmigo")
	public String removerAmigo(@RequestParam("idAmigo") Integer idAmigo){
		DaoAmigo.delete(Tool.getSessionID(), idAmigo);
		return "redirect:/perfil/amigos";
	}
	
	@RequestMapping("/perfil/amigos")
	public ModelAndView amigos(){
		ModelAndView model = new ModelAndView("user/amigos");
		model.addObject("amigos", DaoAmigo.getList(Tool.getSessionID()));
		return model;
	}
}
