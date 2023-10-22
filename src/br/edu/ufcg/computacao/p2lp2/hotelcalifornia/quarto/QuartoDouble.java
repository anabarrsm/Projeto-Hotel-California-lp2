package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

import java.util.Arrays;

/**
 * Classe que representa um Quarto Double, uma subclasse de Quarto.
 * 
 * Um Quarto Double é um tipo de quarto projetado para acomodar até duas pessoas.
 * Ele possui um custo base e um custo adicional por pessoa, bem como a capacidade
 * de incluir pedidos específicos relacionados ao quarto.
 * 
 * @author Maria Helena
 */

public class QuartoDouble extends Quarto {

	private String[] pedidos;

	public QuartoDouble(String idAutenticacao, int idQuartoNum, double precoPorPessoa, double precoBase,
			String[] pedidos) {
		super(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
		this.pedidos = pedidos;
	}

	@Override
	public String exibirQuarto() {
		return "[" + idQuartoNum + "]" + "Quarto Double (custo basico: R$" + precoBase + "; adicional por pessoa: R$"
				+ precoPorPessoa + " >>> R$" + calcularDiaria() + " diária). Pedidos: " + representarPedidos(); 
	}

	@Override
	public double calcularDiaria() {
		double diaria = precoBase + (getQtdMaxPessoas() * precoPorPessoa);
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
		return 2;
	} 
} 