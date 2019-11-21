package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GestaoFuncionarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestaoFuncionarios frame = new GestaoFuncionarios();
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
	public GestaoFuncionarios() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 442);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 2);
		contentPane.add(scrollPane);
		
		DefaultListModel listFuncionario = new DefaultListModel();
		JList list = new JList(listFuncionario);
		listFuncionario.add(0,"teste");
		
		list.setBounds(42, 131, 275, 245);
		contentPane.add(list);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.YELLOW);
		btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditar.setBounds(327, 182, 89, 28);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setFont(new Font("Arial", Font.BOLD, 14));
		btnExcluir.setBounds(327, 274, 89, 28);
		contentPane.add(btnExcluir);
		
		JLabel lblGestoDeFuncionrios = new JLabel("Gest\u00E3o de Funcion\u00E1rios");
		lblGestoDeFuncionrios.setFont(new Font("Arial", Font.BOLD, 18));
		lblGestoDeFuncionrios.setBounds(109, 18, 222, 28);
		contentPane.add(lblGestoDeFuncionrios);
		
		textPesquisa = new JTextField();
		textPesquisa.setBounds(42, 86, 168, 20);
		contentPane.add(textPesquisa);
		textPesquisa.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(210, 86, 107, 20);
		contentPane.add(btnPesquisar);
	}
}
