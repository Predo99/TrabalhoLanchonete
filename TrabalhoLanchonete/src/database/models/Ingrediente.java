package database.models;

public class Ingrediente {

	private String nomei;
	private double quantidade;
	private double custo;
	
	public Ingrediente(String nomei, double quantidade, double custo) {
		this.nomei = nomei;
		this.quantidade = quantidade;
		this.custo = custo;
	}

	public Ingrediente() {
		
	}

	public String getNomei() {
		return nomei;
	}

	public void setNomei(String nomei) {
		this.nomei = nomei;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == this)  
			return true; 
		if (!(obj instanceof Ingrediente))
			return false; 
		Ingrediente ingrediente = (Ingrediente) obj; 
		
		return nomei.equals(ingrediente.nomei);
	}
}
