package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Amigo;
import dominio.Item;
import dominio.Turma;
import dominio.construtores.TurmaConstrutor;
import dominio.validacao.TurmaValidacao;

public class DaoTurma {
	private static Connection conexao = Conexao.getConnection();
	
	public static boolean insert(Turma turma){
		
		if (!TurmaValidacao.validarNovo(turma)) return false;
		String sql = "insert into turma (nome, descricao, idPerfil) values (?,?,?)";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, turma.getNome());
			stm.setString(2, turma.getDescricao());
			stm.setInt(3, turma.getPerfil().getId());
			stm.executeUpdate();
			stm.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Turma");
			return false;
		}
		
	}
	
	public static List<Turma> getList(Integer idPerfil, Integer idTipo){
		List<Turma> turmas = new ArrayList<>();
		String  sql;
		if(idTipo == 1)
			sql = "select turma.*, perfil.nome as nomeDono "
				+ "from membro "
				+ "inner join turma on turma.idTurma = membro.idTurma"
				+ "inner join perfil on turma.idPerfil = perfil.idPerfil "
				+ "where membro.idPerfil <> ?";
		else
			sql = "select turma.*, perfil.nome as nomeDono "
				+ "from turma "
				+ "inner join perfil on turma.idPerfil = perfil.idPerfil "
				+ "where turma.idPerfil <> ?";
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Turma turma = TurmaConstrutor.construir(rs);
				turmas.add(turma);
			}
			rs.close(); stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar turmas");
		}
		
		return turmas;
	}
	
	public static List<Turma> getListForProfessor(Integer idProfessor){
		List<Turma> turmas = new ArrayList<>();
		String sql = "select turma.*, perfil.nome as nomeDono "
				+ "from turma "
				+ "inner join perfil on turma.idPerfil = perfil.idPerfil "
				+ "where turma.idPerfil = ?";
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idProfessor);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Turma turma = TurmaConstrutor.construir(rs);
				turmas.add(turma);
			}
			rs.close(); stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar turmas do professor");
		}
		
		return turmas;
	}
	
	public static List<Turma> getListForAluno(Integer idAluno){
		List<Turma> turmas = new ArrayList<>();
		String sql = "select turma.*, perfil.nome as nomeDono "
				+ "from membro "
				+ "inner join turma on turma.idTurma = membro.idTurma "
				+ "inner join perfil on perfil.idPerfil = membro.idPerfil "
				+ "where membro.idPerfil = ?";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idAluno);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Turma turma = TurmaConstrutor.construir(rs);
				turmas.add(turma);
			}
			rs.close(); stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar turmas do professor");
		}
		
		
		return turmas;
	}
	
	public static Turma findTurma(Integer idTurma){
		Turma t = null;
		String sql = "select turma.*, perfil.nome as nomeDono "
				+ "from turma "
				+ "inner join perfil on perfil.idPerfil = turma.idPerfil "
				+ "where turma.idTurma = ?";
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idTurma);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				t = TurmaConstrutor.construir(rs);
			}
			stm.close(); rs.close();
		} catch (SQLException e) {
			System.out.println("erro ao procurar turma");
		}
				
		return t;
	}
	
	public static void insertMembro(Integer idTurma, Integer idAluno){
		String sql = "insert into membro (idTurma, idPerfil) values (?,?)";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idTurma);
			stm.setInt(2, idAluno);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir membro");
		}
		
	}
	public static void removeMembro(Integer idTurma, Integer idAluno){
		String sql = "delete from membro where idTurma = ? AND idPerfil = ?";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idTurma);
			stm.setInt(2, idAluno);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao remover membro");
		}
		
	}
	
	public static List<Item> getListMembros(Integer idTurma){
		List<Item> membros = new ArrayList<>();
		String sql = "select perfil.idPerfil as id, perfil.nome "
				+ "from membro "
				+ "inner join perfil on membro.idPerfil = perfil.idPerfil "
				+ "where membro.idTurma = ?";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idTurma);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setNome(rs.getString("nome"));
				membros.add(i);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao Listar Membros");
		}
		return membros;
	}
	
	public static List<Item> getListNovoAluno(Integer idTurma, Integer idProfessor){
		List<Item> alunos = new ArrayList<>();
		List<Amigo> amigos = DaoAmigo.getList(idProfessor);
		for (Amigo amigo : amigos) {
			Item i = new Item();
			i.setId(amigo.getId()); i.setNome(amigo.getNome());
			alunos.add(i);
		}
			for (int i = alunos.size() -1; i > -1; i--) {
				for (Item membro : DaoTurma.getListMembros(idTurma)) {
					if(alunos.get(i).getId() == membro.getId())
						alunos.remove(i);
				}
				if(alunos.size() !=0)
				if(DaoPerfil.get(alunos.get(i).getId()).getIdTipo() == 2)
					alunos.remove(i);
			}
		
		return alunos;
	}
	
	public static boolean contains(Integer idTurma, Integer idAluno){
		String sql = "select * from membro where idTurma = ? and idPerfil = ?";
		
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idTurma); stm.setInt(2, idAluno);
			ResultSet rs = stm.executeQuery();
			if(rs.next())
				return true;
			stm.close(); rs.close();
		} catch (SQLException e) {
			System.out.println("erro ao verificar preseça de aluno em uma turma");
		}
		return false;
	}

	public static void fecharTurma(Integer idTurma) {
		String[] sqls = {
				
				"delete from membro where idTurma = " + idTurma,
				"delete from notificacao where idTurma = " + idTurma,
				"delete from post where idTurma = " + idTurma,
				"delete from turma where idTurma = " + idTurma
			};
		PreparedStatement stm = null;
		try {
			for (String string : sqls) {
				stm = conexao.prepareStatement(string);
				stm.executeUpdate();
			}
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}

