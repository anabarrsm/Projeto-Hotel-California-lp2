package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

public class FormaDePagamento {
	private String tipoDePagamento;
	private double percentualDesconto;
	private int id;
	
	public FormaDePagamento(int id, String tipoDePagamento, double percentualDesconto) {
		this.id = id;
		this.tipoDePagamento = tipoDePagamento;
		this.percentualDesconto = percentualDesconto;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTipo() {
		return tipoDePagamento;
	}
	
	public double getPercentual() {
		return percentualDesconto;
	}
	
	public void setPercentual(double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	
	@Override
	public String toString() {
		return "[" + id + "]" + "Forma de pagamento: " + tipoDePagamento + " (" + percentualDesconto + " de desconto em pagamentos)";
	}

}