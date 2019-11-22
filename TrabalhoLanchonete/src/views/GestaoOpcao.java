package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import database.dao.OpcaoDAO;
import database.models.Opcao;

import javax.swing.JScrollPane;

public class GestaoOpcao extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnImagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestaoOpcao frame = new GestaoOpcao();
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
	@SuppressWarnings("serial")
	public GestaoOpcao() {
		OpcaoDAO od = new OpcaoDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestoDeOpcoes = new JLabel("Gest\u00E3o de Op\u00E7\u00F5es");
		lblGestoDeOpcoes.setFont(new Font("Arial", Font.BOLD, 18));
		lblGestoDeOpcoes.setBounds(109, 18, 222, 28);
		contentPane.add(lblGestoDeOpcoes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 99, 252, 205);
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
		preencher(od, model);
		
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
		
		JTextField textPesquisa = new JTextField();
		textPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				TableRowSorter<DefaultTableModel> filtro = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(filtro);
				filtro.setRowFilter(RowFilter.regexFilter(textPesquisa.getText()));
			}
		});
		textPesquisa.setBounds(30, 68, 168, 20);
		contentPane.add(textPesquisa);
		textPesquisa.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() >= 0){
					if(od.remover((String) table.getValueAt(table.getSelectedRow(), 0))) {
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
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
		btnExcluir.setBounds(327, 258, 89, 28);
		contentPane.add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() >= 0) {
					
				}else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditar.setBounds(327, 219, 89, 28);
		contentPane.add(btnEditar);
		
		JButton btnListar = new JButton("Ingredientes");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() >= 0) {
					Opcao opcao = od.consultar((String) table.getValueAt(table.getSelectedRow(), 0));
					new ListaIngredientes(opcao).setVisible(true);
				}else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		btnListar.setBackground(Color.GREEN);
		btnListar.setFont(new Font("Arial", Font.BOLD, 13));
		btnListar.setBounds(305, 180, 120, 28);
		contentPane.add(btnListar);
		
		btnImagem = new JButton("Imagem");
		btnImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		btnImagem.setBackground(Color.GREEN);
		btnImagem.setFont(new Font("Arial", Font.BOLD, 13));
		btnImagem.setBounds(327, 141, 89, 28);
		contentPane.add(btnImagem);
	}

	private void preencher(OpcaoDAO od, DefaultTableModel model) {
		// TODO Auto-generated method stub
		List<Opcao> opcoes = od.consultar();
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
