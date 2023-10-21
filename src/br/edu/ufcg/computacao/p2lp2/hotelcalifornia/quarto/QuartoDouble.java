package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

import java.util.Arrays;

public class QuartoDouble extends Quarto {

	private String[] pedidos;

	public QuartoDouble(String idAutenticacao, int idQuartoNum, double precoPorPessoa, double precoBase,
			String[] pedidos) {
		super(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
		this.pedidos = pedidos;
		qtdMaxPessoas = 2;
	}

	@Override
	public String exibirQuarto() {
		return "[" + idQuartoNum + "]" + "Quarto Double (custo basico: R$" + precoBase + "; adicional por pessoa: R$"
				+ precoPorPessoa + " >>> R$" + calcularDiaria() + " diária). Pedidos: " + representarPedidos(); 
	}

	@Override
	public double calcularDiaria() {
		double diaria = precoBase + (qtdMaxPessoas * precoPorPessoa);
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