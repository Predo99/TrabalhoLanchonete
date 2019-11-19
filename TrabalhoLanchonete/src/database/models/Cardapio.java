package database.models;

import java.util.List;
public class Cardapio {
	
	private int id;
	
	private List<Opcao> opcoes;

	
	public Cardapio(int id, List<Opcao> opcoes) {
		super();
		this.id = id;
		this.opcoes = opcoes;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

	@Override
	public String toString() {
		return "Cardapio [id=" + id + ", opcoes=" + opcoes + "]";
	}

	
	
}
