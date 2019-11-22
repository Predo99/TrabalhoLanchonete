package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.models.Opcao;

public class Imagem extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Imagem frame = new Imagem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/*0*
	 * Create the frame.
	 */
	public Imagem(Opcao opcao) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 324, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImagem = new JLabel("Imagem");
		lblImagem.setFont(new Font("Arial", Font.BOLD, 15));
		lblImagem.setBounds(114, 0, 158, 25);
		contentPane.add(lblImagem);
		
		JLabel lblPainel = new JLabel("");
		lblPainel.setBounds(43, 25, 216, 149);
		contentPane.add(lblPainel);
		
		ImageIcon img = new ImageIcon(opcao.getImagem());
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(lblPainel.getWidth(), lblPainel.getHeight(),  java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg); 
		lblPainel.setIcon(img);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnFechar.setBackground(Color.RED);
		btnFechar.setFont(new Font("Arial", Font.BOLD, 13));
		btnFechar.setBounds(208, 185, 90, 25);
		contentPane.add(btnFechar);
	}

}
