package dominio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Amigo {
	private Integer id, idRelacao;
	private String nome;
	
	public Amigo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdRelacao() {
		return idRelacao;
	}

	public void setIdRelacao(Integer idRelacao) {
		this.idRelacao = idRelacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Amigo(ResultSet rs){
		try {
			this.id = rs.getInt("idAmigo");
			this.nome = rs.getString("nome");
		} catch (SQLException e) {
			System.out.println("Erro ao gravar Atributos");
		}
	}
	
	
}
