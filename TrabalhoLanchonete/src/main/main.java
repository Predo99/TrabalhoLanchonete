package main;

import java.sql.Connection;
import java.util.List;

import database.Conexao;
import database.dao.FuncionarioDAO;
import database.dao.IngredienteDAO;
import database.models.Funcionario;
import database.models.Ingrediente;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*FuncionarioDAO fd = new FuncionarioDAO();
		Funcionario f1 = new Funcionario("Pedro", "12345678900", "123456", 15000, true);
		Funcionario f2 = new Funcionario("User2", "12345678911", "123456", 10000, false);
		if(fd.cadastrar(f2)) 
			System.out.println("Funcionario cadastrado.");
		else 
			System.out.println("Erro ao cadastrar");
		
		Funcionario funcionario = fd.consultar("12345678911");
		
		if(funcionario != null)
			System.out.println(funcionario.getNomef() + "," + funcionario.getCpf() + "," 
								+ funcionario.getSalario() + "," + funcionarios.isGerente());
		else
			System.out.println("Funcionário não cadastrado.");
		 
		Funcionario f = fd.login("12345678910", "123456");
		if(f != null)
			System.out.println(f.getNomef() + "," + f.getCpf() + "," 
					+ f.getSalario() + "," + f.isGerente());
		else
			System.out.println("Funcionário não cadastrado.");
		fd.remover("12345678911");
		
		funcionarios = fd.consultar();
		if(funcionarios.size() > 0)
			for(int i = 0; i < funcionarios.size(); i++)
				System.out.println(funcionarios.get(i).getNomef() + "," + funcionarios.get(i).getCpf() + "," 
									+ funcionarios.get(i).getSalario() + "," + funcionarios.get(i).isGerente());
		else
			System.out.println("Não há funcionários cadastrados.");*/
		
		IngredienteDAO id = new IngredienteDAO();
		Ingrediente i1 = new Ingrediente("Bife de Hamburguer", 10, 5);
		Ingrediente i2 = new Ingrediente("Coca-Cola lata 300ml", 10, 5);
		id.cadastrar(i1);
		id.cadastrar(i2);
		
		Ingrediente ingrediente = id.consultar("Coca-Cola lata 300ml");
		if(ingrediente != null)
			System.out.println(ingrediente.getNomei() + "," 
								+ ingrediente.getQuantidade() + "," 
								+ ingrediente.getCusto());
		else
			System.out.println("Ingrediente não cadastrado.");
		
		System.out.println("-------------------------");
		i2.setQuantidade(15);
		id.atualizar(i2);
		
		ingrediente = id.consultar("Coca-Cola lata 300ml");
		if(ingrediente != null)
			System.out.println(ingrediente.getNomei() + "," 
								+ ingrediente.getQuantidade() + "," 
								+ ingrediente.getCusto());
		else
			System.out.println("Ingrediente não cadastrado.");
		
		System.out.println("-------------------------");
		id.atualizar(i2.getNomei(), "custo", 3.0);
		
		List<Ingrediente> ingredientes = id.consultar();
		for(int i = 0; i < ingredientes.size(); i++)
			System.out.println(ingredientes.get(i).getNomei() + "," 
								+ ingredientes.get(i).getQuantidade() + "," 
								+ ingredientes.get(i).getCusto());
		
		System.out.println("-------------------------");
		id.remover("Coca-Cola lata 300ml");
		
		ingredientes = id.consultar();
		for(int i = 0; i < ingredientes.size(); i++)
			System.out.println(ingredientes.get(i).getNomei() + "," 
								+ ingredientes.get(i).getQuantidade() + "," 
								+ ingredientes.get(i).getCusto());
		
	}

}
