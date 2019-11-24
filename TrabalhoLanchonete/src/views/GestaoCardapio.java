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

public class GestaoCardapio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestaoCardapio frame = new GestaoCardapio();
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
	public GestaoCardapio(Funcionario funcionario) {
		setResizable(false);
		CardapioDAO cd = new CardapioDAO();
		OpcaoDAO od = new OpcaoDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestoDeCardpio = new JLabel("Gest\u00E3o do Card\u00E1pio");
		lblGestoDeCardpio.setFont(new Font("Arial", Font.BOLD, 18));
		lblGestoDeCardpio.setBounds(131, 11, 222, 28);
		contentPane.add(lblGestoDeCardpio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 252, 205);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Pre\u00E7o"
			}
		) {
			private static final long serialVersionUID = 1L;

			/*@Override
            public Class<?> getColumnClass(int column) {
                if (column==0) return ImageIcon.class;
                return Object.class;
            }*/
			 @Override
			 public boolean isCellEditable(int row, int column) {
			     //all cells false
				 return false;
			 }
		});
		
		DefaultTableModel model =  (DefaultTableModel) table.getModel();
		preencher(cd, model);
		
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
		textField.setBounds(10, 68, 168, 20);
		contentPane.add(textField);
		
		JButton button = new JButton("Excluir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >= 0){
					if(cd.removeOpcao(((String) table.getValueAt(table.getSelectedRow(), 0)))) {
						JOptionPane.showMessageDialog(null, "Opção excluída.");
						model.removeRow(table.getSelectedRow());
					}	
					else
						JOptionPane.showMessageDialog(null, "Erro ao excluir opção.");
				}
				else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setBackground(Color.RED);
		button.setBounds(285, 236, 120, 28);
		contentPane.add(button);
		
		JButton button_2 = new JButton("Ingredientes");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >= 0) {
					Opcao opcao = od.consultar((String) table.getValueAt(table.getSelectedRow(), 0));
					new ListaIngredientes(opcao).setVisible(true);
				}else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		button_2.setFont(new Font("Arial", Font.BOLD, 13));
		button_2.setBackground(Color.GREEN);
		button_2.setBounds(285, 197, 120, 28);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Imagem");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >= 0) {
					Opcao opcao = od.consultar((String) table.getValueAt(table.getSelectedRow(), 0));
					if(opcao.getImagem() != null)
						new Imagem(opcao).setVisible(true);
					else
						JOptionPane.showMessageDialog(null, "A opção não possui imagem cadastrada");
				}else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		button_3.setFont(new Font("Arial", Font.BOLD, 13));
		button_3.setBackground(Color.GREEN);
		button_3.setBounds(285, 158, 120, 28);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Adicionar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdicionarCardapio(funcionario).setVisible(true);
				dispose();
			}
		});
		button_4.setFont(new Font("Arial", Font.BOLD, 13));
		button_4.setBackground(Color.GREEN);
		button_4.setBounds(285, 121, 120, 26);
		contentPane.add(button_4);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Menu(funcionario).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBounds(285, 274, 120, 27);
		contentPane.add(btnVoltar);
	}
	
	private void preencher(CardapioDAO cd, DefaultTableModel model) {
		// TODO Auto-generated method stub
		List<Opcao> opcoes = cd.mostrarOpcoes();
		Object rowData[] = new Object[2];
		for(int i = 0; i < opcoes.size(); i++) {
			/*if(opcoes.get(i).getImagem() != null) {
				ImageIcon img = new ImageIcon(opcoes.get(i).getImagem());
				Image image = img.getImage();
				Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(newimg); 
				rowData[0] = img;
			}else
				rowData[0] = null;*/
			rowData[0] = opcoes.get(i).getNomeo();
			rowData[1] = opcoes.get(i).getPreco();
			model.addRow(rowData);
		}
	}
}
