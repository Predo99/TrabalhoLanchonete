package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import database.models.Ingrediente;
import database.models.Opcao;

public class ListaIngredientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaIngredientes frame = new ListaIngredientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param opcao2 
	 */
	@SuppressWarnings("serial")
	public ListaIngredientes(Opcao opcao) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setFont(new Font("Arial", Font.BOLD, 15));
		lblIngredientes.setBounds(115, 5, 158, 25);
		contentPane.add(lblIngredientes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 314, 133);
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
			 @Override
			 public boolean isCellEditable(int row, int column) {
		      //all cells false
		       return false;
			 }
		});
		
		DefaultTableModel model =  (DefaultTableModel) table.getModel();
		List<Ingrediente> ingredientes = opcao.getIngredientes();
		preencher(ingredientes, model);
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
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setBackground(Color.RED);
		btnFechar.setFont(new Font("Arial", Font.BOLD, 13));
		btnFechar.setBounds(234, 174, 90, 25);
		contentPane.add(btnFechar);
	}

	private void preencher(List<Ingrediente> ingredientes, DefaultTableModel model) {
		// TODO Auto-generated method stub
		Object rowData[] = new Object[3];
		for(int i = 0; i < ingredientes.size(); i++) {
			rowData[0] = ingredientes.get(i).getNomei();
			rowData[1] = ingredientes.get(i).getCusto();
			rowData[2] = ingredientes.get(i).getQuantidade();
			model.addRow(rowData);
		}
	}
}
