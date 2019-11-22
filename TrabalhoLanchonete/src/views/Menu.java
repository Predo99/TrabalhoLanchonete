package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.models.Funcionario;
import database.models.Ingrediente;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JButton btnIngredientes;
	private JButton btnOpcao;
	private JButton btnCardapio;
	private JButton btnRelatorio;
	private JButton btnSair;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu(Funcionario funcionario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBemVindo = new JLabel("Bem vindo " + funcionario.getNomef() + "!");
		lblBemVindo.setFont(new Font("Arial", Font.BOLD, 20));
		lblBemVindo.setBounds(10, 11, 414, 29);
		contentPane.add(lblBemVindo);
		
		if(funcionario.isGerente()) {
			JButton btnFuncionario = new JButton("Gest\u00E3o de Funcion\u00E1rios");
			btnFuncionario.setFont(new Font("Arial", Font.PLAIN, 13));
			btnFuncionario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new GestaoFuncionarios(funcionario).setVisible(true);
					dispose();
				}
			});
			btnFuncionario.setBounds(10, 208, 172, 50);
			contentPane.add(btnFuncionario);
		}

		btnIngredientes = new JButton("Gestão de Ingredientes");
		btnIngredientes.setFont(new Font("Arial", Font.PLAIN, 13));
		btnIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GestaoIngredientes(funcionario).setVisible(true);
				dispose();
			}
		});
		btnIngredientes.setBounds(10, 56, 172, 50);
		contentPane.add(btnIngredientes);
		
		btnOpcao = new JButton("Gestão de Opções");
		btnOpcao.setFont(new Font("Arial", Font.PLAIN, 13));
		btnOpcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GestaoOpcao(funcionario).setVisible(true);
				dispose();
			}
		});
		btnOpcao.setBounds(252, 56, 172, 50);
		contentPane.add(btnOpcao);
		
		btnCardapio = new JButton("Gestão de Cardápio");
		btnCardapio.setFont(new Font("Arial", Font.PLAIN, 13));
		btnCardapio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GestaoCardapio(funcionario).setVisible(true);
				dispose();
			}
		});
		btnCardapio.setBounds(252, 132, 172, 50);
		contentPane.add(btnCardapio);
		
		btnRelatorio = new JButton("Gerar Relat\u00F3rios");
		btnRelatorio.setFont(new Font("Arial", Font.PLAIN, 13));
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnRelatorio.setBounds(10, 132, 172, 50);
		contentPane.add(btnRelatorio);
		
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Arial", Font.BOLD, 15));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnSair.setBounds(326, 208, 98, 50);
		contentPane.add(btnSair);
	}

}
