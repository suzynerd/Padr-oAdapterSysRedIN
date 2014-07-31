package controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoPerfil;
import dao.DaoTurma;

@Controller
public class BuscaController {
	
	@RequestMapping("/busca/Pessoas")
	public ModelAndView buscaPessoas(){
		ModelAndView model = new ModelAndView("busca/pessoas");
		model.addObject("perfis", DaoPerfil.getList(Tool.getSessionID()));
		return model;
	}
	
	@RequestMapping("/busca/Turmas")
	public ModelAndView buscaTurmas(){
		ModelAndView model = new ModelAndView("busca/turmas");
		if(Tool.getSessionObject().getIdTipo() == 1)
			model.addObject("turmas", DaoTurma.getListForAluno(Tool.getSessionID()));
		else
			model.addObject("turmas", DaoTurma.getListForProfessor(Tool.getSessionID()));
		model.addObject("professor", Tool.getSessionObject().getIdTipo() == 2);
		return model;
	}
}
