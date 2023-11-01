package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

import java.util.Arrays;

/**
 * Classe que representa um Quarto Family, uma subclasse de Quarto.
 * 
 * Um Quarto Family é um tipo de quarto que pode acomodar um número específico
 * de pessoas (capacidade máxima). Ele possui um custo base e um custo adicional
 * por pessoa. Além disso, permite que os hóspedes façam pedidos específicos
 * relacionados ao quarto.
 * 
 * @author Maria Helena
 */

public class QuartoFamily extends Quarto {

	private String[] pedidos;
	private int qtdMaxPessoas;

	public QuartoFamily(int idQuartoNum, double precoBase, double precoPorPessoa, String[] pedidos, int qtdMaxPessoas) {
		super(idQuartoNum, precoBase, precoPorPessoa);
		this.pedidos = pedidos;
		this.qtdMaxPessoas = qtdMaxPessoas;
	}

	@Override
	public String exibirQuarto() {
	    String formattedString;
	    if (pedidos.length == 0) {
	        formattedString = String.format("[%d] Quarto Family (custo básico: R$%.2f; adicional por pessoa: R$%.2f >>> R$%.2f diária). Capacidade: %02d pessoa(s). Pedidos: (nenhum)",
	            idQuartoNum, precoBase, precoPorPessoa, calcularDiaria(), qtdMaxPessoas);
	    } else {
	        formattedString = String.format("[%d] Quarto Family (custo básico: R$%.2f; adicional por pessoa: R$%.2f >>> R$%.2f diária). Capacidade: %02d pessoa(s). Pedidos: %s",
	            idQuartoNum, precoBase, precoPorPessoa, calcularDiaria(), qtdMaxPessoas, representarPedidos());
	    }
	    return formattedString;
	}
	
	@Override
	public double calcularDiaria() {
		double diaria = precoBase + (precoPorPessoa * qtdMaxPessoas);
		return diaria;
	}

	private String representarPedidos() {
		String output = "";
		if (this.pedidos.length == 0) {
			output = "(nenhum)";
		} else {
			output = Arrays.toString(pedidos);
		}
		return output;
	}

	@Override
	public int getQtdMaxPessoas() {
		return qtdMaxPessoas;
	}
	

	@Override
	public String toString() {
		return "QuartoFamily [idQuartoNum=" + idQuartoNum + "]";
	}

}