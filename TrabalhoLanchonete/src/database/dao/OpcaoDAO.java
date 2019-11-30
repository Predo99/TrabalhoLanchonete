package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import database.Conexao;
import database.models.Ingrediente;
import database.models.Opcao;

public class OpcaoDAO {

	private Connection connection;

	public OpcaoDAO() {
		this.connection = Conexao.getConexao();
	}

	public boolean cadastrar(Opcao opcao) {
		try {
			String sql = "insert into opcao values (?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, opcao.getNomeo());
			stmt.setDouble(2, opcao.getPreco());
			if (opcao.getImagem() != null) {
				SerialBlob blob = new SerialBlob(opcao.getImagem());
				stmt.setBlob(3, blob);
			} else
				stmt.setNull(3, java.sql.Types.BLOB);
			stmt.execute();
			stmt.close();

			if(addIngredientes(opcao))
            	return true;
            else {
            	remover(opcao.getNomeo());
            	return false;
            }
		} catch (SQLException e) {
			return false;
		}
	}
	
	private boolean addIngredientes(Opcao opcao) {
		try { 
			String sql = "insert into ingredientes_opcao (nomei, nomeo) values (?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            for(int i = 0; i < opcao.getIngredientes().size(); i++) {
            	stmt.setString(1, opcao.getIngredientes().get(i).getNomei());
            	stmt.setString(2, opcao.getNomeo());
            	stmt.execute();
            }
            stmt.close();
			return true;
		}catch(SQLException e) {
			return false;
		}
    }

	public boolean atualizar(Opcao opcao) {
		try {
			String sql = "update opcao set preco = ?, imagem = ? where nomeo = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, opcao.getPreco());
			if (opcao.getImagem() != null) {
				SerialBlob blob = new SerialBlob(opcao.getImagem());
				stmt.setBlob(2, blob);
			} else
				stmt.setNull(2, java.sql.Types.BLOB);
			stmt.setString(3, opcao.getNomeo());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean atualizar(Opcao opcao,String nomeo) {
		try {
			String sql = "update opcao set preco = ?, imagem = ?, nomeo = ? where nomeo = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, opcao.getPreco());
			if (opcao.getImagem() != null) {
				SerialBlob blob = new SerialBlob(opcao.getImagem());
				stmt.setBlob(2, blob);
			} else
				stmt.setNull(2, java.sql.Types.BLOB);
			stmt.setString(3, opcao.getNomeo());
			stmt.setString(4, nomeo);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean atualizar(String nomeo, double preco) {
		try {
			String sql = "update opcao set preco = ? where nomeo = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, preco);
			stmt.setString(2, nomeo);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean atualizar(String nomeo, byte[] imagem) {
		try {
			String sql = "update opcao set imagem = ? where nomeo = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			if (imagem != null) {
				SerialBlob blob = new SerialBlob(imagem);
				stmt.setBlob(1, blob);
			} else
				stmt.setNull(1, java.sql.Types.BLOB);
			stmt.setString(2, nomeo);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean atualizar(String nomeo, List<Ingrediente> ingredientes) {
		try {
			String sql = "delete from ingredientes_opcao where nomeo = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeo);
			stmt.execute();
			stmt.close();

			sql = "insert into ingredientes_opcao (nomei, nomeo) values (?,?)";
			stmt = connection.prepareStatement(sql);
			for (int i = 0; i < ingredientes.size(); i++) {
				stmt.setString(1, ingredientes.get(i).getNomei());
				stmt.setString(2, nomeo);
				stmt.execute();
			}
			stmt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Opcao consultar(String nomeo) {
		Opcao o = null;
		try {
			String sql = "select * from opcao where nomeo = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				o = new Opcao();
				o.setNomeo(rs.getString("nomeo"));
				o.setPreco(rs.getDouble("preco"));
				if (rs.getBytes("imagem") != null) {
					byte[] data = rs.getBytes("imagem");
					o.setImagem(data);
				} else
					o.setImagem(null);

				List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
				String sql2 = "select * from ingredientes_opcao where nomeo = ?";
				PreparedStatement stmt2 = connection.prepareStatement(sql2);
				stmt2.setString(1, nomeo);
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					IngredienteDAO id = new IngredienteDAO();
					Ingrediente ingrediente = id.consultar(rs2.getString("nomei"));
					ingredientes.add(ingrediente);
				}
				o.setIngredientes(ingredientes);
				rs2.close();
				stmt2.close();
			}
			rs.close();
			stmt.close();
			return o;
		} catch (SQLException e) {
			e.printStackTrace();
			return o;
		}
	}

	public List<Opcao> consultar() {
		List<Opcao> opcoes = new ArrayList<Opcao>();
		try {
			String sql = "select * from opcao";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Opcao o = new Opcao();
				o.setNomeo(rs.getString("nomeo"));
				o.setPreco(rs.getDouble("preco"));
				if (rs.getBytes("imagem") != null) {
					byte[] data = rs.getBytes("imagem");
					o.setImagem(data);
				} else
					o.setImagem(null);

				List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
				String sql2 = "select * from ingredientes_opcao where nomeo = ?";
				PreparedStatement stmt2 = connection.prepareStatement(sql2);
				stmt2.setString(1, o.getNomeo());
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					IngredienteDAO id = new IngredienteDAO();
					Ingrediente ingrediente = id.consultar(rs2.getString("nomei"));
					ingredientes.add(ingrediente);
				}
				o.setIngredientes(ingredientes);
				rs2.close();
				stmt2.close();
				opcoes.add(o);
			}
			rs.close();
			stmt.close();
			return opcoes;
		} catch (SQLException e) {
			return opcoes;
		}
	}

	public boolean remover(String nomeo) {
		try {
			String sql = "delete from opcao where nomeo = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeo);
			stmt.execute();
			stmt.close();

			sql = "delete from ingredientes_opcao where nomeo = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeo);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
