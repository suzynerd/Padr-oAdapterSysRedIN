package dominio;

import java.util.Date;

public class Post {
	private Integer id, idPerfil, idTurma;
	private String nome, conteudo;
	private Perfil perfil;
	private Date data;
	private Turma turma;

	public Post(Integer id, String conteudo, Perfil perfil, Turma turma) {
		this.id = id;
		this.conteudo = conteudo;
		this.perfil = perfil;
		this.turma  = turma;
		this.data = new java.util.Date();
	}
	public Post() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Integer idPost) {
		this.idPerfil = idPost;
	}
	public Integer getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Turma getTurma() {
		return this.turma;
	}
}
