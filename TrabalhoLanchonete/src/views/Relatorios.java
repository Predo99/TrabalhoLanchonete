package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.dao.PedidoDAO;
import database.models.Funcionario;
import database.models.Ingrediente;

public class Relatorios extends JFrame {

	private JPanel contentPane;
	private JLabel lblDigite;
	private JTextArea textRelatorio;
	private PedidoDAO pd = new PedidoDAO();
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios frame = new Relatorios();
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
	public Relatorios(Funcionario funcionario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRelatorio = new JLabel("Rel\u00E1torios");
		lblRelatorio.setFont(new Font("Arial", Font.BOLD, 20));
		lblRelatorio.setBounds(172, 11, 112, 29);
		contentPane.add(lblRelatorio);
		
		lblDigite = new JLabel("Digite o ano do relat\u00F3rio desejado:");
		lblDigite.setFont(new Font("Arial", Font.BOLD, 13));
		lblDigite.setBounds(10, 47, 231, 29);
		contentPane.add(lblDigite);
		
		JTextField textAno = new JTextField();
		textAno.setBounds(232, 51, 78, 20);
		contentPane.add(textAno);
		
		JTextArea textObs = new JTextArea();
		textObs.setFont(new Font("Arial", Font.PLAIN, 11));
		textObs.setText("Obs.: Ao deixar o campo de ano vazio o relat\u00F3rio gerado ser\u00E1 de todos os anos.");
		textObs.setSize(200, 29);
		textObs.setLineWrap(true);
		textObs.setLocation(10, 72);
		contentPane.add(textObs);
		
		textRelatorio = new JTextArea();
		textRelatorio.setFont(new Font("Arial", Font.PLAIN, 13));
		textRelatorio.setSize(324, 200);
		textRelatorio.setLineWrap(true);
		textRelatorio.setLocation(10, 112);
		contentPane.add(textRelatorio);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textAno.getText().equals("")) {
					textRelatorio.setText(pd.gerarRelatorio(Integer.parseInt(textAno.getText())));
				}else {
					textRelatorio.setText(pd.gerarRelatorio());
				}
			}
		});
		btnGerar.setBounds(331, 50, 93, 23);
		contentPane.add(btnGerar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Menu(funcionario).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBounds(344, 289, 80, 23);
		contentPane.add(btnVoltar);
	}

}
