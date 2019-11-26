package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import database.dao.CardapioDAO;
import database.dao.OpcaoDAO;
import database.models.Funcionario;
import database.models.Opcao;

public class AdicionarCardapio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 * 
	 * @param funcionario
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { AdicionarCardapio frame = new
	 * AdicionarCardapio(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 * 
	 * /** Create the frame.
	 */
	public AdicionarCardapio(Funcionario funcionario) {
		setTitle("Adicionar Caardápio");
		setResizable(false);
		CardapioDAO cd = new CardapioDAO();
		OpcaoDAO od = new OpcaoDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdicionarOpo = new JLabel("Adicionar Op\u00E7\u00E3o");
		lblAdicionarOpo.setFont(new Font("Arial", Font.BOLD, 18));
		lblAdicionarOpo.setBounds(129, 28, 222, 28);
		contentPane.add(lblAdicionarOpo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 109, 252, 141);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Pre\u00E7o" }) {

			private static final long serialVersionUID = 1L;

			/*
			 * @Override public Class<?> getColumnClass(int column) { if (column==0) return
			 * ImageIcon.class; return Object.class; }
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
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

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(filtro);
				filtro.setRowFilter(RowFilter.regexFilter(textField.getText()));
			}
		});
		textField.setColumns(10);
		textField.setBounds(35, 78, 168, 20);
		contentPane.add(textField);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Opcao opcao = od.consultar((String) table.getValueAt(table.getSelectedRow(), 0));
					if (cd.addOpcao(opcao)) {
						JOptionPane.showMessageDialog(null, "Cadastrado Com Sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Erro no Cadastro");
					}
					new GestaoCardapio(funcionario).setVisible(true);
					dispose();
				} else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		btnConfirmar.setFont(new Font("Arial", Font.BOLD, 13));
		btnConfirmar.setBackground(Color.GREEN);
		btnConfirmar.setBounds(297, 133, 100, 23);
		contentPane.add(btnConfirmar);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestaoCardapio(funcionario).setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(297, 174, 100, 23);
		contentPane.add(btnNewButton);
	}

	private void preencher(OpcaoDAO od, DefaultTableModel model) {
		// TODO Auto-generated method stub
		List<Opcao> opcoes = od.consultar();
		Object rowData[] = new Object[2];
		for (int i = 0; i < opcoes.size(); i++) {
			/*
			 * if(opcoes.get(i).getImagem() != null) { ImageIcon img = new
			 * ImageIcon(opcoes.get(i).getImagem()); Image image = img.getImage(); Image
			 * newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); img
			 * = new ImageIcon(newimg); rowData[0] = img; }else rowData[0] = null;
			 */
			rowData[0] = opcoes.get(i).getNomeo();
			rowData[1] = opcoes.get(i).getPreco();
			model.addRow(rowData);
		}
	}

}
