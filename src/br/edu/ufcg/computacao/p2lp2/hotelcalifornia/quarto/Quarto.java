package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

public abstract class Quarto {

	protected String idAutenticacao;
	protected int idQuartoNum;
	protected double precoPorPessoa;
	protected double precoBase;

	public Quarto(String idAutenticacao, int idQuartoNum, double precoPorPessoa, double precoBase) {
		this.idAutenticacao = idAutenticacao;
		this.idQuartoNum = idQuartoNum;
		this.precoPorPessoa = precoPorPessoa;
		this.precoBase = precoBase;
	}
	

	public abstract String exibirQuarto();

	public abstract double calcularDiaria();
}

