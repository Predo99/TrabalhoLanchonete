package main;
import java.util.ArrayList;

import java.util.List;

import database.Conexao;
import database.dao.CardapioDAO;
import database.dao.FuncionarioDAO;
import database.dao.IngredienteDAO;
import database.dao.OpcaoDAO;
import database.dao.PedidoDAO;
import database.models.Funcionario;
import database.models.Ingrediente;
import database.models.Opcao;
import database.models.Pedido;


public class main {

	public static void main(String[] args) {


		//Lista de Ingredientes

		Ingrediente i1 = new Ingrediente("Bife de Hamburguer", 10, 5);
		Ingrediente i2 = new Ingrediente("Alface", 10, 2);
		Ingrediente i3 = new Ingrediente("Salsicha", 10, 3);
		Ingrediente i4 = new Ingrediente("Batata Palha", 10, 2);
		Ingrediente i5 = new Ingrediente("Leite", 10, 4);
		Ingrediente i6 = new Ingrediente("Chocolate", 10, 5);
		Ingrediente i7 = new Ingrediente("Tomate", 10, 2);
		Ingrediente i8 = new Ingrediente("Massa", 10, 4);


		//Criando 4 arrays para as 4 opcoes que teremos.

		List<Ingrediente> io1 = new ArrayList();
		List<Ingrediente> io2 = new ArrayList();
		List<Ingrediente> io3 = new ArrayList();
		List<Ingrediente> io4 = new ArrayList();

		// Adicionando os ingredientes no array da Classe Ingrediente.
		io1.add(i1);
		io1.add(i2);
		io2.add(i3);
		io2.add(i4);
		io3.add(i5);
		io3.add(i6);
		io4.add(i7);
		io4.add(i8);

		// Cadastrando todos os ingredientes no banco.
		IngredienteDAO ingre = new IngredienteDAO();
		ingre.cadastrar(i1);
		ingre.cadastrar(i2);
		ingre.cadastrar(i3);
		ingre.cadastrar(i4);
		ingre.cadastrar(i5);
		ingre.cadastrar(i6);
		ingre.cadastrar(i7);
		ingre.cadastrar(i8);

		// Criando as 4 opcoes que teremos.

		Opcao op1=new Opcao();
		Opcao op2=new Opcao();
		Opcao op3=new Opcao();
		Opcao op4=new Opcao();

		// Setando os nomes e os preços das 4 opcoes.

		op1.setNomeo("OP1-Hamburger Simples");
		op1.setPreco(10.5);
		op2.setNomeo("OP2-Cachorro Quente");
		op2.setPreco(6.0);
		op3.setNomeo("OP3-Milk Shake");
		op3.setPreco(5.5);
		op4.setNomeo("OP4-Pizza Simples");
		op4.setPreco(17.5);


		// Setar os arrays de ingredientes em cada opcao.
		op1.setIngredientes(io1);
		op2.setIngredientes(io2);
		op3.setIngredientes(io3);
		op4.setIngredientes(io4);        

		// Cadastrando todas as opcoes no banco.
		OpcaoDAO opd= new OpcaoDAO();

		opd.cadastrar(op1);
		opd.cadastrar(op2);
		opd.cadastrar(op3);
		opd.cadastrar(op4);


		// Criando 2 arrays de opcoes uma para cada pedido
		List<Opcao> opcoesPedido1 = new ArrayList();
		List<Opcao> opcoesPedido2 = new ArrayList();

		// Adicionando as opcoes nos arrays.
		opcoesPedido1.add(op1);
		opcoesPedido1.add(op2);
		opcoesPedido2.add(op3);
		opcoesPedido2.add(op4);



		//______________________________________________________________________________________________	    
		//TESTANDO CADASTRAR NA TABELA DE COMPLEMENTOS.



		Ingrediente comp1= new Ingrediente("Bacon", 10, 2);
		Ingrediente comp2 = new Ingrediente("Ovo", 10, 1);
		Ingrediente comp3 = new Ingrediente("Queijo", 10, 2);
		Ingrediente comp4= new Ingrediente("Peperoni", 10, 3);


		IngredienteDAO ingre2= new IngredienteDAO();
		ingre2.cadastrar(comp1);
		ingre2.cadastrar(comp2);
		ingre2.cadastrar(comp3);
		ingre2.cadastrar(comp4);

		List<Ingrediente> c1 = new ArrayList();
		List<Ingrediente> c2 = new ArrayList();
		List<Ingrediente> c3 = new ArrayList();
		
		c1.add(comp1);
		c1.add(comp2);
		c2.add(comp3);
		c2.add(comp4);
		c3.add(i4);


		//______________________________________________________________________________________________	        


		// Criando 2 pedidos.

		Pedido ped1=new Pedido();
		Pedido ped2=new Pedido();

		// Setando informaçoes sobre cada pedido.
		ped1.setEmbalagem("Plastico");
		ped1.setFormaPag("Cartao");
		ped1.setData(ped1.getDataB());

		ped2.setEmbalagem("Caixa");
		ped2.setFormaPag("Dinheiro");
		ped2.setData(ped2.getDataB());


		// Setando 2 opcoes para cada pedido.	    
		ped1.setOpcoes(opcoesPedido1);
		ped1.setPreco(ped1.getPrecoBanco());
		
		ped2.setOpcoes(opcoesPedido2);
		ped2.setPreco(ped2.getPrecoBanco());
		
		/*System.out.println(ped1.getEmbalagem());
		System.out.println(ped1.getFormaPag());
		System.out.println(ped1.getPreco());
		System.out.println(ped1.getData());
		for (int i = 0; i < ped1.getOpcoes().size(); i++) {
			System.out.println(ped1.getOpcoes().get(i).getNomeo());
			System.out.println(ped1.getOpcoes().get(i).getPreco());
			for (int j = 0; j < ped1.getOpcoes().get(i).getIngredientes().size(); j++) {
				System.out.println(ped1.getOpcoes().get(i).getIngredientes().get(j).getNomei());
			}
		}
		
		System.out.println("---------");
		
		System.out.println(ped2.getEmbalagem());
		System.out.println(ped2.getFormaPag());
		System.out.println(ped2.getPreco());
		System.out.println(ped2.getData());
		for (int i = 0; i < ped2.getOpcoes().size(); i++) {
			System.out.println(ped2.getOpcoes().get(i).getNomeo());
			System.out.println(ped2.getOpcoes().get(i).getPreco());
			for (int j = 0; j < ped2.getOpcoes().get(i).getIngredientes().size(); j++) {
				System.out.println(ped2.getOpcoes().get(i).getIngredientes().get(j).getNomei());
			}
		}*/

		// Setando os complementos para os pedidos.

		// Cadastrando os 2 pedidos no banco.
		PedidoDAO pd= new PedidoDAO();	    

		//pd.cadastrar(ped1);
		//pd.cadastrar(ped2);

		// Consultando um pedido no banco
		//pd.consultar(ped1.getCodigop());

		//System.out.println("Finalizado cadastro no banco");


		Pedido pedidoConsulta = pd.consultar(2);
		/*if(pedidoConsulta != null)
			System.out.println(pedidoConsulta.getCodigop() + ",   " 
					+ pedidoConsulta.getEmbalagem()+ ",  " 
					+ pedidoConsulta.getFormaPag()
					+ ",  "+ pedidoConsulta.getPreco() + ",   "+ pedidoConsulta.getData());
		else
			System.out.println("pedido não cadastrado.");

		System.out.println("Consulta finalizada" + "\n");


		System.out.println("Consultando pedido 3 (que nao tem) ");
		pedidoConsulta = pd.consultar(3);
		if(pedidoConsulta != null)
			System.out.println(pedidoConsulta.getCodigop() + ",   " 
					+ pedidoConsulta.getEmbalagem()+ ",  " 
					+ pedidoConsulta.getFormaPag()
					+ ",  "+ pedidoConsulta.getPreco() + ",   "+ pedidoConsulta.getData());
		else
			System.out.println("pedido não cadastrado.");

		System.out.println("Consulta finalizada");*/
		
		System.out.println("---------");
		//pd.cadastrar(ped1);
		
		//pedidoConsulta = pd.consultar(4);
		//pd.cadastrarComplementos(pedidoConsulta, 0, c1);
		/*pedidoConsulta = pd.consultar(4);
		
		System.out.println(pedidoConsulta.getPreco());
		System.out.println(pedidoConsulta.getData());
		for (int i = 0; i < pedidoConsulta.getOpcoes().size(); i++) {
			System.out.println(pedidoConsulta.getOpcoes().get(i).getNomeo());
			System.out.println(pedidoConsulta.getOpcoes().get(i).getPreco());
			for (int j = 0; j < pedidoConsulta.getOpcoes().get(i).getIngredientes().size(); j++) {
				System.out.println(pedidoConsulta.getOpcoes().get(i).getIngredientes().get(j).getNomei());
			}
		}
		/*
		pedidoConsulta = pd.consultar(4);
		//pd.cadastrarRemocoes(pedidoConsulta, 1, c3);
		System.out.println("---------------");
		System.out.println(pedidoConsulta.getPreco());
		System.out.println(pedidoConsulta.getData());
		for (int i = 0; i < pedidoConsulta.getOpcoes().size(); i++) {
			System.out.println(pedidoConsulta.getOpcoes().get(i).getNomeo());
			System.out.println(pedidoConsulta.getOpcoes().get(i).getPreco());
			for (int j = 0; j < pedidoConsulta.getOpcoes().get(i).getIngredientes().size(); j++) {
				System.out.println(pedidoConsulta.getOpcoes().get(i).getIngredientes().get(j).getNomei());
			}
		}
		*/
		//System.out.println(pd.gerarRelatorio(2019));
		//System.out.println(pd.gerarRelatorio());
		
		CardapioDAO cd = new CardapioDAO();
		List<Opcao> listCardapio = cd.mostrarOpcoes();
		OpcaoDAO od = new OpcaoDAO();
		Opcao teste = od.consultar(listCardapio.get(2).getNomeo());
		System.out.println(teste.getNomeo());
		
		if(teste.getImagem() != null)
			System.out.println("aq");
		else
			System.out.println("n");
		
	}
}