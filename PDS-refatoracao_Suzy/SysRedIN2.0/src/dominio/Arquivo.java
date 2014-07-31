package dominio;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Arquivo {
	private int id;
	private String formato, nome;
	private byte[] bites;
	
	
	public Arquivo() {
	}
	
	public Arquivo(MultipartFile file){
		this.nome = file.getOriginalFilename();
		try {
			this.bites = file.getBytes();
		} catch (IOException e) {
			System.out.println("Erro ao Carregar Bytes");
		}
		this.formato = file.getContentType();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public byte[] getBites() {
		return bites;
	}
	public void setBites(byte[] bites) {
		this.bites = bites;
	}
}
