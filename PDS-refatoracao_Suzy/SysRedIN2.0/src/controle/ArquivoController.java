/*package controle;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
//import dao.DaoArquivo;
import dominio.Arquivo;

@Controller
public class ArquivoController {
	
	@RequestMapping("/perfil/arquivos")
	public ModelAndView novo() throws SQLException{
		ModelAndView model = new ModelAndView("user/arquivos");
		model.addObject("arquivos", dominio.getList(Tool.getSessionID()));
		
		return model;
	}
	
	@RequestMapping(value="/arquivos/upload", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file){
		if(file != null){
			Arquivo a = new Arquivo(file);
			DaoArquivo.insert(a, Tool.getSessionID());
		}
		
		return "redirect:/perfil/arquivos";
	}
	
	@RequestMapping(value="/arquivos/download", method=RequestMethod.GET)
	public HttpEntity<byte[]> download(@RequestParam("idArquivo") Integer idArquivo) throws IOException, SQLException{
				Arquivo a = DaoArquivo.find(idArquivo);
				HttpHeaders headers = new HttpHeaders();
				
				String[] tokens = a.getFormato().split("/");
				headers.setContentType(new MediaType(tokens[0], tokens[1]));
				headers.set("Content-Disposition", "attachment; filename=" + a.getNome().replace(" ", "-"));
				headers.setContentLength(a.getBites().length);
				return new HttpEntity<byte[]>(a.getBites(), headers);
	}
	
	@RequestMapping(value="/arquivos/excluir", method=RequestMethod.GET)
	public String delete(@RequestParam("idArquivo") Integer idArquivo){
		DaoArquivo.remove(idArquivo);
		return "redirect:/perfil/arquivos";
	}
}*/