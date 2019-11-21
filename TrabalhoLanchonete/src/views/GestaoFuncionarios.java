package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;

import database.dao.FuncionarioDAO;
import database.models.Funcionario;
import database.models.Ingrediente;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GestaoFuncionarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textPesquisa;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestaoFuncionarios frame = new GestaoFuncionarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public GestaoFuncionarios() {
		FuncionarioDAO fd = new FuncionarioDAO();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 442);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 2);
		contentPane.add(scrollPane);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditar.setBounds(327, 182, 89, 28);
		contentPane.add(btnEditar);
		
		JLabel lblGestoDeFuncionrios = new JLabel("Gest\u00E3o de Funcion\u00E1rios");
		lblGestoDeFuncionrios.setFont(new Font("Arial", Font.BOLD, 18));
		lblGestoDeFuncionrios.setBounds(109, 18, 222, 28);
		contentPane.add(lblGestoDeFuncionrios);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnPesquisar.setBounds(210, 86, 107, 20);
		contentPane.add(btnPesquisar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(42, 131, 275, 245);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "CPF", "Sal\u00E1rio"
			}
		));
		
		
		DefaultTableModel model =  (DefaultTableModel) table.getModel();
		preencher(fd, model);
		
		textPesquisa = new JTextField();
		textPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(filtro);
				filtro.setRowFilter(RowFilter.regexFilter(textPesquisa.getText()));
			}
		});
		textPesquisa.setBounds(42, 86, 168, 20);
		contentPane.add(textPesquisa);
		textPesquisa.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fd.remover((String) table.getValueAt(table.getSelectedRow(), 1))) {
					JOptionPane.showMessageDialog(null, "Funcionário excluído.");
					model.removeRow(table.getSelectedRow());
				}	
				else
					JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario.");
			}
		});
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
		btnExcluir.setBounds(327, 274, 89, 28);
		contentPane.add(btnExcluir);
		
	}
	
	public void preencher(FuncionarioDAO fd, DefaultTableModel model) {
		List<Funcionario> listFuncionario =  fd.consultar();
		
		Object rowData[] = new Object[3];
		for(int i = 0; i < listFuncionario.size(); i++) {
			rowData[0] = listFuncionario.get(i).getNomef();
			rowData[1] = listFuncionario.get(i).getCpf();
			rowData[2] = listFuncionario.get(i).getSalario();
			model.addRow(rowData);
		}
	}
	
}
