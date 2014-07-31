package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Item;

public class DaoTipo {
	private static Connection conexao = Conexao.getConnection();
	
	public static List<Item> getList(){
		List<Item> tipos = new ArrayList<>();
		String sql = "select * from tipoperfil";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Item l = new Item();
				l.setId(rs.getInt("idTipo"));
				l.setNome(rs.getString("nomeTipo"));
				tipos.add(l);
			}
			stm.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Erro a Listar tipos de perfil");
		}
		return tipos;
	}
}
