package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.json.JSONException;

import dominio.Post;

public class DaoPost extends Dao<Post> {
	private static Connection conexao = Conexao.getConnection();
	
	public DaoPost() {
		this.tableName = "role";
	}
	
	public List<Post> getList(Integer idTurma) throws JSONException {
		//List<Post> list_roles = this.find("list", parameters, false);
		List<Post> list_roles = this.find("list");
		return list_roles;
	}
	
	/*public static List<Post> getList(Integer idTurma){
		List<Post> posts = new ArrayList<>();
		String sql = "select post.idPost, post.nome, post.data, post.conteudo"
				+ " from post "
				+ "inner join perfil on perfil.idPerfil = post.idPerfil "
				+ "where post.idTurma = " + idTurma + " order by post.data DESC";
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				posts.add(PostConstrutor.construir(rs));
			}
			stm.close(); rs.close();
		} catch (SQLException e) {
			System.out.println("Erro a listar posts");
		}
		
		return posts;
	}*/
	
	public static void insert(Post post) throws SQLException{
		String sql = "insert into post (conteudo, data, idPerfil, idTurma) values (?, ?, ?, ?)";
		
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, post.getConteudo());
			stm.setDate(2, new java.sql.Date(post.getData().getTime()));
			stm.setInt(3, post.getIdPerfil());
			stm.setInt(4, post.getIdTurma());
			stm.executeUpdate();
			stm.close();
		
		
	}
	
	public static void delete(Integer idPost){
		String sql = "delete from post where idPost = " + idPost;
		try {
			Statement stm = conexao.createStatement();
			stm.executeUpdate(sql);
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao deletar post");
		}
	}
}
