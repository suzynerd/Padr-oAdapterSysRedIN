package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.Tool;
import dominio.Perfil;
import dominio.construtores.PerfilConstrutor;
import dominio.validacao.PerfilValidacao;

public class DaoPerfil{
	private static Connection conexao = Conexao.getConnection();
	
	public static boolean insert(Perfil perfil) {
		if (!PerfilValidacao.validarNovo(perfil)) return false;
		String sql = "insert into perfil (nome, email, senha, idTipo, idInstituicao) values (?, ?, ?, ?, ?)";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, perfil.getNome());
			stm.setString(2, perfil.getEmail());
			stm.setString(3, perfil.getSenha());
			stm.setInt(4, perfil.getTipo().getId());
			stm.setInt(5, perfil.getInstituicao().getId());
			stm.executeUpdate();
			stm.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir um novo Perfil no Banco de Dados");
			return false;
		}
	}

	public static List<Perfil> getList() {
		List<Perfil> perfis = new ArrayList<>();
		
		String sql = "select perfil.idPerfil, perfil.idTipo, perfil.nome, perfil.email, perfil.idInstituicao, "
				+ "concat_ws(' - ', instituicao.sigla, instituicao.nome) as nomeInstituicao "
				+ "from perfil "
				+ "inner join instituicao on perfil.idInstituicao = instituicao.idInstituicao "
				+ "where idPerfil <> ?";
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, Tool.getSessionID());
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				Perfil p = new Perfil();
				p.setId(rs.getInt("idPerfil"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setIdInstituicao(rs.getInt("idInstituicao"));
//				p.setInstituicao("nomeInstituicao");
				p.setIdTipo(rs.getInt("idTipo"));
				perfis.add(p);
			}
			stm.close(); rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar Perfis");
		}
		return perfis;
	}
	
	public static List<Perfil> getList(Integer id){
		List<Perfil> perfis = new ArrayList<>();
		String sql = 
				"select perfil.idPerfil, perfil.nome, perfil.email, tipoperfil.nomeTipo, instituicao.idInstituicao, "
				+ "concat_ws(' - ', instituicao.sigla, instituicao.nome) as nomeInstituicao "
				+ "from perfil "
				+ "inner join tipoperfil on perfil.idTipo = tipoperfil.idTipo "
				+ "inner join instituicao on perfil.idInstituicao = instituicao.idInstituicao "
				+ "where perfil.idPerfil <> " + id;
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				Perfil perfil = PerfilConstrutor.construir(rs);
				perfis.add(perfil);
			}
			stm.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Perfis 2");
		}
		
		
		return perfis;
	}

	public static void delete(Integer id) {
		String sql = "delete from perfil where idPerfil = ?";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, id);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Perfil");
		}
		
	}

	public static Perfil get(Integer id) {
		Perfil perfil = new Perfil();
		String sql = "select * from perfil where idperfil = " + id;
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			rs.next();
			perfil.setId(rs.getInt("idPerfil"));
			perfil.setNome(rs.getString("nome"));
			perfil.setEmail(rs.getString("email"));
			perfil.setIdInstituicao(rs.getInt("idInstituicao"));
			perfil.setIdTipo(rs.getInt("idTipo"));
			stm.close();
			rs.close();
		}catch(SQLException e){
			System.out.println("Erro ao procurar Perfil");
		}
		
		return perfil;
	}
	
	public static boolean exists(String login, String senha){
		String sql = "select * from perfil where email = ? AND senha = ?";
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, login);
			stm.setString(2, senha);
			ResultSet rs = stm.executeQuery();
			if (rs.next())
				return true;
			rs.close();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar Perfil boolean");
		}
		return false;
	}
	
	public static Perfil logar(String login, String senha){
		
		Perfil p = null;
		String sql = "select * from perfil where email = ? AND senha = ?";
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, login);
			stm.setString(2, senha);
			ResultSet rs = stm.executeQuery();
			rs.next();
			
			p = new Perfil();
			p.setId(rs.getInt("idPerfil"));
			p.setNome(rs.getString("nome"));
			p.setEmail(rs.getString("email"));
			p.setIdInstituicao(rs.getInt("idInstituicao"));
			p.setIdTipo(rs.getInt("idTipo"));
			
			stm.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar Perfil para login");
		}
		
		return p;
	}
	
	public static boolean existsEmail(String email) {
		String sql = "select * from perfil where email = '" + email + "'";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			if(rs.next())
				return true;
			rs.close();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao Verificar E-mail");
		}
		return false;		
	}
	
	public static boolean existsEmail(Perfil perfil){
		return DaoPerfil.existsEmail(perfil.getEmail());
	}
	
	public static void update(String nome, String email, Integer idPerfil){
		String sql = "update perfil set nome = ?, email = ? where idPerfil = " + idPerfil;
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, nome);
			stm.setString(2, email);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao alterar dados do Perfil (perfil)");
		}
	}
	
	public static void update(String senha, Integer idPerfil){
		String sql = "update perfil set senha = ? where idPerfil = " + idPerfil;
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, senha);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao alterar dados do Perfil (nome email)");
		}
	}

}
