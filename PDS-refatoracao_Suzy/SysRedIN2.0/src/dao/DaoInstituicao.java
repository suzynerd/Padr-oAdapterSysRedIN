package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Item;

public class DaoInstituicao {
	private static Connection conexao = Conexao.getConnection();
	
	public static List<Item> getList(){
		String sql = "select * from instituicao";
		List<Item> itens = new ArrayList<>();
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("idInstituicao"));
				item.setNome(rs.getString("sigla") + " - " +rs.getString("nome"));
				itens.add(item);
			}
			stm.close(); rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar Instituições");
		}
		return itens;
	}
	
	public static Item get(Integer idInstituicao){
		Item i = null;
		String sql = "select * from instituicao where idInstituicao = " + idInstituicao;
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				i = new Item();
				i.setId(rs.getInt("idInstituicao"));
				i.setNome(rs.getString("sigla") + " - " +rs.getString("nome"));
			}
			stm.close(); rs.close();
		} catch (SQLException e) {
			System.out.println("Erro a procurar Instituicao: DaoInstituicao.get()");
		}
		return i;
	}
}
