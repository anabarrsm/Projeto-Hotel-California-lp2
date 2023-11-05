package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.text.DecimalFormat;
import java.util.Objects;

public abstract class FormaDePagamento {
	protected String tipoDePagamento;
	protected double percentualDesconto;
	protected int id;
	protected static int idcont = 1;
	
	public FormaDePagamento(String tipoDePagamento, double percentualDesconto) {
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
	
	public abstract void efetuarPagamento(double valorReserva, int qtdeParcelas);
	
	public abstract String exibirPagamento(String saida);

}