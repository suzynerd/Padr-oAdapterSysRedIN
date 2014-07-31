package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Notificacao;

public class DaoNotificacao {
	private static Connection conexao = Conexao.getConnection();
	
	public static void insert(Notificacao not){
		String sql = "insert into notificacao (idTurma, idPerfil) values (?,?)";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, not.getIdTurma());
			stm.setInt(2, not.getIdPerfil());
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public static List<Notificacao> getList(Integer idTurma){
		List<Notificacao> not = new ArrayList<>();
		String sql = "select notificacao.idNotificacao, "
				+ "concat('O aluno ', perfil.nome, 'deseja entrar nessa turma.') "
				+ "from notificacao "
				+ "inner join perfil on perfil.idPerfil = notificacao.idPerfil "
				+ "where notificacao.idTurma = ?";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idTurma);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				not.add(new Notificacao(rs));
			}
			stm.close();rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return not;
	}
	
	public static void remove(Integer id){
		String sql = "delete from notificacao where idNotificacao = " + id;
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.executeUpdate(); stm.close();
		} catch (SQLException e) {e.printStackTrace();}
		
	}

	public static void aceitar(Integer idNotificacao) {
		String sql = "select * from notificacao where idNotificacao = " + idNotificacao;
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery(); rs.next();
			Notificacao not = new Notificacao(rs);
			DaoTurma.insertMembro(not.getIdTurma(), not.getIdPerfil());
			remove(idNotificacao); stm.close(); rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		
	}
}
