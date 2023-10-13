package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

import java.util.Arrays;

public class QuartoFamily extends Quarto {

	private String[] pedidos;
	private int qtdMaxPessoas;

	public QuartoFamily(String idAutenticacao, int idQuartoNum, double precoPorPessoa, double precoBase,
			String[] pedidos, int qtdMaxPessoas) {
		super(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
		this.pedidos = pedidos;
		this.qtdMaxPessoas = qtdMaxPessoas;
	}
 
	@Override
	public String exibirQuarto() {
		return "[" + idQuartoNum + "]" + "Quarto Single (custo basico: R$" + precoBase + "; adicional por pessoa: R$"
				+ precoPorPessoa + " >>> R$" + calcularDiaria() + " diária). Capacidade: " + this.qtdMaxPessoas + ". Pedidos: " + representarPedidos();
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

}