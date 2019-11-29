package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.dao.IngredienteDAO;
import database.models.Funcionario;
import database.models.Ingrediente;

public class CadastrarIngrediente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Nome;
	private JTextField textField_Custo;
	private JTextField textField_Quantidade;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CadastrarIngrediente frame = new
	 * CadastrarIngrediente(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the frame.
	 * 
	 * @wbp.parser.constructor
	 */
	public CadastrarIngrediente(Funcionario funcionario) {
		setTitle("Cadastrar Ingredientes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 421);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastrarIngrediente = new JLabel("Cadastrar Ingrediente");
		lblCadastrarIngrediente.setFont(new Font("Arial", Font.BOLD, 24));
		lblCadastrarIngrediente.setBounds(258, 11, 294, 42);
		contentPane.add(lblCadastrarIngrediente);

		textField_Nome = new JTextField();
		textField_Nome.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_Nome.setBounds(314, 99, 155, 20);
		contentPane.add(textField_Nome);
		textField_Nome.setColumns(10);

		textField_Custo = new JTextField();
		textField_Custo.setBounds(314, 142, 155, 20);
		contentPane.add(textField_Custo);
		textField_Custo.setColumns(10);

		textField_Quantidade = new JTextField();
		textField_Quantidade.setBounds(314, 181, 155, 20);
		contentPane.add(textField_Quantidade);
		textField_Quantidade.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		lblNome.setBounds(258, 101, 46, 14);
		contentPane.add(lblNome);

		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setFont(new Font("Arial", Font.BOLD, 14));
		lblCusto.setBounds(258, 144, 46, 14);
		contentPane.add(lblCusto);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Arial", Font.BOLD, 14));
		lblQuantidade.setBounds(219, 183, 85, 14);
		contentPane.add(lblQuantidade);

		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void limparDados() {
				textField_Custo.setText("");
				textField_Nome.setText("");
				textField_Quantidade.setText("");
			}

			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_Custo.getText().equals("") || textField_Nome.getText().equals("")
							|| textField_Quantidade.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Alguns campos estão vazios!");
					} else {
						String nome = textField_Nome.getText();
						double custo = Double.parseDouble(textField_Custo.getText());
						double quantidade = Double.parseDouble(textField_Quantidade.getText());

						Ingrediente ingrediente = new Ingrediente(nome, quantidade, custo);
						IngredienteDAO id = new IngredienteDAO();

						if (custo <= 0 || quantidade <= 0) {
							JOptionPane.showMessageDialog(null, "Campo custo e quantidade precisa ser maior que 0!");
						} else {

							if (id.cadastrar(ingrediente)) {
								JOptionPane.showMessageDialog(null, "Ingrediente Cadastrado");
								limparDados();
								new GestaoIngredientes(funcionario).setVisible(true);
								dispose();
							} else
								JOptionPane.showMessageDialog(null, "Ingrediente já cadastrado ou dados inválidos");
						}
					}
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Dados inválidos");
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(229, 270, 109, 28);
		contentPane.add(btnNewButton);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_Custo.setText("");
				textField_Nome.setText("");
				textField_Quantidade.setText("");
			}
		});
		btnLimpar.setBackground(Color.RED);
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpar.setBounds(348, 270, 109, 28);
		contentPane.add(btnLimpar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestaoIngredientes(funcionario).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBounds(467, 270, 89, 27);
		contentPane.add(btnVoltar);
	}

	public CadastrarIngrediente(Funcionario funcionario, Ingrediente aux) {
		// TODO Auto-generated constructor stub
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 421);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastrarIngrediente = new JLabel("Cadastrar Ingrediente");
		lblCadastrarIngrediente.setFont(new Font("Arial", Font.BOLD, 24));
		lblCadastrarIngrediente.setBounds(258, 11, 294, 42);
		contentPane.add(lblCadastrarIngrediente);

		textField_Nome = new JTextField();
		textField_Nome.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_Nome.setBounds(314, 99, 155, 20);
		textField_Nome.setText(aux.getNomei());
		contentPane.add(textField_Nome);
		textField_Nome.setColumns(10);

		textField_Custo = new JTextField();
		textField_Custo.setBounds(314, 142, 155, 20);
		textField_Custo.setText(String.valueOf(aux.getCusto()));
		contentPane.add(textField_Custo);
		textField_Custo.setColumns(10);

		textField_Quantidade = new JTextField();
		textField_Quantidade.setBounds(314, 181, 155, 20);
		textField_Quantidade.setText(String.valueOf(aux.getQuantidade()));
		contentPane.add(textField_Quantidade);
		textField_Quantidade.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		lblNome.setBounds(258, 101, 46, 14);
		contentPane.add(lblNome);

		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setFont(new Font("Arial", Font.BOLD, 14));
		lblCusto.setBounds(258, 144, 46, 14);
		contentPane.add(lblCusto);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Arial", Font.BOLD, 14));
		lblQuantidade.setBounds(219, 183, 85, 14);
		contentPane.add(lblQuantidade);

		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void limparDados() {
				textField_Custo.setText("");
				textField_Nome.setText("");
				textField_Quantidade.setText("");
			}

			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_Custo.getText().equals("") || textField_Nome.getText().equals("")
							|| textField_Quantidade.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Alguns campos estão vazios!");
					} else {
						String nome = textField_Nome.getText();
						double custo = Double.parseDouble(textField_Custo.getText());
						double quantidade = Double.parseDouble(textField_Quantidade.getText());

						Ingrediente ingrediente = new Ingrediente(nome, custo, quantidade);
						IngredienteDAO id = new IngredienteDAO();

						if (id.atualizar(ingrediente)) {
							JOptionPane.showMessageDialog(null, "Ingrediente Atualizado");
							limparDados();
							new GestaoIngredientes(funcionario).setVisible(true);
							dispose();
						} else
							JOptionPane.showMessageDialog(null, "Dados inválidos");
					}
				} catch (Error err) {
					JOptionPane.showMessageDialog(null, "Dados inválidos");
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(229, 270, 109, 28);
		contentPane.add(btnNewButton);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_Custo.setText("");
				textField_Nome.setText("");
				textField_Quantidade.setText("");
			}
		});
		btnLimpar.setBackground(Color.RED);
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpar.setBounds(348, 270, 109, 28);
		contentPane.add(btnLimpar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestaoIngredientes(funcionario).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBounds(467, 270, 89, 27);
		contentPane.add(btnVoltar);
	}

}
