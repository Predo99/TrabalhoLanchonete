package views;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.dao.IngredienteDAO;
import database.dao.OpcaoDAO;
import database.models.Funcionario;
import database.models.Ingrediente;
import database.models.Opcao;

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
	 * 
	 * @param funcionario
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CadastrarOpcao frame = new
	 * CadastrarOpcao(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 * 
	 * /** Create the frame.
	 */
	public CadastrarOpcao(Funcionario funcionario) {
		setTitle("Cadastrar Opção");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastrarOpo = new JLabel("Cadastrar Op\u00E7\u00E3o");
		lblCadastrarOpo.setFont(new Font("Arial", Font.BOLD, 24));
		lblCadastrarOpo.setBounds(211, 23, 292, 29);
		contentPane.add(lblCadastrarOpo);

		textField_Nome = new JTextField();
		textField_Nome.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_Nome.setBounds(284, 85, 109, 20);
		contentPane.add(textField_Nome);
		textField_Nome.setColumns(10);

		textField_Preco = new JTextField();
		textField_Preco.setBounds(284, 116, 109, 20);
		contentPane.add(textField_Preco);
		textField_Preco.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		lblNome.setBounds(228, 87, 46, 14);
		contentPane.add(lblNome);

		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setFont(new Font("Arial", Font.BOLD, 14));
		lblCusto.setBounds(228, 118, 46, 14);
		contentPane.add(lblCusto);

		JLabel lblImagem = new JLabel("Imagem:");
		lblImagem.setFont(new Font("Arial", Font.BOLD, 14));
		lblImagem.setBounds(216, 143, 58, 20);
		contentPane.add(lblImagem);

		JButton btnImagem = new JButton("Buscar Imagem");
		btnImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * JFileChooser jfile = new JFileChooser(); jfile.setCurrentDirectory(new
				 * File(System.getProperty("user.home"))); FileNameExtensionFilter filter = new
				 * FileNameExtensionFilter("*.Image", "jpg", "png");
				 * jfile.addChoosableFileFilter(filter);
				 */

				JFileChooser fileChooser = null;
				LookAndFeel previousLF = UIManager.getLookAndFeel();
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					fileChooser = new JFileChooser();
					UIManager.setLookAndFeel(previousLF);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				int returnValue = fileChooser.showOpenDialog(null);
				File test = fileChooser.getSelectedFile();

				if (test != null) {
					filename = test.getName();
					if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".png")
							|| filename.endsWith("PNG")) {
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image", "jpg", "png");
							fileChooser.setFileFilter(filter);
							selectedFile = fileChooser.getSelectedFile();

							filename = selectedFile.getName();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Formato de imagem inválida");
						filename = null;
					}
				}
			}
		});
		btnImagem.setFont(new Font("Arial", Font.PLAIN, 11));
		btnImagem.setBounds(284, 143, 109, 23);
		contentPane.add(btnImagem);

		IngredienteDAO fd = new IngredienteDAO();

		listModel = new DefaultListModel<>();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 208, 148, 64);
		contentPane.add(scrollPane);

		JLabel lblTeste = new JLabel("Ingredientes:");
		lblTeste.setFont(new Font("Arial", Font.BOLD, 14));
		lblTeste.setBounds(180, 177, 94, 20);
		contentPane.add(lblTeste);

		textField = new JTextField();
		textField.setBounds(284, 177, 109, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnProcurar = new JButton("Procurar ");
		btnProcurar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ingrediente f = fd.consultar(textField.getText());
				if (f != null) {
					listModel.addElement(f);
				}
				// funcionarios = listModel.toArray();
			}
		});
		btnProcurar.setBounds(403, 174, 94, 23);
		contentPane.add(btnProcurar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_Nome.getText().equals("") || textField_Preco.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Alguns campos estão vazios!");
					} else {
						String nome = textField_Nome.getText();
						double preco = Double.parseDouble(textField_Preco.getText());
						byte[] data = null;

						if (selectedFile != null) {
							BufferedImage bImage = ImageIO.read(selectedFile);
							filename = filename.substring(filename.lastIndexOf('.') + 1);
							ByteArrayOutputStream bos = new ByteArrayOutputStream();
							ImageIO.write(bImage, filename, bos);
							data = bos.toByteArray();
						}

						List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
						for (int i = 0; i < listModel.getSize(); i++) {
							ingredientes.add(listModel.elementAt(i));
						}

						Opcao opcao = new Opcao(nome, preco, data, ingredientes);
						OpcaoDAO od = new OpcaoDAO();

						if (od.cadastrar(opcao)) {
							JOptionPane.showMessageDialog(null, "Opção cadastrada");
							limparDados();
							new GestaoOpcao(funcionario).setVisible(true);
							dispose();
						} else
							JOptionPane.showMessageDialog(null, "Opção já cadastrada ou dados inválidos");
					}

				} catch (Error | IOException err) {
					JOptionPane.showMessageDialog(null, "Dados inválidos");
				}
			}
		});
		btnConfirmar.setBackground(Color.GREEN);
		btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
		btnConfirmar.setBounds(199, 314, 109, 28);
		contentPane.add(btnConfirmar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparDados();
			}
		});
		btnLimpar.setBackground(Color.RED);
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpar.setBounds(318, 314, 109, 28);
		contentPane.add(btnLimpar);

		JList<Ingrediente> list_1 = new JList<Ingrediente>(listModel);
		list_1.setBounds(284, 208, 146, 62);
		contentPane.add(list_1);
		list_1.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Ingrediente) {
					// Here value will be of the Type 'CD'
					((JLabel) renderer).setText(((Ingrediente) value).getNomei());
				}
				return renderer;
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestaoOpcao(funcionario).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBounds(437, 314, 89, 27);
		contentPane.add(btnVoltar);

	}

	/**
	 * @wbp.parser.constructor
	 */
	public CadastrarOpcao(Funcionario funcionario, Opcao aux) {
		// TODO Auto-generated constructor stub
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastrarOpo = new JLabel("Cadastrar Op\u00E7\u00E3o");
		lblCadastrarOpo.setFont(new Font("Arial", Font.BOLD, 24));
		lblCadastrarOpo.setBounds(211, 23, 292, 29);
		contentPane.add(lblCadastrarOpo);

		textField_Nome = new JTextField();
		textField_Nome.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_Nome.setBounds(284, 85, 109, 20);
		textField_Nome.setText(aux.getNomeo());
		contentPane.add(textField_Nome);
		textField_Nome.setColumns(10);

		textField_Preco = new JTextField();
		textField_Preco.setBounds(284, 116, 109, 20);
		textField_Preco.setText(String.valueOf(aux.getPreco()));
		contentPane.add(textField_Preco);
		textField_Preco.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		lblNome.setBounds(228, 87, 46, 14);
		contentPane.add(lblNome);

		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setFont(new Font("Arial", Font.BOLD, 14));
		lblCusto.setBounds(228, 118, 46, 14);
		contentPane.add(lblCusto);

		JLabel lblImagem = new JLabel("Imagem:");
		lblImagem.setFont(new Font("Arial", Font.BOLD, 14));
		lblImagem.setBounds(216, 143, 58, 20);
		contentPane.add(lblImagem);

		JButton btnImagem = new JButton("Buscar Imagem");
		btnImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * JFileChooser jfile = new JFileChooser(); jfile.setCurrentDirectory(new
				 * File(System.getProperty("user.home"))); FileNameExtensionFilter filter = new
				 * FileNameExtensionFilter("*.Image", "jpg", "png");
				 * jfile.addChoosableFileFilter(filter);
				 */

				JFileChooser fileChooser = null;
				LookAndFeel previousLF = UIManager.getLookAndFeel();
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					fileChooser = new JFileChooser();
					UIManager.setLookAndFeel(previousLF);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				int returnValue = fileChooser.showOpenDialog(null);
				File test = fileChooser.getSelectedFile();

				if (test != null) {
					filename = test.getName();
					if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".png")
							|| filename.endsWith("PNG")) {
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image", "jpg", "png");
							fileChooser.setFileFilter(filter);
							selectedFile = fileChooser.getSelectedFile();

							filename = selectedFile.getName();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Formato de imagem inválida");
						filename = null;
					}
				}
			}
		});
		btnImagem.setFont(new Font("Arial", Font.PLAIN, 11));
		btnImagem.setBounds(284, 143, 109, 23);
		contentPane.add(btnImagem);

		IngredienteDAO fd = new IngredienteDAO();

		listModel = new DefaultListModel<>();
		for (int i = 0; i < aux.getIngredientes().size(); i++) {
			listModel.add(i, aux.getIngredientes().get(i));
		}

		JLabel lblTeste = new JLabel("Ingredientes:");
		lblTeste.setFont(new Font("Arial", Font.BOLD, 14));
		lblTeste.setBounds(180, 177, 94, 20);
		contentPane.add(lblTeste);

		textField = new JTextField();
		textField.setBounds(284, 177, 109, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnProcurar = new JButton("Adicionar ");
		btnProcurar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ingrediente f = fd.consultar(textField.getText());
				if (f != null) {
					listModel.addElement(f);
				}
				// funcionarios = listModel.toArray();
			}
		});
		btnProcurar.setBounds(403, 174, 94, 23);
		contentPane.add(btnProcurar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_Nome.getText().equals("") || textField_Preco.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Alguns campos estão vazios!");
					} else {
						String nome = textField_Nome.getText();
						double preco = Double.parseDouble(textField_Preco.getText());
						byte[] data = aux.getImagem();

						if (selectedFile != null) {
							BufferedImage bImage = ImageIO.read(selectedFile);
							filename = filename.substring(filename.lastIndexOf('.') + 1);
							ByteArrayOutputStream bos = new ByteArrayOutputStream();
							ImageIO.write(bImage, filename, bos);
							data = bos.toByteArray();
						}

						List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
						for (int i = 0; i < listModel.getSize(); i++) {
							ingredientes.add(listModel.elementAt(i));
						}

						Opcao opcao = new Opcao(nome, preco, data, ingredientes);
						OpcaoDAO od = new OpcaoDAO();

						if (od.atualizar(opcao)) {
							JOptionPane.showMessageDialog(null, "Opção Atualizada");
							limparDados();
							new GestaoOpcao(funcionario).setVisible(true);
							dispose();
						} else
							JOptionPane.showMessageDialog(null, "Dados inválidos");
					}

				} catch (Error | IOException err) {
					JOptionPane.showMessageDialog(null, "Dados inválidos");
				}
			}
		});
		btnConfirmar.setBackground(Color.GREEN);
		btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
		btnConfirmar.setBounds(199, 314, 109, 28);
		contentPane.add(btnConfirmar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparDados();
			}
		});
		btnLimpar.setBackground(Color.RED);
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpar.setBounds(318, 314, 109, 28);
		contentPane.add(btnLimpar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(284, 208, 146, 62);
		contentPane.add(scrollPane);

		JList<Ingrediente> list_1 = new JList<Ingrediente>(listModel);
		scrollPane.setViewportView(list_1);
		list_1.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Ingrediente) {
					// Here value will be of the Type 'CD'
					((JLabel) renderer).setText(((Ingrediente) value).getNomei());
				}
				return renderer;
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestaoOpcao(funcionario).setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.setBounds(437, 314, 89, 27);
		contentPane.add(btnVoltar);

		JButton btnNewButton = new JButton("Deletar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingrediente f = fd.consultar(textField.getText());
				if (f != null) {
					listModel.removeElement(f);
				}
				// funcionarios = listModel.toArray();
			}
		});
		btnNewButton.setBounds(507, 174, 89, 23);
		contentPane.add(btnNewButton);
	}

	public void limparDados() {
		textField_Nome.setText("");
		textField_Preco.setText("");
		selectedFile = null;
		textField.setText("");
		listModel.removeAllElements();
	}
}
