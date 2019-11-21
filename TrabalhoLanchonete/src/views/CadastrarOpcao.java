package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.dao.IngredienteDAO;
import database.dao.OpcaoDAO;
import database.models.Ingrediente;
import database.models.Opcao;

public class CadastrarOpcao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Nome;
	private JTextField textField_Preco;
	private File selectedFile;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarOpcao frame = new CadastrarOpcao();
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
	public CadastrarOpcao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarOpo = new JLabel("Cadastrar Op\u00E7\u00E3o");
		lblCadastrarOpo.setFont(new Font("Arial", Font.BOLD, 24));
		lblCadastrarOpo.setBounds(259, 22, 292, 29);
		contentPane.add(lblCadastrarOpo);
		
		textField_Nome = new JTextField();
		textField_Nome.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_Nome.setBounds(347, 85, 86, 20);
		contentPane.add(textField_Nome);
		textField_Nome.setColumns(10);
		
		textField_Preco = new JTextField();
		textField_Preco.setBounds(347, 116, 86, 20);
		contentPane.add(textField_Preco);
		textField_Preco.setColumns(10);
				      
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		lblNome.setBounds(291, 87, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setFont(new Font("Arial", Font.BOLD, 14));
		lblCusto.setBounds(291, 118, 46, 14);
		contentPane.add(lblCusto);
		
		JLabel lblImagem = new JLabel("Imagem:");
		lblImagem.setFont(new Font("Arial", Font.BOLD, 14));
		lblImagem.setBounds(279, 143, 58, 20);
		contentPane.add(lblImagem);
		
		JButton btnImagem = new JButton("Buscar Imagem");
		btnImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(); 
				int returnValue = fileChooser.showOpenDialog(null); 
				if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
					selectedFile = fileChooser.getSelectedFile();
				}
			}
		});
		btnImagem.setFont(new Font("Arial", Font.PLAIN, 11));
		btnImagem.setBounds(347, 143, 109, 23);
		contentPane.add(btnImagem);
		
		IngredienteDAO fd = new IngredienteDAO();
		List<Ingrediente> ingredientes = new ArrayList();
		
		DefaultListModel<Ingrediente> listModel = new DefaultListModel<>();
		
		JList<Ingrediente> list = new JList(listModel);
		list.setBounds(347, 209, 148, 64);
		list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Ingrediente) {
                    // Here value will be of the Type 'CD'
                    ((JLabel) renderer).setText(((Ingrediente) value).getNomei());
                }
                return renderer;
            }
        });
	
		contentPane.add(list);
		
		JLabel lblTeste = new JLabel("Ingredientes:");
		lblTeste.setFont(new Font("Arial", Font.BOLD, 14));
		lblTeste.setBounds(243, 180, 94, 20);
		contentPane.add(lblTeste);	
		

		textField = new JTextField();
		textField.setBounds(347, 178, 109, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JButton btnProcurar = new JButton("Procurar Ingrediente");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ingrediente f = fd.consultar(textField.getText());
				if(f != null) {
					listModel.addElement(f);
				}
				for(int i=0;i<listModel.getSize();i++) {
					ingredientes.add(listModel.elementAt(i));
				}
				//funcionarios = listModel.toArray();
			}
		});
		btnProcurar.setBounds(466, 177, 177, 23);
		contentPane.add(btnProcurar);
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void limparDados() {
				textField_Nome.setText("");
				textField_Preco.setText("");
				textField.setText("");
			}
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_Nome.getText().equals("") || textField_Preco.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Alguns campos estão vazios!");
					}else {
						String nome = textField_Nome.getText();
						double preco = Double.parseDouble(textField_Preco.getText());
						
						BufferedImage bImage = ImageIO.read(selectedFile);
					    ByteArrayOutputStream bos = new ByteArrayOutputStream();
					    ImageIO.write(bImage, "jpg", bos );
					    byte [] data = bos.toByteArray();
					    
					    Opcao opcao = new Opcao(nome, preco,data, ingredientes);
					    OpcaoDAO od = new OpcaoDAO();
					    
					    if (od.cadastrar(opcao)) {
							JOptionPane.showMessageDialog(null, "Opção cadastrada");
							limparDados();
						}
						else
							JOptionPane.showMessageDialog(null, "Opção já cadastrada ou dados inválidos");
					}
					
				}catch(Error | IOException err) {
					JOptionPane.showMessageDialog(null, "Dados inválidos");
				}
			}
		});
		btnConfirmar.setBackground(Color.GREEN);
		btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
		btnConfirmar.setBounds(259, 314, 109, 28);
		contentPane.add(btnConfirmar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_Nome.setText("");
				textField_Preco.setText("");
				selectedFile = null;
				textField.setText("");
				ingredientes.clear();
			}
		});
		btnLimpar.setBackground(Color.YELLOW);
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpar.setBounds(378, 314, 109, 28);
		contentPane.add(btnLimpar);
		
		
		
	}
}
