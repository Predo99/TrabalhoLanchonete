package database.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Conexao;
import database.models.Funcionario;

public class FuncionarioDAO {

	private Connection connection;

    public FuncionarioDAO() {
        this.connection = Conexao.getConexao(); 
    }
    
    public boolean cadastrar (Funcionario funcionario) {
    	try {
            String sql = "insert into funcionario values (?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNomef());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getSenha());
            stmt.setDouble(4, funcionario.getSalario());
            stmt.setBoolean(5, funcionario.isGerente());
            stmt.execute();
            stmt.close();
            return true;
    	}catch (SQLException e) {
            return false;
        }	     
    }
    
    public boolean atualizar (Funcionario funcionario) {
    	try {
            String sql = "update funcionario set nomef = ?, senha = ?, salario = ?, gerente = ? where cpf = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNomef());
            stmt.setString(2, funcionario.getSenha());
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setBoolean(4, funcionario.isGerente());
            stmt.setString(5, funcionario.getCpf());
            stmt.execute();
            stmt.close();
            return true;
    	}catch (SQLException e) {
    		return false;
        }	
    }
    
    public Funcionario consultar(String cpf) {
    	Funcionario f = new Funcionario();
    	try {
    		String sql = "select * from funcionario where cpf = ?";
    		PreparedStatement stmt = connection.prepareStatement(sql);
    		stmt.setString(1, cpf);
    		ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				f.setNomef(rs.getString("nomef"));
				f.setCpf(rs.getString("cpf"));
				f.setSenha(rs.getString("senha"));
				f.setSalario(rs.getDouble("salario"));	
				f.setGerente(rs.getBoolean("gerente"));
			}
			rs.close();
            stmt.close();
            return f;
    	}catch (SQLException e) {
    		return null;
        }	
    }
    
    public List<Funcionario> consultar() {
    	List<Funcionario> funcionarios = new ArrayList();
    	try {
    		String sql = "select * from funcionario";
    		PreparedStatement stmt = connection.prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Funcionario f = new Funcionario();
				f.setNomef(rs.getString("nomef"));
				f.setCpf(rs.getString("cpf"));
				f.setSenha(rs.getString("senha"));
				f.setSalario(rs.getDouble("salario"));	
				f.setGerente(rs.getBoolean("gerente"));
				funcionarios.add(f);
			}
			rs.close();
            stmt.close();
            return funcionarios;
    	}catch (SQLException e) {
    		return funcionarios;
        }	
    }
    
    public boolean remover (String cpf) {
    	try {
    		String sql = "delete from funcionario where cpf = ?";
    		PreparedStatement stmt = connection.prepareStatement(sql);
    		stmt.setString(1, cpf);
    		stmt.execute();
            stmt.close();
            return true;
    	}catch (SQLException e) {
            return false;
        }	
    }
    
    public Funcionario login (String cpf, String senha) {
    	Funcionario f = null;
    	try {
    		String sql = "select * from funcionario where cpf = ? and senha = ?";
    		PreparedStatement stmt = connection.prepareStatement(sql);
    		stmt.setString(1, cpf);
    		stmt.setString(2, senha);
    		ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				f = new Funcionario();
				f.setNomef(rs.getString("nomef"));
				f.setCpf(rs.getString("cpf"));
				f.setSenha(rs.getString("senha"));
				f.setSalario(rs.getDouble("salario"));	
				f.setGerente(rs.getBoolean("gerente"));
			}
			rs.close();
            stmt.close();
            return f;
    	}catch (SQLException e) {
    		return f;
        }
    }
    
}
