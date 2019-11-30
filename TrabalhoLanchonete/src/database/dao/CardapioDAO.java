package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Conexao;
import database.models.Opcao;

public class CardapioDAO {

	private Connection connection;

	public CardapioDAO() {
		this.connection = Conexao.getConexao();

	}

	public ArrayList<Opcao> mostrarOpcoes() {
		ArrayList<Opcao> opcoes = new ArrayList<Opcao>();
		try {
			String sql = "select * from opcoes_cardapio";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getInt("id")+" "+rs.getString("nomeo")+
				// " "+rs.getInt("codigoc")+ "\n---------------");

				sql = "select * from opcao where nomeo =?";
				PreparedStatement stmt2 = connection.prepareStatement(sql);
				stmt2.setString(1, rs.getString("nomeo"));
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					Opcao o = new Opcao();
					o.setNomeo(rs2.getString("nomeo"));
					o.setPreco(rs2.getDouble("preco"));
					if (rs2.getBytes("imagem") != null) {
						byte[] data = rs2.getBytes("imagem");
						o.setImagem(data);
					} else
						o.setImagem(null);
					opcoes.add(o);
				}
				stmt2.close();
				rs2.close();
			}
			rs.close();
			stmt.close();
			return opcoes;
		} catch (SQLException e) {
			System.out.println("--Consulta Inválida--");
			return opcoes;
		}
	}

	public boolean addOpcao(Opcao o) {
		try {
			String sql = "insert into opcoes_cardapio (codigoc,nomeo) values(?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setString(2, o.getNomeo());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean removeOpcao(String o) {
		try {
			String sql = "delete from opcoes_cardapio where nomeo = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, o);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
