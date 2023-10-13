package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

import java.util.Arrays;

public class QuartoDouble extends Quarto {

	private String[] pedidos;

	public QuartoDouble(String idAutenticacao, int idQuartoNum, double precoPorPessoa, double precoBase,
			String[] pedidos) {
		super(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
		this.pedidos = pedidos;
	}

	@Override
	public String exibirQuarto() {
		return "[" + idQuartoNum + "]" + "Quarto Single (custo basico: R$" + precoBase + "; adicional por pessoa: R$"
				+ precoPorPessoa + " >>> R$" + calcularDiaria() + " diária). Pedidos: " + representarPedidos(); 
	}

	@Override
	public double calcularDiaria() {
		double diaria = precoBase + (2 * precoPorPessoa);
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
} 
