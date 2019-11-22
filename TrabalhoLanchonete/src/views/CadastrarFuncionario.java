package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.dao.FuncionarioDAO;
import database.models.Funcionario;

public class CadastrarFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNomeFuncionario;
	private JTextField textCPF;
	private JLabel lblGerente;
	private JPasswordField passwordFuncionario;
	private JTextField textSalario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarFuncionario frame = new CadastrarFuncionario();
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
	public CadastrarFuncionario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 421);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastroDeFuncionrios = new JLabel("Cadastro De Funcion\u00E1rios");
		lblCadastroDeFuncionrios.setBounds(197, 11, 314, 28);
		lblCadastroDeFuncionrios.setFont(new Font("Arial", Font.BOLD, 24));
		contentPane.add(lblCadastroDeFuncionrios);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(238, 91, 46, 14);
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(lblNome);

		textNomeFuncionario = new JTextField();
		textNomeFuncionario.setBounds(284, 88, 134, 20);
		contentPane.add(textNomeFuncionario);
		textNomeFuncionario.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(245, 138, 46, 14);
		lblCpf.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(lblCpf);

		textCPF = new JTextField();
		textCPF.setBounds(284, 135, 134, 20);
		contentPane.add(textCPF);
		textCPF.setColumns(10);

		lblGerente = new JLabel("Fun\u00E7\u00E3o:");
		lblGerente.setBounds(225, 183, 66, 14);
		lblGerente.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(lblGerente);

		JRadioButton rdbtnGerente = new JRadioButton("Gerente\r\n");
		JRadioButton rdbtnCaixa = new JRadioButton("Caixa");

		// verifica se um radio está selecionado e apaga o outro
		rdbtnGerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnGerente.isSelected()) {
					rdbtnCaixa.setSelected(false);
				}
			}
		});
		rdbtnGerente.setBounds(284, 180, 76, 23);
		rdbtnGerente.setBackground(Color.LIGHT_GRAY);
		rdbtnGerente.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(rdbtnGerente);

		// verifica se um radio está selecionado e apaga o outro
		rdbtnCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnCaixa.isSelected()) {
					rdbtnGerente.setSelected(false);
				}
			}
		});
		rdbtnCaixa.setBounds(366, 180, 109, 23);
		rdbtnCaixa.setBackground(Color.LIGHT_GRAY);
		rdbtnCaixa.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(rdbtnCaixa);

		JButton btnCadastrar = new JButton("Cadastrar");

		btnCadastrar.addActionListener(new ActionListener() {

			// limpar dados apos salvar
			private void limparDados() {
				textNomeFuncionario.setText("");
				textCPF.setText("");
				rdbtnCaixa.setSelected(false);
				rdbtnGerente.setSelected(false);
				passwordFuncionario.setText("");
				textSalario.setText("");
			}

			// Salvar
			public void actionPerformed(ActionEvent e) {
				try {
					if (textNomeFuncionario.getText().equals("")|| textCPF.getText().equals("")
							|| textSalario.getText().equals("") || new String(passwordFuncionario.getPassword()).equals("")
							|| !rdbtnCaixa.isSelected() && !rdbtnGerente.isSelected()) {
						JOptionPane.showMessageDialog(null, "Alguns campos estão vazios!");
					} else {	
						String nome = textNomeFuncionario.getText();
						String cpf = textCPF.getText();
						boolean gerente = false;

						if (rdbtnCaixa.isSelected()) {
							gerente = false;
						} else if (rdbtnGerente.isSelected()) {
							gerente = true;
						}

						String senha = new String(passwordFuncionario.getPassword());
						String salario = textSalario.getText();
						
						Funcionario funcionario = new Funcionario(nome, cpf, senha, Double.parseDouble(salario), gerente);
						FuncionarioDAO fd = new FuncionarioDAO();
						if (fd.cadastrar(funcionario)) {
							JOptionPane.showMessageDialog(null, "Funcionário Cadastrado");
							new GestaoFuncionarios().setVisible(true);
							dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Funcionário já cadastrado ou dados inválidos");
					}
				} catch (Error err) {
					JOptionPane.showMessageDialog(null, "Dados inválidos");
				}

			}
		});
		btnCadastrar.setBackground(Color.GREEN);
		btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCadastrar.setBounds(197, 319, 109, 28);
		contentPane.add(btnCadastrar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			// botao limpar dados
			public void actionPerformed(ActionEvent e) {
				textNomeFuncionario.setText("");
				textCPF.setText("");
				rdbtnCaixa.setSelected(false);
				rdbtnGerente.setSelected(false);
				passwordFuncionario.setText("");
				textSalario.setText("");
			}

		});
		btnLimpar.setBackground(Color.RED);
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpar.setBounds(316, 319, 97, 28);
		contentPane.add(btnLimpar);

		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(225, 225, 59, 14);
		lblSenha.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(lblSenha);

		passwordFuncionario = new JPasswordField();
		passwordFuncionario.setBounds(284, 223, 140, 20);
		contentPane.add(passwordFuncionario);

		JLabel lblSalrio = new JLabel("Sal\u00E1rio:");
		lblSalrio.setFont(new Font("Arial", Font.BOLD, 14));
		lblSalrio.setBounds(215, 271, 59, 14);
		contentPane.add(lblSalrio);

		textSalario = new JTextField();
		textSalario.setBounds(284, 269, 140, 20);
		contentPane.add(textSalario);
		textSalario.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestaoFuncionarios().setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBounds(423, 319, 89, 27);
		contentPane.add(btnVoltar);
	}
}
