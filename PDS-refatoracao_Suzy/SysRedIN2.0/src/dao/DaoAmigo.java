package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.List;

import dominio.Amigo;

public class DaoAmigo {
	public static Connection conexao = Conexao.getConnection();
	
	
	public static void insert(Integer idPerfil, Integer idAmigo){
		String sql = "insert into amigo (idPerfil, idAmigo) values (?,?)";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil); stm.setInt(2, idAmigo);
			stm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao Adicionar amigo: DaoAmigo.insert()");
		}
		
		sql = "insert into amigo (idPerfil, idAmigo) values (?,?)";
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idAmigo); stm.setInt(2, idPerfil);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao Adicionar amigo: DaoAmigo.insert()");
		}
	}
	
	public static List<Amigo> getList(Integer idPerfil){
		List<Amigo> amigos = new ArrayList<>();
		
		String sql = "select amigo.idAmigo, perfil.nome "
				+ "from amigo "
				+ "inner join perfil on amigo.idAmigo = perfil.idPerfil"
				+ " where amigo.idPerfil = ? "
				+ "order by perfil.nome ASC";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				amigos.add(new Amigo(rs));
			}
			rs.close(); stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Amigos");
		}
		
		return amigos;
	}
	
	public static void delete(Integer idPerfil, Integer idAmigo){
		String sql = "delete from amigo where idPerfil = ? and idAmigo = ?";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil);
			stm.setInt(2, idAmigo);
			stm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao deletar amigo: DaoAmigo.delete()");
		}
		
		sql = "delete from amigo where idPerfil = ? and idAmigo = ?";
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idAmigo);
			stm.setInt(2, idPerfil);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao deletar amigo: DaoAmigo.delete()");
		}
	}
	
	public static boolean isAmigo(Integer idPerfil, Integer idAmigo){
		boolean a = false;
		String sql = "select * from amigo where idPerfil = ? and idAmigo = ?";
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil);
			stm.setInt(2, idAmigo);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) a = true;
			stm.close(); rs.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao verificar Amigo");
		}
		
		return a;
	}
	
	public static Integer count(Integer idPerfil){
		return getList(idPerfil).size();
	}
}
