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
		if(opcoes.size() > 0  && opcoes.get(0).getImagem() == null) {
			byte[] data = converter("/batatamolho.jpg");
			od.atualizar("Batata Frita com Molho", data);
			
			data = converter("/batatacheddarbacon.jpg");
			od.atualizar("Fritas com Cheddar e Bacon", data);
			
			data = converter("/HamburguerSimples.jpg");
			od.atualizar("Hamburguer Simples", data);
			
			data = converter("/milkshake.jpg");
			od.atualizar("Milkshake", data);
			
			data = converter("/refrigerantes350.jpg");
			od.atualizar("Refrigerante", data);
			
			data = converter("/hotdog.jpg");
			od.atualizar("Cachorro Quente", data);
			
			data = converter("/img_pizza_calabresa.jpg");
			od.atualizar("Pizza Calabresa", data);
			
			data = converter("/pizzapepperoni.jpg");
			od.atualizar("Pizza Pepperoni", data);
			
			data = converter("/xbacon.jpg");
			od.atualizar("X-Bacon", data);
			
			data = converter("/xeggbacon.jpg");
			od.atualizar("X-EggBacon", data);
			
			data = converter("/x-tudo.jpg");
			od.atualizar("X-Tudo", data);
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
