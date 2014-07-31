package dominio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Notificacao {
	private Integer id, idTurma, idPerfil;
	
	public Notificacao(Integer idTurma, Integer idPerfil) {
		this.idTurma = idTurma;
		this.idPerfil = idPerfil;
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	private String nome, mensagem;
	
	public Notificacao(ResultSet rs){
		try {
			this.id = rs.getInt(1);
			this.nome = rs.getString(2);
			this.mensagem = rs.getString(3);
		} catch (SQLException e) {
			System.out.println("Erro ao gravar notificação");
		}
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
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
