package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.dao.PedidoDAO;
import database.models.Pedido;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Finalizacao extends JFrame {

	private JPanel contentPane;
	private PedidoDAO pd;
	/**
	 * Create the frame.
	 * @param pedido 
	 */
	public Finalizacao(Pedido pedido) {
		pd = new PedidoDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPedidoFinalizadoCom = new JLabel("Pedido finalizado com sucesso!");
		lblPedidoFinalizadoCom.setFont(new Font("Arial", Font.BOLD, 20));
		lblPedidoFinalizadoCom.setBounds(71, 11, 308, 38);
		contentPane.add(lblPedidoFinalizadoCom);
		
		JLabel lblNewLabel = new JLabel("Obrigado pela prefer\u00EAncia! ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(91, 194, 259, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblVolteSempre = new JLabel("Volte sempre!");
		lblVolteSempre.setFont(new Font("Arial", Font.BOLD, 20));
		lblVolteSempre.setBounds(153, 229, 144, 21);
		contentPane.add(lblVolteSempre);
		
		JButton btnGerarComprovante = new JButton("Gerar comprovante");
		btnGerarComprovante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comprovante = pd.gerarComprovante(pedido);
				JOptionPane.showMessageDialog(null, comprovante);
			}
		});
		btnGerarComprovante.setFont(new Font("Arial", Font.PLAIN, 12));
		btnGerarComprovante.setBounds(71, 92, 144, 38);
		contentPane.add(btnGerarComprovante);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cardapio().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(244, 92, 103, 38);
		contentPane.add(btnNewButton);
	}

}
