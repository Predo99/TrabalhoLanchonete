package views;

import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.dao.FuncionarioDAO;
import database.models.Funcionario;
import database.models.Ingrediente;

public class GestaoFuncionarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textPesquisa;
	private JTable table;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

	/**
	 * Create the frame.
	 * @param funcionario 
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	public GestaoFuncionarios(Funcionario funcionario) {
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
		
		JLabel lblGestoDeFuncionrios = new JLabel("Gest\u00E3o de Funcion\u00E1rios");
		lblGestoDeFuncionrios.setFont(new Font("Arial", Font.BOLD, 18));
		lblGestoDeFuncionrios.setBounds(109, 18, 222, 28);
		contentPane.add(lblGestoDeFuncionrios);
		
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
		) {
			 @Override
			 public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
			 }
		});
		
		
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
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >= 0) {
					Funcionario aux = fd.consultar((String) table.getValueAt(table.getSelectedRow(), 1));
					new CadastrarFuncionario(funcionario,aux).setVisible(true);
				}else
					JOptionPane.showMessageDialog(null, "Selecione um funcionário na lista para realizar a operação.");
			}
		});
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditar.setBounds(327, 224, 102, 28);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() >= 0) {
					if(fd.remover((String) table.getValueAt(table.getSelectedRow(), 1))) {
						JOptionPane.showMessageDialog(null, "Funcionário excluído.");
						model.removeRow(table.getSelectedRow());
					}	
					else
						JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario.");
				}else
					JOptionPane.showMessageDialog(null, "Selecione um funcionário na lista para realizar a operação.");
			}
		});
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
		btnExcluir.setBounds(327, 274, 102, 28);
		contentPane.add(btnExcluir);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastrarFuncionario(funcionario).setVisible(true);
				dispose();
			}
		});
		btnAdicionar.setBackground(Color.GREEN);
		btnAdicionar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAdicionar.setBounds(327, 171, 102, 28);
		contentPane.add(btnAdicionar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Menu(funcionario).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBounds(327, 348, 102, 28);
		contentPane.add(btnVoltar);
		
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
