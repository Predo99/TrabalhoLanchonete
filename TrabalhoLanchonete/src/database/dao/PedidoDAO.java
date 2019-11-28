package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.Conexao;
import database.models.Ingrediente;
import database.models.Opcao;
import database.models.Pedido;

public class PedidoDAO {

	private Connection connection;

	public PedidoDAO() {
		this.connection = Conexao.getConexao();
	}

	public boolean cadastrar(Pedido pedido) {
		try {
			String sql = "insert into pedido (embalagem, preco, formaPagamento, dataPedido, numCard) values (?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, pedido.getEmbalagem());
			stmt.setDouble(2, pedido.getPrecoBanco());
			stmt.setString(3, pedido.getFormaPag());
			stmt.setDate(4, pedido.getDataB());
			if(pedido.getNumCard() != null)
				stmt.setString(5, pedido.getNumCard());
			else
				stmt.setNull(5, java.sql.Types.VARCHAR);
			stmt.execute();
			stmt.close();

			sql= "select max(codigop) from pedido";
			PreparedStatement stmt2 = connection.prepareStatement(sql);
			ResultSet rs1 = stmt2.executeQuery();
			while (rs1.next()) {
				pedido.setCodigop(rs1.getInt("max(codigop)"));
			}
			stmt2.execute();
			stmt2.close();

			if(inserirOpcoes(pedido)) {
				IngredienteDAO id = new IngredienteDAO();
				List<Ingrediente> ingredientes = new ArrayList();

				for(int i = 0; i < pedido.getOpcoes().size(); i++) {
					for (int j = 0; j < pedido.getOpcoes().get(i).getIngredientes().size(); j++) {
						Ingrediente ingrediente = id.consultar(pedido.getOpcoes().get(i).getIngredientes().get(j).getNomei());
						if(ingrediente.getQuantidade() != 0) {
							ingredientes.add(ingrediente);
						}
						else {
							deletarPedido(pedido);
							JOptionPane.showMessageDialog(null, "Ingredientes em falta para a opção " 
									+ pedido.getOpcoes().get(i).getNomeo());
							return false;
						}
					}
				}

				for(int i = 0; i < ingredientes.size(); i++)
					id.atualizar(ingredientes.get(i).getNomei(), "quantidade", ingredientes.get(i).getQuantidade()-1);

				return true;
			}	
			else {
				deletarPedido(pedido);
				return false;
			}		
		} catch (SQLException e) {
			return false;
		}
	}

	public Pedido consultar(int codigop) {
		Pedido p = null;
		try {
			String sql = "select * from pedido where codigop = ?"; 		
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, codigop);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				p = new Pedido();
				p.setCodigop(rs.getInt("codigop"));
				p.setEmbalagem(rs.getString("embalagem"));
				p.setPreco(rs.getDouble("preco"));
				p.setFormaPag(rs.getString("formaPagamento"));
				p.setData(rs.getDate("dataPedido"));
				p.setNumCard(rs.getString("numCard"));
				p.setOpcoes(new ArrayList());

				String sql2 = "select * from opcoes_pedido where codigop = ?";
				PreparedStatement stmt2 = connection.prepareStatement(sql2);
				stmt2.setInt(1, codigop);
				ResultSet rs2 = stmt2.executeQuery();
				OpcaoDAO od = new OpcaoDAO();
				while(rs2.next()) {
					Opcao opcao = od.consultar(rs2.getString("nomeo"));
					p.getOpcoes().add(opcao);
				}
				rs2.close();
				stmt2.close();

				sql2 = "select * from complementos where codigop = ?";
				stmt2 = connection.prepareStatement(sql2);
				stmt2.setInt(1, codigop);
				rs2 = stmt2.executeQuery();
				IngredienteDAO id = new IngredienteDAO();
				while(rs2.next()) {
					Opcao opcao = od.consultar(rs2.getString("nomeo"));
					Ingrediente ingrediente = id.consultar(rs2.getString("nomei"));
					p.getOpcoes().get(rs2.getInt("indice")).getIngredientes().add(ingrediente);
				}
				rs2.close();
				stmt2.close();

				sql2 = "select * from remocoes where codigop = ?";
				stmt2 = connection.prepareStatement(sql2);
				stmt2.setInt(1, codigop);
				rs2 = stmt2.executeQuery();
				id = new IngredienteDAO();
				while(rs2.next()) {
					Opcao opcao = od.consultar(rs2.getString("nomeo"));
					Ingrediente ingrediente = id.consultar(rs2.getString("nomei"));
					p.getOpcoes().get(rs2.getInt("indice")).getIngredientes().remove(ingrediente);
				}
				rs2.close();
				stmt2.close();

			}
			rs.close();
			stmt.close();
			return p;
		}catch (SQLException e) {
			e.printStackTrace();
			return p;
		}
	}

	private boolean inserirOpcoes (Pedido pedido) {
		try {
			String sql = "insert into opcoes_pedido (codigop, nomeo) values (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			for (int i = 0; i < pedido.getOpcoes().size(); i++) {
				stmt.setInt(1, pedido.getCodigop());
				stmt.setString(2, pedido.getOpcoes().get(i).getNomeo());
				stmt.execute();
			}
			stmt.close();

			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	private boolean deletarPedido (Pedido pedido) {
		try {
			String sql = "delete from pedido where codigop = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, pedido.getCodigop());
			stmt.execute();
			stmt.close();	
			return true;
		}catch (SQLException e) {
			return false;
		}
	}

	public boolean cadastrarComplementos (Pedido pedido, int indice, List<Ingrediente> complementos) {
		try {
			IngredienteDAO id = new IngredienteDAO();
			List<Ingrediente> ingredientes = new ArrayList();

			for (int i = 0; i < complementos.size(); i++) {
				Ingrediente ingrediente = id.consultar(complementos.get(i).getNomei());
				if(ingrediente.getQuantidade() != 0) {
					ingredientes.add(ingrediente);
				}
				else {
					JOptionPane.showMessageDialog(null, "Ingrediente " + complementos.get(i).getNomei() 
							+  "em falta para a opção ");
					return false;
				}
			}
			String sql = "insert into complementos (indice, codigop, nomeo, nomei) values (?,?,?,?)";;
			PreparedStatement stmt = connection.prepareStatement(sql);
			for(int i = 0; i < complementos.size(); i++)
			{
				stmt.setInt(1, indice);
				stmt.setInt(2, pedido.getCodigop());
				stmt.setString(3, pedido.getOpcoes().get(indice).getNomeo());
				stmt.setString(4, complementos.get(i).getNomei());

				stmt.execute();
			}

			stmt.close();

			pedido.addComplementos(pedido.getOpcoes().get(indice), complementos);
			sql = "update pedido set preco = ? where codigop = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, pedido.getPreco());
			stmt.setInt(2, pedido.getCodigop());
			stmt.execute();
			stmt.close();

			for(int i = 0; i < ingredientes.size(); i++)
				id.atualizar(ingredientes.get(i).getNomei(), "quantidade", ingredientes.get(i).getQuantidade()-1);

			return true;
		}catch (SQLException e) {
			return false;
		}
	}

	public boolean cadastrarRemocoes (Pedido pedido, int indice, List<Ingrediente> remocoes) {
		try {
			String sql = "insert into remocoes (indice, codigop, nomeo, nomei) values (?,?,?,?)";;
			PreparedStatement stmt = connection.prepareStatement(sql);
			for(int i = 0; i < remocoes.size(); i++)
			{
				if(pedido.getOpcoes().get(indice).getIngredientes().contains(remocoes.get(i))) {
					stmt.setInt(1, indice);
					stmt.setInt(2, pedido.getCodigop());
					stmt.setString(3, pedido.getOpcoes().get(indice).getNomeo());
					stmt.setString(4, remocoes.get(i).getNomei());

					stmt.execute();
				}
			}

			stmt.close();

			pedido.removerIngredientes(pedido.getOpcoes().get(indice), remocoes);
			sql = "update pedido set preco = ? where codigop = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, pedido.getPreco());
			stmt.setInt(2, pedido.getCodigop());
			stmt.execute();
			stmt.close();

			IngredienteDAO id = new IngredienteDAO();

			for (int i = 0; i < remocoes.size(); i++) {
				Ingrediente ingrediente = id.consultar(remocoes.get(i).getNomei());
				id.atualizar(ingrediente.getNomei(), "quantidade", ingrediente.getQuantidade()+1);
			}

			return true;
		}catch (SQLException e) {
			return false;
		}
	}

	public String gerarRelatorio (int ano) {
		String relatorio = "";
		try {
			String sql = "select month(dataPedido) as mes, count(codigop) as num, sum(preco) as total from pedido where year(dataPedido) = ? "
					+ "group by month (dataPedido) order by mes";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, ano);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				relatorio += "Mês: " + rs.getString("mes") + "\nNúmero de vendas: " + rs.getString("num") 
				+ "\nTotal: R$ " + String.format("%.2f",rs.getDouble("total")) + "\n";
				
				String sql2 = "select embalagem, count(codigop) as num from pedido where month(dataPedido) = ? "
						+ "group by embalagem";
				PreparedStatement stmt2 = connection.prepareStatement(sql2);
				stmt2.setInt(1, rs.getInt("mes"));
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					relatorio += "Número de vendas na embalagem " + rs2.getString("embalagem") + ": " + rs2.getString("num")+ "\n";
				}
				stmt2.execute();
				stmt2.close();
				
				sql2 = "select formapagamento, count(codigop) as num from pedido where month(dataPedido) = ? "
				+ "group by formapagamento";
				stmt2 = connection.prepareStatement(sql2);
				stmt2.setInt(1, rs.getInt("mes"));
				rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					relatorio += "Número de vendas na forma de pagamento " + rs2.getString("formapagamento") + ": " + rs2.getString("num")+ "\n";
				}
				stmt2.execute();
				stmt2.close();
				
			}
			stmt.execute();
			stmt.close();

			if(relatorio.length() == 0)
				relatorio = "Não houveram pedidos no ano informado.";
			return relatorio;
		}catch (SQLException e) {
			e.printStackTrace();
			relatorio = "Formato inválido";
			return relatorio;
		}
	}
	
	public String gerarRelatorio () {
		String relatorio = "";
		try {
			String sql = "select count(codigop) as num, sum(preco) as total from pedido";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				relatorio += "Número de vendas: " + rs.getString("num") 
				+ "\nTotal: R$ " + String.format("%.2f",rs.getDouble("total")) + "\n";
				
				String sql2 = "select embalagem, count(codigop) as num from pedido group by embalagem";
				PreparedStatement stmt2 = connection.prepareStatement(sql2);
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					relatorio += "Número de vendas na embalagem " + rs2.getString("embalagem") + ": " + rs2.getString("num")+ "\n";
				}
				stmt2.execute();
				stmt2.close();
				
				sql2 = "select formapagamento, count(codigop) as num from pedido group by formapagamento";
				stmt2 = connection.prepareStatement(sql2);
				rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					relatorio += "Número de vendas na forma de pagamento " + rs2.getString("formapagamento") + ": " + rs2.getString("num")+ "\n";
				}
				stmt2.execute();
				stmt2.close();
				
			}
			stmt.execute();
			stmt.close();

			if(relatorio.length() == 0)
				relatorio = "Não houveram pedidos no ano informado.";
			return relatorio;
		}catch (SQLException e) {
			e.printStackTrace();
			relatorio = "Formato inválido";
			return relatorio;
		}
	}

}