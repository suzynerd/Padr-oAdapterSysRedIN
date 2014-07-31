package controle;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoNotificacao;
import dao.DaoPost;
import dao.DaoTurma;
import dominio.Notificacao;
import dominio.Post;
import dominio.Turma;
import dominio.construtores.PostConstrutor;

@Controller
public class TurmaController {
	
		@Resource(name="roleService")
		private DaoPost daoPost;
		
		@RequestMapping("/perfil/turmas")
		public ModelAndView minhasTurmas(){
			ModelAndView model = new ModelAndView("user/turmas");
			List<Turma> turmas;
			if(Tool.getSessionObject().getIdTipo() == 2)
				turmas = DaoTurma.getListForProfessor(Tool.getSessionID());
			else turmas = DaoTurma.getListForAluno(Tool.getSessionID());
			model.addObject("professor", Tool.getSessionObject().getIdTipo() == 2);
			model.addObject("turmas", turmas);
			return model;
		}
		
		@RequestMapping("/turma/fechar")
		public String fecharTurma(HttpSession session){
			DaoTurma.fecharTurma(Tool.getTurmaId(session));
			return "redirect:/perfil/turmas";
		}
		
		@RequestMapping("/novaturma")
		public ModelAndView criarTurma(){
			ModelAndView model = new ModelAndView("user/criarTurma");
			model.addObject("turma", new Turma());
			model.addObject("professor", Tool.getSessionObject().getIdTipo() == 2);
			return model;
		}
	
		@RequestMapping(value="/salvarturma")
		public String salvarTurma(Turma turma){
			//DaoTurma.insert(turma, Tool.getSessionID());
			return "redirect:/perfil/turmas";
		}
		
		@RequestMapping("/turma")
		public ModelAndView turma(HttpSession session, @RequestParam("idTurma") Integer idTurma) throws JSONException{
			ModelAndView model;
				model = new ModelAndView("turma/main");
				Turma turma = DaoTurma.findTurma(idTurma);
				model.addObject("posts", daoPost.getList(idTurma));
				model.addObject("newPost", new Post());
				model.addObject("posts", daoPost.getList(idTurma));
				model.addObject("professor", Tool.getSessionObject().getIdTipo() == 2);
				session.setAttribute("turma", turma);
			
			return model;
		}
		
		@RequestMapping("/turma/entrarTurma")
		public String entrarTurma(HttpSession session, @RequestParam("idAluno") Integer idAluno){
			DaoNotificacao.insert(new Notificacao(Tool.getTurmaId(session), idAluno));
			return "redirect:/perfil/Turmas";
		}
		
		@RequestMapping("/turma/alunos")
		public ModelAndView membros(HttpSession session){
			ModelAndView model = new ModelAndView("turma/membros");
			model.addObject("membros", DaoTurma.getListMembros(Tool.getTurmaId(session)));
			model.addObject("professor", Tool.getSessionObject().getIdTipo() == 2);
			return model;
		}
		
		@RequestMapping("/turma/novoAluno")
		public ModelAndView novoAluno(HttpSession session){
			ModelAndView model = new ModelAndView("turma/novoAluno");
			model.addObject("alunos", DaoTurma.getListNovoAluno(Tool.getTurmaId(session), Tool.getSessionID()));
			model.addObject("professor", Tool.getSessionObject().getIdTipo() == 2);
			return model;
		}
		
		
		@RequestMapping(value="/turma/convidarAluno", method=RequestMethod.GET)
		public String convidarAluno(HttpSession session, @RequestParam("idAluno") Integer idAluno){
			DaoTurma.insertMembro(Tool.getTurmaId(session), idAluno);
			return "redirect:/turma/novoAluno";
		}
		
		@RequestMapping(value="/turma/removerAluno", method=RequestMethod.GET)
		public String removerAluno(HttpSession session, @RequestParam("idAluno") Integer idAluno){
			DaoTurma.removeMembro(Tool.getTurmaId(session), idAluno);
			return "redirect:/turma/alunos";
		}
		
		@RequestMapping(value="/turma/postar", method=RequestMethod.GET)
		public String postarTurma(@RequestParam("post") String conteudo, HttpSession session) throws SQLException{
			Post post = PostConstrutor.construir(conteudo, Tool.getSessionID(), Tool.getTurmaId(session));
//			Post post = new Post(conteudo, Tool.getSessionID(), Tool.getTurmaId(session));
			DaoPost.insert(post);
			return "redirect:/turma?idTurma=" + Tool.getTurmaId(session);
		}
		
		@RequestMapping("/turma/notificacao")
		public ModelAndView notificacoes(HttpSession session){
			ModelAndView model = new ModelAndView("turma/notificacao");
			model.addObject("notificacoes", DaoNotificacao.getList(Tool.getTurmaId(session)));
			return model;
		}
		
		@RequestMapping("/turma/aceitarNotificacao")
		public String aceitarSolicitacao(@RequestParam("idNotificacao") Integer idNotificacao){
			DaoNotificacao.aceitar(idNotificacao);
			return "redirect:/turma/notificacao";
		}
		
		@RequestMapping("/turma/recusarNotificacao")
		public String recusarSolicitacao(@RequestParam("idNotificacao") Integer idNotificacao){
			DaoNotificacao.remove(idNotificacao);
			return "redirect:/turma/notificacao";
		}
		
		@RequestMapping("/turma/sair")
		public String sairTurma(HttpSession session){
			DaoTurma.removeMembro(Tool.getTurmaId(session), Tool.getSessionID());
			session.removeAttribute("turma");
			return "redirect:/perfil";
		}
}
