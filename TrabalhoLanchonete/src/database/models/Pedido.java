package database.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Pedido {
	private int codigop;
	private String embalagem;
	private double preco;
	private String formaPag;
	private String numCard;
	private Date data;
	private List<Opcao> opcoes;

	public Pedido (int codigop, String embalagem, Date data, String formaPag, List<Opcao> opcoes, String numCard)
	{
		this.codigop=codigop;
		this.embalagem=embalagem;
		this.data=data;
		this.opcoes=opcoes;
		this.numCard=numCard;
		this.preco = getPrecoBanco();
	}

	public Pedido() {

	}

	public int getCodigop() {
		return codigop;
	}

	public void setCodigop(int codigop) {
		this.codigop = codigop;
	}

	public String getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getFormaPag() {
		return formaPag;
	}

	public void setFormaPag(String formaPag) {
		this.formaPag = formaPag;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {

		this.opcoes = opcoes;
	}

	public void addComplementos() {
		preco = getPrecoBanco();
	}

	public void removerIngredientes()
	{
		preco = getPrecoBanco();
	}

	public java.sql.Date getDataB(){
		this.setData(Calendar.getInstance().getTime());
		java.sql.Date d= new java.sql.Date(this.getData().getTime());
		return d;
	}

	public String getNumCard() {
		return numCard;
	}

	public void setNumCard(String numCard) {
		this.numCard = numCard;
	}

	public double getPrecoBanco()
	{
		preco = 0;
		for(int i = 0; i < opcoes.size();i++)
		{
			preco += opcoes.get(i).getPreco();
		}
		return preco;	
	}
}