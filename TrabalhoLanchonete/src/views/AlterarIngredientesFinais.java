package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import database.dao.IngredienteDAO;
import database.dao.OpcaoDAO;
import database.models.Ingrediente;
import database.models.Opcao;

public class AlterarIngredientesFinais extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Nome;
	private JTextField textField_Preco;
	private File selectedFile;
	private JTextField textProcurar;
	private String filename = null;
	private DefaultListModel<Ingrediente> listModel;

	public AlterarIngredientesFinais(List<Opcao> opcoes, Opcao aux) {
		setTitle("Alterar Ingredientes Finais");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		IngredienteDAO fd = new IngredienteDAO();

		listModel = new DefaultListModel<>();
		for (int i = 0; i < aux.getIngredientes().size(); i++) {
			listModel.add(i, aux.getIngredientes().get(i));
		}
		contentPane.setLayout(null);

		JLabel lblTeste = new JLabel("Ingredientes:");
		lblTeste.setBounds(56, 91, 94, 20);
		lblTeste.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(lblTeste);

		textProcurar = new JTextField();
		textProcurar.setBounds(160, 92, 109, 20);
		contentPane.add(textProcurar);
		textProcurar.setColumns(10);

		JButton btnProcurar = new JButton("Adicionar ");
		btnProcurar.setBounds(279, 91, 94, 23);
		btnProcurar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ingrediente f = fd.consultar(textProcurar.getText());
				if (f != null) {
					if(f.getQuantidade() != 0) {
						listModel.addElement(f);
						opcoes.get(opcoes.indexOf(aux)).addComplemento(aux, f);
					}else
						JOptionPane.showMessageDialog(null, "Ingrediente em falta.");		
				}else
					JOptionPane.showMessageDialog(null, "Ingrediente n�o cadastrado.");
			}
		});
		contentPane.add(btnProcurar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(99, 231, 109, 28);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				JOptionPane.showMessageDialog(null, "Op��o Atualizada");
				new Pagamento(opcoes).setVisible(true);
				dispose();
				
			}
		});
		btnConfirmar.setBackground(Color.GREEN);
		btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(btnConfirmar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(218, 231, 109, 28);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparDados();
			}
		});
		btnLimpar.setBackground(Color.RED);
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(btnLimpar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 138, 146, 62);
		contentPane.add(scrollPane);

		JList<Ingrediente> list_1 = new JList<Ingrediente>(listModel);
		scrollPane.setViewportView(list_1);
		list_1.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Ingrediente) {
					// Here value will be of the Type 'CD'
					((JLabel) renderer).setText(((Ingrediente) value).getNomei());
				}
				return renderer;
			}
		});
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingrediente f = fd.consultar(textProcurar.getText());
				if (f != null  && aux.getIngredientes().contains(f)) {
					listModel.removeElement(f);
					opcoes.get(opcoes.indexOf(aux)).removerIngrediente(aux, f);
				}else {
					JOptionPane.showMessageDialog(null, "A op��o n�o possui esse ingrediente.");
				}
			}
		});
		btnDeletar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnDeletar.setBounds(383, 91, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Pagamento(opcoes).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setBounds(345, 231, 109, 28);
		contentPane.add(btnVoltar);
		
		JLabel lblDigiteONome = new JLabel("Digite o nome de um ingrediente para adicionar ou remover");
		lblDigiteONome.setFont(new Font("Arial", Font.BOLD, 16));
		lblDigiteONome.setBounds(36, 31, 464, 20);
		contentPane.add(lblDigiteONome);
	}

	public void limparDados() {
		textProcurar.setText("");
		listModel.removeAllElements();
	}
}
