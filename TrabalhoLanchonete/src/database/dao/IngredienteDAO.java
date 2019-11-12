package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Conexao;
import database.models.Ingrediente;

public class IngredienteDAO {
	
	private Connection connection;

    public IngredienteDAO() {
        this.connection = Conexao.getConexao(); 
    }
	
    public boolean cadastrar (Ingrediente ingrediente) {
    	try {
            String sql = "insert into ingrediente values (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ingrediente.getNomei());
            stmt.setDouble(2, ingrediente.getQuantidade());
            stmt.setDouble(3, ingrediente.getCusto());
            stmt.execute();
            stmt.close();
            return true;
    	}catch (SQLException e) {
            return false;
        }
    }
    
    public boolean atualizar (Ingrediente ingrediente) {
    	try {
            String sql = "update ingrediente set quantidade = ?, custo = ? where nomei = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, ingrediente.getQuantidade());
            stmt.setDouble(2, ingrediente.getCusto());
            stmt.setString(3, ingrediente.getNomei());
            stmt.execute();
            stmt.close();
            return true;
    	}catch (SQLException e) {
    		return false;
        }
    }
    
    public boolean atualizar (String nomei, String coluna, Double valor) {
    	try {
            String sql = "update ingrediente set " + coluna + " = ? where nomei = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, valor);
            stmt.setString(2, nomei);
            stmt.execute();
            stmt.close();
            return true;
    	}catch (SQLException e) {
    		return false;
        }
    }
    
    public Ingrediente consultar (String nomei){
    	Ingrediente i = new Ingrediente();
    	try {
    		String sql = "select * from ingrediente where nomei = ?";
    		PreparedStatement stmt = connection.prepareStatement(sql);
    		stmt.setString(1, nomei);
    		ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i.setNomei(rs.getString("nomei"));
				i.setQuantidade(rs.getDouble("quantidade"));
				i.setCusto(rs.getDouble("custo"));
			}
			rs.close();
            stmt.close();
            return i;
    	}catch (SQLException e) {
    		return null;
        }	
    }
    
    public List<Ingrediente> consultar (){
    	List<Ingrediente> ingredientes = new ArrayList();
    	try {
    		String sql = "select * from ingrediente";
    		PreparedStatement stmt = connection.prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Ingrediente i = new Ingrediente();
				i.setNomei(rs.getString("nomei"));
				i.setQuantidade(rs.getDouble("quantidade"));
				i.setCusto(rs.getDouble("custo"));
				ingredientes.add(i);
			}
			rs.close();
            stmt.close();
            return ingredientes;
    	}catch (SQLException e) {
    		return ingredientes;
        }
    }
    
    public boolean remover (String nomei) {
    	try {
    		String sql = "delete from ingrediente where nomei = ?";
    		PreparedStatement stmt = connection.prepareStatement(sql);
    		stmt.setString(1, nomei);
    		stmt.execute();
            stmt.close();
            return true;
    	}catch (SQLException e) {
            return false;
        }
    }
	
}
