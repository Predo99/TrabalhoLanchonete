package database.models;

public class Funcionario {
	
	private String nomef;
	private String cpf;
	private String senha;
	private double salario;
	private boolean gerente;
	
	public Funcionario(String nomef, String cpf, String senha, double salario, boolean gerente) {
		this.nomef = nomef;
		this.cpf = cpf;
		this.senha = senha;
		this.salario = salario;
		this.gerente = gerente;
	}

	public Funcionario() {
		
	}

	public String getNomef() {
		return nomef;
	}

	public void setNomef(String nomef) {
		this.nomef = nomef;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean isGerente() {
		return gerente;
	}

	public void setGerente(boolean gerente) {
		this.gerente = gerente;
	}
	
	
	
}
