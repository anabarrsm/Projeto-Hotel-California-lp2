package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

public abstract class Quarto {

	protected String idAutenticacao;
	protected int idQuartoNum;
	protected double precoPorPessoa;
	protected double precoBase;
	protected boolean quartoReservado;
	

	public Quarto(String idAutenticacao, int idQuartoNum, double precoPorPessoa, double precoBase) {
		this.idAutenticacao = idAutenticacao;
		this.idQuartoNum = idQuartoNum;
		this.precoPorPessoa = precoPorPessoa;
		this.precoBase = precoBase;
		this.quartoReservado = false;
	}

	public String getIdAutenticacao() {
		return idAutenticacao;
	}


	public int getIdQuartoNum() {
		return idQuartoNum;
	}


	public double getPrecoPorPessoa() {
		return precoPorPessoa;
	}

	public double getPrecoBase() {
		return precoBase;
	}


	public boolean isQuartoReservado() {
		return quartoReservado;
	}

	
	public void setQuartoReservado(boolean status) {
		this.quartoReservado = status;
	}

	public abstract String exibirQuarto();

	public abstract double calcularDiaria();
	
	public abstract int getQtdMaxPessoas();

	

}

