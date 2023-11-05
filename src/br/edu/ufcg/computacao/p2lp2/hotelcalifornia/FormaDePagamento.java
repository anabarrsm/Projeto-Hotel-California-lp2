package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.text.DecimalFormat;
import java.util.Objects;

public class FormaDePagamento {
	private String tipoDePagamento;
	private double percentualDesconto;
	private int id;
	private static int idcont = 1;
	
	public FormaDePagamento(int id, String tipoDePagamento, double percentualDesconto) {
		this.id = idcont++;
		this.tipoDePagamento = tipoDePagamento;
		this.percentualDesconto = percentualDesconto;
	}
	
	public int getId() { 
		return id;
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
		 return "[" + id + "] Forma de pagamento: " + tipoDePagamento + " (" + percentualDesconto + "% de desconto em pagamentos)";
	 }

}