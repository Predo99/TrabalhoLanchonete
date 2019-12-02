package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import database.dao.OpcaoDAO;
import database.dao.PedidoDAO;
import database.models.Ingrediente;
import database.models.Opcao;
import database.models.Pedido;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Pagamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private OpcaoDAO od;
	private PedidoDAO pd;
	List<String> listEmbalagem;
	List<String> listPagamento;
	JComboBox<String> boxEmbalagem;
	JComboBox<String> boxPagamento;
	String respBoxPagamento;
	String respBoxEmbalagem;
	private JTable table;
	private double preco = 0;
	private JTextField textNumCartao;
	private JLabel lblNumCartao;

	public Pagamento(List<Opcao> opcoes) {
		od = new OpcaoDAO();
		pd = new PedidoDAO();
		
		for(int i = 0; i < opcoes.size(); i++) {
			preco += opcoes.get(i).getPreco();
		}
		
		setTitle("Pagamento");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane verificarIngredientes = new JScrollPane();
		verificarIngredientes.setBounds(309, 77, 252, 205);
		contentPane.add(verificarIngredientes);

		table = new JTable();
		verificarIngredientes.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Pre\u00E7o" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		preencher(opcoes, model);

		for (int i = 0; i < table.getColumnCount(); i++) {
			DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
			TableColumn col = colModel.getColumn(i);
			int width = 0;

			TableCellRenderer renderer = col.getHeaderRenderer();
			for (int r = 0; r < table.getRowCount(); r++) {
				renderer = table.getCellRenderer(r, i);
				Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, i), false, false, r,
						i);
				width = Math.max(width, comp.getPreferredSize().width);
			}
			col.setPreferredWidth(width + 2);
		}

		JLabel lblPagamento = new JLabel("Pagamento");
		lblPagamento.setFont(new Font("Arial", Font.BOLD, 24));
		lblPagamento.setBounds(219, 11, 147, 28);
		contentPane.add(lblPagamento);

		JLabel lblSelecioneAEmbalagem = new JLabel("Embalagem:");
		lblSelecioneAEmbalagem.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSelecioneAEmbalagem.setBounds(69, 79, 87, 23);
		contentPane.add(lblSelecioneAEmbalagem);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() >= 0) {
					Opcao aux = opcoes.get(table.getSelectedRow());
					new AlterarIngredientesFinais(opcoes, aux).setVisible(true);
					dispose();
				} else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditar.setBounds(179, 217, 120, 28);
		contentPane.add(btnEditar);

		boxEmbalagem = new JComboBox<String>();
		boxEmbalagem.setBackground(Color.GRAY);
		listEmbalagem = new ArrayList<String>();
		listEmbalagem.add("Viajem");
		listEmbalagem.add("Local");
		boxEmbalagem.addItem(listEmbalagem.get(0));
		boxEmbalagem.addItem(listEmbalagem.get(1));
		boxEmbalagem.setBounds(152, 80, 147, 22);
		contentPane.add(boxEmbalagem);

		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento:");
		lblFormaDePagamento.setFont(new Font("Arial", Font.PLAIN, 14));
		lblFormaDePagamento.setBounds(10, 125, 147, 23);
		contentPane.add(lblFormaDePagamento);

		boxPagamento = new JComboBox<String>();
		boxPagamento.setBackground(Color.GRAY);
		listPagamento = new ArrayList<String>();
		listPagamento.add("Dinheiro");
		listPagamento.add("Cartão de Débito");
		listPagamento.add("Cartão de Crédito");
		boxPagamento.addItem(listPagamento.get(0));
		boxPagamento.addItem(listPagamento.get(1));
		boxPagamento.addItem(listPagamento.get(2));

		boxPagamento.setBounds(152, 126, 147, 22);
		contentPane.add(boxPagamento);
		

		JLabel lblValorTotal = new JLabel("Valor total:");
		lblValorTotal.setFont(new Font("Arial", Font.BOLD, 17));
		lblValorTotal.setBounds(221, 357, 92, 14);
		contentPane.add(lblValorTotal);

		JLabel labelPreco = new JLabel("R$ " + String.format("%.2f", preco));
		labelPreco.setFont(new Font("Arial", Font.BOLD, 18));
		labelPreco.setBounds(321, 357, 131, 14);
		contentPane.add(labelPreco);
				
		JButton btnPagamento = new JButton("Efetuar pagamento");
		btnPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				respBoxEmbalagem = boxEmbalagem.getSelectedItem().toString();
				respBoxPagamento = boxPagamento.getSelectedItem().toString();
				
				Pedido pedido = new Pedido();
				pedido.setEmbalagem(respBoxEmbalagem);
				pedido.setFormaPag(respBoxPagamento);
				pedido.setData(pedido.getDataB());
				pedido.setOpcoes(opcoes);
				pedido.setPreco(pedido.getPrecoBanco());
				
				if(!respBoxPagamento.equals("Dinheiro") && textNumCartao.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Digite o número do cartão para continuar.");
				else if(!respBoxPagamento.equals("Dinheiro") && textNumCartao.getText().length() != 16)
					JOptionPane.showMessageDialog(null, "Digite um número de cartão válido (16 dígitos).");
				else {
					if(respBoxPagamento.equals("Dinheiro"))
						pedido.setNumCard(null);
					else
						pedido.setNumCard(textNumCartao.getText());
					pd.cadastrar(pedido);	
					
					for(int i = 0; i < model.getRowCount(); i++) {
						Opcao o = opcoes.get(i);
						comparar(pedido, o);
						new Finalizacao(pedido).setVisible(true);
						dispose();
					}		
				}	
				/*
				*/
			}
		});
		btnPagamento.setBackground(Color.GREEN);
		btnPagamento.setFont(new Font("Arial", Font.BOLD, 14));
		btnPagamento.setBounds(121, 299, 178, 34);
		contentPane.add(btnPagamento);	
		
		lblNumCartao = new JLabel("N\u00FAmero do cart\u00E3o:");
		lblNumCartao.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNumCartao.setBounds(28, 174, 117, 14);
		contentPane.add(lblNumCartao);

		textNumCartao = new JTextField();
		textNumCartao.setFont(new Font("Arial", Font.PLAIN, 14));
		textNumCartao.setBounds(152, 172, 147, 20);
		contentPane.add(textNumCartao);
		textNumCartao.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cardapio().setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBounds(458, 299, 103, 34);
		contentPane.add(btnCancelar);

	}

	private void preencher(List<Opcao> opcoes, DefaultTableModel model) {
		// TODO Auto-generated method stub
		Object rowData[] = new Object[2];
		for (int i = 0; i < opcoes.size(); i++) {
			rowData[0] = opcoes.get(i).getNomeo();
			rowData[1] = String.format("%.2f", opcoes.get(i).getPreco());
			model.addRow(rowData);
		}
	}
	
	private void comparar (Pedido pedido, Opcao o) {
		Opcao original = od.consultar(o.getNomeo());
		List<Ingrediente> complementos = new ArrayList();
		List<Ingrediente> remocoes = new ArrayList();
		
		for(int i = 0; i < o.getIngredientes().size(); i++) {
			if(!original.getIngredientes().contains(o.getIngredientes().get(i))) {
				complementos.add(o.getIngredientes().get(i));
			}
		}
		
		for(int i = 0; i < original.getIngredientes().size(); i++) {
			if(!o.getIngredientes().contains(original.getIngredientes().get(i))) {
				System.out.println(original.getIngredientes().get(i).getNomei());
				remocoes.add(original.getIngredientes().get(i));
			}
		}
		if(!complementos.isEmpty())
			pd.cadastrarComplementos(pedido, pedido.getOpcoes().indexOf(o), complementos);
		if(!remocoes.isEmpty())
			pd.cadastrarRemocoes(pedido, pedido.getOpcoes().indexOf(o), remocoes);
	}
}
