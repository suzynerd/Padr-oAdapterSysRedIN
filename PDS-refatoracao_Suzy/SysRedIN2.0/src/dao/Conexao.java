package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
	public static Conexao c;
	public static Connection conexao;
	
	public static synchronized Conexao getInstance(){
		if(c == null)
			c = new Conexao();
		return c;
	}
	
	public static Connection getConnection(){
		if (conexao == null) {
			try {
				conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
				PreparedStatement stm = conexao.prepareStatement("SET GLOBAL max_allowed_packet=68688824");
				stm.executeUpdate();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexao;
	}
}
