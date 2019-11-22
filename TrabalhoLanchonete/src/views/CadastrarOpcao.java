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
import javax.swing.filechooser.FileNameExtensionFilter;

import database.dao.IngredienteDAO;
import database.dao.OpcaoDAO;
import database.models.Ingrediente;
import database.models.Opcao;
import javax.swing.JScrollPane;

public class CadastrarOpcao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Nome;
	private JTextField textField_Preco;
	private File selectedFile;
	private JTextField textField;
	private String filename = null;
	private DefaultListModel<Ingrediente> listModel;
	
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
				/*JFileChooser jfile = new JFileChooser();
				jfile.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image", "jpg", "png");
				jfile.addChoosableFileFilter(filter);*/
				
				JFileChooser fileChooser = new JFileChooser(); 
				
				int returnValue = fileChooser.showOpenDialog(null); 
				File test = fileChooser.getSelectedFile();
				if(test != null) {
					filename = test.getName();
					if(filename.endsWith(".jpg") || filename.endsWith(".JPG") 
							|| filename.endsWith(".png") || filename.endsWith("PNG")) {
						if (returnValue == JFileChooser.APPROVE_OPTION) 
						{
							selectedFile = fileChooser.getSelectedFile();
							filename = selectedFile.getName();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Formato de imagem inválida");
						filename = null;
					}
				}
			}
		});
		btnImagem.setFont(new Font("Arial", Font.PLAIN, 11));
		btnImagem.setBounds(347, 143, 109, 23);
		contentPane.add(btnImagem);
		
		IngredienteDAO fd = new IngredienteDAO();
		
		listModel = new DefaultListModel<>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 209, 148, 64);
		contentPane.add(scrollPane);
		
		JList<Ingrediente> list_1 = new JList(listModel);
		scrollPane.setViewportView(list_1);
		list_1.setCellRenderer(new DefaultListCellRenderer() {
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
				//funcionarios = listModel.toArray();
			}
		});
		btnProcurar.setBounds(466, 177, 177, 23);
		contentPane.add(btnProcurar);
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_Nome.getText().equals("") || textField_Preco.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Alguns campos estão vazios!");
					}else {
						String nome = textField_Nome.getText();
						double preco = Double.parseDouble(textField_Preco.getText());
						byte [] data = null;
						
						if(selectedFile != null) {
							BufferedImage bImage = ImageIO.read(selectedFile);
							filename = filename.substring(filename.lastIndexOf('.')+1);
						    ByteArrayOutputStream bos = new ByteArrayOutputStream();
						    ImageIO.write(bImage, filename, bos );
						    data = bos.toByteArray();
						}
					    
					    List<Ingrediente> ingredientes = new ArrayList();
					    for(int i=0;i<listModel.getSize();i++) {
							ingredientes.add(listModel.elementAt(i));
						}
					    
					    Opcao opcao = new Opcao(nome, preco, data, ingredientes);
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
				limparDados();
			}
		});
		btnLimpar.setBackground(Color.YELLOW);
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpar.setBounds(378, 314, 109, 28);
		contentPane.add(btnLimpar);
		
	}
	
	public void limparDados() {
		textField_Nome.setText("");
		textField_Preco.setText("");
		selectedFile = null;
		textField.setText("");
		listModel.removeAllElements();
	}
}
