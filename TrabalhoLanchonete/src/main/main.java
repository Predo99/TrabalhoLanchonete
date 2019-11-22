package main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import database.Conexao;
import database.dao.CardapioDAO;
import database.dao.FuncionarioDAO;
import database.dao.IngredienteDAO;
import database.dao.OpcaoDAO;
import database.models.Cardapio;
import database.models.Funcionario;
import database.models.Ingrediente;
import database.models.Opcao;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//	OpcaoDAO OP = new OpcaoDAO();
		//List<Opcao> p = new ArrayList<Opcao>();
		//	p = OP.consultar();
		//for (Opcao opcao : p) {
			//	System.out.println(opcao.getNomeo() +" "+ opcao.getPreco()+
		//"\n-----------------------------");
		//}
		
		Cardapio cardapio1 = new Cardapio(1, null);
		CardapioDAO cDAO = new CardapioDAO();
		
		cardapio1.setOpcoes( cDAO.mostrarOpcoes());
		int i = 1;
		for (Opcao opcao : cardapio1.getOpcoes()) {
			
			System.out.println("Opcao"+i+": "+opcao.getNomeo()+" Preco: "+opcao.getPreco()+"\n--------------");
			i++;
		}
		
		Opcao o = new Opcao("suco 200ml", 0,null, null);
	   	System.out.println("Situação do adicionamento: "+cDAO.addOpcao(o,8));
	    cardapio1.setOpcoes( cDAO.mostrarOpcoes());
	    
	    i = 1;System.out.println("\n\n\nCardápio Atulizado, adicionado "+o.getNomeo());
		for (Opcao opcao : cardapio1.getOpcoes()) {
			
			System.out.println("Opcao"+i+": "+opcao.getNomeo()+" Preco: "+opcao.getPreco()+"\n--------------");
			i++;
		}
	    
		System.out.println(cDAO.removeOpcao(o));
		cardapio1.setOpcoes( cDAO.mostrarOpcoes());
		i = 1;System.out.println("\n\n\nCardápio Atulizado, removido "+o.getNomeo());
		for (Opcao opcao : cardapio1.getOpcoes()) {
			
			System.out.println("Opcao"+i+": "+opcao.getNomeo()+" Preco: "+opcao.getPreco()+"\n--------------");
			i++;
		}
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
								+ funcionario.getSalario() + "," + funcionario.isGerente());
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
		
		
		Ingrediente i1 = new Ingrediente("Bife de Hamburguer", 10, 5);
		Ingrediente i2 = new Ingrediente("Coca-Cola lata 300ml", 10, 5);
		/*IngredienteDAO id = new IngredienteDAO();
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
								+ ingredientes.get(i).getCusto());*/
		
		/*OpcaoDAO od = new OpcaoDAO();
		List<Ingrediente> ingredientes = new ArrayList();
		ingredientes.add(i1);
		ingredientes.add(i2);
		
		Opcao o1 = new Opcao("Opcao 1", 10.0, null, ingredientes);
		if(od.cadastrar(o1))
			System.out.println("Cadastrado com sucesso");
		else
			System.out.println("Erro ao cadastrar");
		
		List<Opcao> opcoes = od.consultar();
		for(int i = 0; i < opcoes.size(); i++) {
			System.out.println(opcoes.get(i).getNomeo() + "," + opcoes.get(i).getPreco());
			for(int j = 0; j < opcoes.get(i).getIngredientes().size(); j++)
				System.out.println(opcoes.get(i).getIngredientes().get(j).getNomei());
			System.out.println("------------------------");
		}
		
		ingredientes.add(i1);
		Opcao o2 = new Opcao("Opcao 2", 11.0, null, ingredientes);
		od.cadastrar(o2);
		o2.setPreco(12);
		od.atualizar(o2);
		od.atualizar(o2.getNomeo(), 10);
		ingredientes.add(i1);
		od.atualizar(o2.getNomeo(), ingredientes);
		//od.remover(o2.getNomeo());
		
		Opcao o3 = od.consultar("Opcao 2");
		if(o3 != null) {
			System.out.println(o3.getNomeo() + "," + o3.getPreco());
			for(int i = 0; i < o3.getIngredientes().size(); i++)
				System.out.println(o3.getIngredientes().get(i).getNomei());
		}else {
			System.out.println("Opção não cadastrada.");
		}*/
			
	}

}
