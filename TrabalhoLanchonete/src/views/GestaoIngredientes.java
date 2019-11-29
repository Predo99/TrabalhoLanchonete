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

import database.dao.IngredienteDAO;
import database.models.Funcionario;
import database.models.Ingrediente;

public class GestaoIngredientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Pesquisa;
	private JTable table;


	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestaoIngredientes frame = new GestaoIngredientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param funcionario 
	 */
	public GestaoIngredientes(Funcionario funcionario) {
		setTitle("Gestão de Ingredientes");
		setResizable(false);
		IngredienteDAO id = new IngredienteDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestoDeIngredientes = new JLabel("Gest\u00E3o de Ingredientes");
		lblGestoDeIngredientes.setFont(new Font("Arial", Font.BOLD, 24));
		lblGestoDeIngredientes.setBounds(70, 11, 283, 29);
		contentPane.add(lblGestoDeIngredientes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 131, 275, 245);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nome", "Custo", "Quantidade"
			}
		) {
	
			private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
			 }
		});
		DefaultTableModel model =  (DefaultTableModel) table.getModel();
		preencher(id, model);
		for (int i = 0; i < table.getColumnCount(); i++) {
		      DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
		      TableColumn col = colModel.getColumn(i);
		      int width = 0;
	
		      TableCellRenderer renderer = col.getHeaderRenderer();
		      for (int r = 0; r < table.getRowCount(); r++) {
		        renderer = table.getCellRenderer(r, i);
		        Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, i),
		            false, false, r, i);
		        width = Math.max(width, comp.getPreferredSize().width);
		      }
		      col.setPreferredWidth(width + 2);
		}
		

		textField_Pesquisa = new JTextField();
		textField_Pesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(filtro);
				filtro.setRowFilter(RowFilter.regexFilter(textField_Pesquisa.getText()));
			}
		});
		textField_Pesquisa.setBounds(42, 87, 148, 20);
		contentPane.add(textField_Pesquisa);
		textField_Pesquisa.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAdicionar.setBackground(Color.GREEN);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CadastrarIngrediente(funcionario).setVisible(true);
				dispose();
			}
		});
		btnAdicionar.setBounds(327, 203, 100, 23);
		contentPane.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() >= 0) {
					Ingrediente aux = id.consultar((String) table.getValueAt(table.getSelectedRow(), 0));
					new CadastrarIngrediente(funcionario,aux).setVisible(true);
					dispose();
				}else
					JOptionPane.showMessageDialog(null, "Selecione um Ingrediente na lista para realizar a operação.");
			}
		});
		btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setBounds(327, 253, 100, 23);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >= 0) {
					if(id.remover((String) table.getValueAt(table.getSelectedRow(), 0))) {
						JOptionPane.showMessageDialog(null, "Ingrediente excluído.");
						model.removeRow(table.getSelectedRow());
					}	
					else
						JOptionPane.showMessageDialog(null, "Erro ao excluir Ingrediente.");
				}else
					JOptionPane.showMessageDialog(null, "Selecione um ingrediente na lista para realizar a operação.");
			}
		});
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setBounds(327, 302, 100, 23);
		contentPane.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Menu(funcionario).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBounds(327, 349, 100, 27);
		contentPane.add(btnVoltar);
	}
	private void preencher(IngredienteDAO id, DefaultTableModel model) {
		// TODO Auto-generated method stub
		List<Ingrediente> ingredientes = id.consultar();
		Object rowData[] = new Object[3];
		for(int i = 0; i < ingredientes.size(); i++) {
			rowData[0] = ingredientes.get(i).getNomei();
			rowData[1] = ingredientes.get(i).getCusto();
			rowData[2] = ingredientes.get(i).getQuantidade();
			model.addRow(rowData);
		}
	}
}
