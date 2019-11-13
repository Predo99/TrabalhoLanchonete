package database.models;

import java.util.List;

public class Opcao {

	private String nomeo;
	private double preco;
	private byte[] imagem;
	private List<Ingrediente> ingredientes;
	
	public Opcao(String nomeo, double preco, byte[] imagem, List<Ingrediente> ingredientes) {
		this.nomeo = nomeo;
		this.preco = preco;
		this.imagem = imagem;
		this.ingredientes = ingredientes;
	}

	public Opcao() {
		super();
	}
	
	public String getNomeo() {
		return nomeo;
	}

	public void setNomeo(String nomeo) {
		this.nomeo = nomeo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
}
