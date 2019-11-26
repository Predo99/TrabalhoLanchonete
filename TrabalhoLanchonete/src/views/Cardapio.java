package views;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.dao.CardapioDAO;
import database.models.Opcao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cardapio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table1;
	private CardapioDAO cd;
	private int contador = 0;

	public Cardapio() {
		cd = new CardapioDAO();
		setTitle("Cardápio");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(583, 0, 17, 369);
		contentPane.add(scrollBar);

		JScrollPane painelCardapio = new JScrollPane();
		table1 = new JTable();
		painelCardapio.setViewportView(table1);
		table1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Preço" }) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		DefaultTableModel model = (DefaultTableModel) table1.getModel();
		preencher(cd, model);

		painelCardapio.setBounds(10, 65, 235, 293);
		contentPane.add(painelCardapio);

		JLabel lblSelecioneOPedido = new JLabel("Selecione o Pedido");
		lblSelecioneOPedido.setFont(new Font("Arial", Font.BOLD, 24));
		lblSelecioneOPedido.setBounds(175, 11, 241, 30);
		contentPane.add(lblSelecioneOPedido);

		JButton btnFinalizarPedido = new JButton("Finalizar Pedido");
		btnFinalizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Teste
				JOptionPane.showMessageDialog(null, "Pedido finalizado com sucesso!");
				dispose();
			}
		});
		btnFinalizarPedido.setBounds(385, 328, 194, 30);
		contentPane.add(btnFinalizarPedido);
		btnFinalizarPedido.setBackground(Color.GREEN);
		btnFinalizarPedido.setFont(new Font("Arial", Font.BOLD, 16));

		JScrollPane painelCarrinho = new JScrollPane();
		painelCarrinho.setBounds(385, 65, 194, 252);
		contentPane.add(painelCarrinho);

		JLabel lblCarrinho = new JLabel("Carrinho ");
		lblCarrinho.setBackground(Color.WHITE);
		lblCarrinho.setHorizontalAlignment(SwingConstants.CENTER);
		painelCarrinho.setColumnHeaderView(lblCarrinho);
		lblCarrinho.setFont(new Font("Arial", Font.BOLD, 16));

		JButton btnAdd = new JButton("+\r\n");
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador++;
			}
		});
		btnAdd.setBounds(255, 71, 39, 19);
		contentPane.add(btnAdd);

		JLabel labelContador = new JLabel();
		labelContador.setHorizontalAlignment(SwingConstants.CENTER);
		labelContador.setText("" + contador);
		labelContador.setBounds(295, 72, 46, 14);
		contentPane.add(labelContador);

		JButton button = new JButton("-");
		button.setForeground(Color.WHITE);
		button.setBackground(Color.RED);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contador == 0) {
					JOptionPane.showMessageDialog(null, "Não é possível ter quantidade negativa no carrinho!");
				} else {
					contador--;
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button.setBounds(336, 71, 39, 19);
		contentPane.add(button);
	}

	public void preencher(CardapioDAO cd, DefaultTableModel model) {
		List<Opcao> listCardapio = cd.mostrarOpcoes();
		Object rowData[] = new Object[3];
		for (int i = 0; i < listCardapio.size(); i++) {
			rowData[0] = listCardapio.get(i).getImagem();
			rowData[1] = listCardapio.get(i).getNomeo();
			rowData[2] = listCardapio.get(i).getPreco();
			model.addRow(rowData);
		}
	}
}
