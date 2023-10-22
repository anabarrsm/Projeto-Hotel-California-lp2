package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

/** 
 * @author maria helena
 * 
 * cria a classe abstrata Quarto
 */
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

	/**
	 * Exibe a representação textual do quarto
	 * @return String resentação textual do quarto
	 */
	public abstract String exibirQuarto();
	
	/**
	 * Realiza o cálculo da diária de um quarto
	 * @return double valor da diária do quarto
	 */

	public abstract double calcularDiaria();
	
	public abstract int getQtdMaxPessoas();

	

}

