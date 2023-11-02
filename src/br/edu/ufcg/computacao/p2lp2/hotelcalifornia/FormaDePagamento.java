package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.text.DecimalFormat;
import java.util.Objects;

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
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    FormaDePagamento that = (FormaDePagamento) o;
	    return id == that.id && Objects.equals(tipoDePagamento, that.tipoDePagamento);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id, tipoDePagamento);
	}

	public String getTipo() {
		return tipoDePagamento;
	}
	
	public void setTipo(String tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}
	
	public double getPercentual() {
		return percentualDesconto;
	}
	
	public void setPercentual(double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	
	  @Override
	    public String toString() {
	        DecimalFormat df = new DecimalFormat("#.#");
	        return "[" + id + "] Forma de pagamento: " + tipoDePagamento + " (" + df.format(percentualDesconto*100) + "% de desconto em pagamentos)";
	    }

}