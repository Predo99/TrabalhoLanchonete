package main;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import database.dao.OpcaoDAO;
import database.models.Opcao;
import views.Cardapio;

public class mainCardapio {

	public static void main(String[] args) throws IOException {
		OpcaoDAO od = new OpcaoDAO();
		List<Opcao> opcoes = od.consultar();
		if(opcoes.size() > 0) {
			byte[] data = converter("/batataFrita.jpg");
			od.atualizar("Batata frita", data);
			
			data = converter("/HamburgerSimples.png");
			od.atualizar("Hamburguer simples", data);
			
			data = converter("/milkShake2.jpg");
			od.atualizar("Milkshake", data);
			
			data = converter("/refrigerantes350.jpg");
			od.atualizar("Refrigerante", data);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cardapio frame = new Cardapio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static byte[] converter (String path) throws IOException {
		BufferedImage bImage = ImageIO.read(mainLogin.class.getResource(path));
		String filename = path.substring(path.lastIndexOf('.') + 1);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, filename, bos);
		
		return bos.toByteArray();
	}
}
