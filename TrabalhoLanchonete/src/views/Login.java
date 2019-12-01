package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.dao.FuncionarioDAO;
import database.models.Funcionario;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUser;
	private JPasswordField passwordField;

	/**
	 * Criação de Frame Login.
	 */
	public Login() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 436);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.GRAY);
		panel.setBounds(160, 59, 357, 289);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLogin.setBounds(143, 11, 94, 29);
		panel.add(lblLogin);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setFont(new Font("Arial", Font.BOLD, 16));
		lblUsurio.setBounds(63, 59, 70, 14);
		panel.add(lblUsurio);

		textUser = new JTextField();
		textUser.setForeground(Color.GRAY);
		textUser.setBounds(63, 84, 222, 29);
		panel.add(textUser);
		textUser.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial", Font.BOLD, 16));
		lblSenha.setBounds(63, 131, 54, 14);
		panel.add(lblSenha);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.GRAY);
		passwordField.setBounds(63, 156, 222, 29);
		panel.add(passwordField);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Classe FuncionarioDAO para fazer as operações do banco
				FuncionarioDAO fd = new FuncionarioDAO();
				Funcionario funcionario = fd.login(textUser.getText(), new String(passwordField.getPassword()));
				if (funcionario != null) {
					JOptionPane.showMessageDialog(null, "Login realizado com sucesso.");
					new Menu(funcionario).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Login ou senha incorretos");
				}
			}
		});
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEntrar.setBackground(Color.GREEN);
		btnEntrar.setBounds(126, 217, 94, 29);
		panel.add(btnEntrar);

		JLabel imgLogin = new JLabel("");
		imgLogin.setBounds(76, 11, 618, 375);
		contentPane.add(imgLogin);
		imgLogin.setIcon(new ImageIcon(getClass().getResource("/burger.png")));
	}
}
