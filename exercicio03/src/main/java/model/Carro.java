package model;

import java.time.LocalDate;

public class Carro {
	private int id;
	private String modelo;
	private int quantidade;
	private LocalDate datafabricacao;
	private LocalDate datacompra;
	private float preco;
	
	public Carro () {
		id = -1;
		modelo = "";
		quantidade = 0;
		datafabricacao = LocalDate.now().minusMonths(6);
		datacompra = LocalDate.now();
		preco = 0.00F;
	}
	
	public Carro (int id, String modelo, int quantidade, LocalDate datafabricacao, LocalDate datacompra, float preco) {
		setId(id);
		setModelo(modelo);
		setQuantidade(quantidade);
		setDatafabricacao(datafabricacao);
		setDatacompra(datacompra);
		setPreco(preco);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public LocalDate getDatafabricacao() {
		return datafabricacao;
	}
	public void setDatafabricacao(LocalDate datafabricacao) {
		// Pega a Data Atual
		LocalDate agora = LocalDate.now();
		// Garante que a data de fabricação não pode ser futura
		if (agora.compareTo(datafabricacao) >= 0)
			this.datafabricacao = datafabricacao;
	}
	public LocalDate getDatacompra() {
		return datacompra;
	}
	public void setDatacompra(LocalDate datacompra) {
		// A data de fabricacao deve ser antes da data de compra
		if(getDatafabricacao().isBefore(datacompra))
			this.datacompra = datacompra;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		// Preco deve ser positivo
		if(preco>=0)
			this.preco = preco;
	}
	
	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Id: " + id + "Modelo: " + modelo + "Quantidade: " + quantidade + "Data de fabricação: " + datafabricacao + "Data de compra: " + datacompra + "Preço: " + preco;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Carro) obj).getId());
	}	
}
