package dominio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Turma {
	private Integer id, idPerfil;
	private String nome;
	private String descricao;
	
	private Perfil perfil;
	
	
	public Turma(Integer id,String nome ,String descricao,Perfil perfil){
		
		this.id=id;
		this.nome=nome;
		this.descricao=descricao;
		this.perfil=perfil;
	}
	
	public Turma(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Turma(){}
	
	
	
	
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}


	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
}
