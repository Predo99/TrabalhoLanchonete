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
import database.models.Opcao;

public class Pagamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private OpcaoDAO od;
	List<String> listEmbalagem;
	List<String> listPagamento;
	JComboBox<String> boxEmbalagem;
	JComboBox<String> boxPagamento;
	String respBoxPagamento;
	String respBoxEmbalagem;
	private JTable table;

	public Pagamento(double preco) {
		od = new OpcaoDAO();
		setTitle("Pagamento");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane verificarIngredientes = new JScrollPane();
		verificarIngredientes.setBounds(286, 77, 252, 205);
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
		preencher(od, model);

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
					Opcao aux = od.consultar((String) table.getValueAt(table.getSelectedRow(), 0));
					new AlterarIngredientesFinais(aux).setVisible(true);
				} else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditar.setBounds(156, 166, 120, 28);
		contentPane.add(btnEditar);

		boxEmbalagem = new JComboBox<String>();
		boxEmbalagem.setBackground(Color.GRAY);
		listEmbalagem = new ArrayList<String>();
		listEmbalagem.add("Viajem");
		listEmbalagem.add("Local");
		boxEmbalagem.setBounds(152, 80, 120, 22);
		contentPane.add(boxEmbalagem);
		boxEmbalagem.addItem(listEmbalagem.get(0));
		boxEmbalagem.addItem(listEmbalagem.get(1));

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
		boxPagamento.setBounds(152, 126, 120, 22);
		contentPane.add(boxPagamento);

		JLabel lblValorTotal = new JLabel("Valor total:");
		lblValorTotal.setFont(new Font("Arial", Font.BOLD, 17));
		lblValorTotal.setBounds(221, 357, 92, 14);
		contentPane.add(lblValorTotal);

		JLabel labelPreco = new JLabel("R$ " + preco);
		labelPreco.setFont(new Font("Arial", Font.BOLD, 18));
		labelPreco.setBounds(321, 357, 72, 14);
		contentPane.add(labelPreco);

		boxPagamento.addItem(listPagamento.get(0));
		boxPagamento.addItem(listPagamento.get(1));
		boxPagamento.addItem(listPagamento.get(2));

		respBoxEmbalagem = boxEmbalagem.getSelectedItem().toString();
		respBoxPagamento = boxPagamento.getSelectedItem().toString();

		JButton btnPagamento = new JButton("Efetuar pagamento");
		btnPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Pedidio Finalizado, valor R$ " + preco + " Embalagem: "
						+ respBoxEmbalagem + " ,Forma de Pagamento: " + respBoxPagamento);
			}
		});
		btnPagamento.setBackground(Color.GREEN);
		btnPagamento.setFont(new Font("Arial", Font.BOLD, 14));
		btnPagamento.setBounds(215, 303, 178, 34);
		contentPane.add(btnPagamento);
	}

	private void preencher(OpcaoDAO od, DefaultTableModel model) {
		// TODO Auto-generated method stub
		List<Opcao> opcoes = od.consultar();
		Object rowData[] = new Object[2];
		for (int i = 0; i < opcoes.size(); i++) {
			rowData[0] = opcoes.get(i).getNomeo();
			rowData[1] = opcoes.get(i).getPreco();
			model.addRow(rowData);
		}
	}
}
