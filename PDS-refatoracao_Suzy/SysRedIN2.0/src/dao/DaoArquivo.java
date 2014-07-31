package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Arquivo;

public class DaoArquivo {
	private static Connection conexao = Conexao.getConnection();
	
	public static List<Arquivo> getList(Integer idPerfil){
		List<Arquivo> arquivos = new ArrayList<>();
		String sql = "select * from arquivo where idPerfil = ?";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				Arquivo a = new Arquivo();
				a.setId(rs.getInt("idArquivo"));
				a.setNome(rs.getNString("nome"));
				a.setFormato(rs.getString("formato"));
				arquivos.add(a);
			}
			stm.close(); rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Arquivos: DaoArquivo.getList");
		}
		
		return arquivos;
	}
	
	public static void insert(Arquivo arquivo, Integer idPerfil){
		String sql = "insert into arquivo (nome, arquivo, formato, idPerfil) values (?,?,?,?)";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, arquivo.getNome());
			stm.setBytes(2, arquivo.getBites());
			stm.setString(3, arquivo.getFormato());
			stm.setInt(4, idPerfil);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Arquivo: DaoArquivo.insert()");
		}
	}
	
	public static void remove(Integer idArquivo){
		String sql = "delete from arquivo where idArquivo = ?";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idArquivo);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro a Remover Arquivo: DaoArquivo.remove()");
		}
		
	}
	
	public static Arquivo find(Integer id){
		Arquivo a = new Arquivo();
		String sql = "select * from arquivo where idArquivo = ?";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				a.setId(rs.getInt("idArquivo"));
				a.setNome(rs.getNString("nome"));
				a.setBites(rs.getBytes("arquivo"));
				a.setFormato(rs.getString("formato"));
			}
			stm.close(); rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao procurar Arquivo: DaoArquivo.find()");
		}
		
		return a;
	}
	
	public static Integer count(Integer idPerfil){
		return getList(idPerfil).size();
	}
}