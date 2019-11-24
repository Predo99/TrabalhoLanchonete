package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection getConexao(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanchonete", "root", "");
		}catch(SQLException e) {
			System.err.println("Erro");
			e.getMessage();
		}
		return conn;
	}
}
