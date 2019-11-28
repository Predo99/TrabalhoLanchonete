package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import database.dao.CardapioDAO;
import database.dao.OpcaoDAO;
import database.models.Opcao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cardapio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table1;
	private CardapioDAO cd;
	private OpcaoDAO od;
	private int contador = 0;
	private JTable table;
	double preco = 0;
	private ImageIcon img;
	private Image image;
	private Image newimg;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cardapio frame = new Cardapio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Cardapio() {
		cd = new CardapioDAO();
		od = new OpcaoDAO();
		setTitle("Cardápio");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(615, 22, 17, 369);
		contentPane.add(scrollBar);

		JScrollPane painelCardapio = new JScrollPane();
		table1 = new JTable();
		painelCardapio.setViewportView(table1);
		table1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Imagem", "Nome", "Preço" }) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
			
			@Override
            public Class<?> getColumnClass(int column) {
                if (column==0) return ImageIcon.class;
                return Object.class;
            }
		});

		DefaultTableModel model = (DefaultTableModel) table1.getModel();
		preencher(cd, model);
		
		for (int i = 0; i < table1.getColumnCount(); i++) {
		      DefaultTableColumnModel colModel = (DefaultTableColumnModel) table1.getColumnModel();
		      TableColumn col = colModel.getColumn(i);
		      int width = 0;
	
		      TableCellRenderer renderer = col.getHeaderRenderer();
		      for (int r = 0; r < table1.getRowCount(); r++) {
		        renderer = table1.getCellRenderer(r, i);
		        Component comp = renderer.getTableCellRendererComponent(table1, table1.getValueAt(r, i),
		            false, false, r, i);
		        width = Math.max(width, comp.getPreferredSize().width);
		      }
		      col.setPreferredWidth(width + 2);
		}
		
		for (int row = 0; row < table1.getRowCount(); row++)
	    {
	        int rowHeight = table1.getRowHeight();

	        for (int column = 0; column < table1.getColumnCount(); column++)
	        {
	            Component comp = table1.prepareRenderer(table1.getCellRenderer(row, column), row, column);
	            rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
	        }

	        table1.setRowHeight(row, rowHeight);
	    }

		painelCardapio.setBounds(10, 65, 357, 293);
		contentPane.add(painelCardapio);

		JLabel lblSelecioneOPedido = new JLabel("Selecione o Pedido");
		lblSelecioneOPedido.setFont(new Font("Arial", Font.BOLD, 24));
		lblSelecioneOPedido.setBounds(175, 11, 241, 30);
		contentPane.add(lblSelecioneOPedido);

		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Teste
				JOptionPane.showMessageDialog(null, "Pedido finalizado com sucesso!");
				dispose();
			}
		});
		btnContinuar.setBounds(427, 328, 194, 30);
		contentPane.add(btnContinuar);
		btnContinuar.setBackground(Color.GREEN);
		btnContinuar.setFont(new Font("Arial", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(427, 102, 178, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome"
			}
		)	{
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		DefaultTableModel model1 = (DefaultTableModel) table.getModel();
		
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

		JLabel labelPreco = new JLabel("R$ " + preco);
		labelPreco.setHorizontalAlignment(SwingConstants.CENTER);
		labelPreco.setFont(new Font("Arial", Font.BOLD, 16));
		labelPreco.setBackground(Color.WHITE);
		labelPreco.setBounds(486, 299, 119, 18);
		contentPane.add(labelPreco);
		
		JButton btnAdd = new JButton("");
		btnAdd.setBounds(377, 71, 39, 38);
		img = new ImageIcon(getClass().getResource("/add.png"));
		image = img.getImage();
		newimg = image.getScaledInstance(btnAdd.getWidth(), btnAdd.getHeight(),  java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg); 
		btnAdd.setIcon(img);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table1.getSelectedRow() >= 0) {		
					Opcao opcao = od.consultar((String) table1.getValueAt(table1.getSelectedRow(), 1));
					Object rowData[] = new Object[1];
					rowData[0] = opcao.getNomeo();
					model1.addRow(rowData);
					preco += opcao.getPreco();				
					labelPreco.setText("R$ " + preco);
				}else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		contentPane.add(btnAdd);

		JButton button = new JButton("");
		button.setBounds(377, 120, 39, 38);
		img = new ImageIcon(getClass().getResource("/remove.png"));
		image = img.getImage();
		newimg = image.getScaledInstance(button.getWidth(), button.getHeight(),  java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg); 
		button.setIcon(img);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >= 0) {		
					Opcao o = od.consultar((String) table.getValueAt(table.getSelectedRow(), 0));
					model1.removeRow(table.getSelectedRow());
					preco -= o.getPreco();				
					labelPreco.setText("R$ " + preco);
				}else
					JOptionPane.showMessageDialog(null, "Selecione uma opção na lista para realizar a operação.");
			}
		});
		
		contentPane.add(button);
		
		JLabel lblCarrinho = new JLabel("");
		lblCarrinho.setBounds(472, 30, 94, 61);
		contentPane.add(lblCarrinho);
		lblCarrinho.setHorizontalAlignment(SwingConstants.CENTER);
		
		img = new ImageIcon(getClass().getResource("/carrinho.png"));
		image = img.getImage();
		newimg = image.getScaledInstance(lblCarrinho.getWidth(), lblCarrinho.getHeight(),  java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg); 
		lblCarrinho.setIcon(img);
		
		JLabel label2 = new JLabel("Total");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Arial", Font.BOLD, 16));
		label2.setBackground(Color.WHITE);
		label2.setBounds(427, 299, 49, 18);
		contentPane.add(label2);
				
	}

	public void preencher(CardapioDAO cd, DefaultTableModel model) {
		List<Opcao> listCardapio = cd.mostrarOpcoes();
		Object rowData[] = new Object[3];
		for (int i = 0; i < listCardapio.size(); i++) {
			if(listCardapio.get(i).getImagem() != null) {
				img = new ImageIcon(listCardapio.get(i).getImagem());
				image = img.getImage();
				newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(newimg); 
				rowData[0] = img;
			}else
				rowData[0] = null;
			
			rowData[1] = listCardapio.get(i).getNomeo();
			rowData[2] = listCardapio.get(i).getPreco();
			model.addRow(rowData);
		}
	}
}
