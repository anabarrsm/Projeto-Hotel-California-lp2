package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

import java.util.Arrays;


/**
 * Classe que representa um Quarto Family, uma subclasse de Quarto.
 * 
 * Um Quarto Family é um tipo de quarto que pode acomodar um número específico
 * de pessoas (capacidade máxima). Ele possui um custo base e um custo adicional por pessoa.
 * Além disso, permite que os hóspedes façam pedidos específicos relacionados ao quarto.
 * 
 * @author Maria Helena
 */

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
		return "[" + idQuartoNum + "]" + "Quarto Family (custo basico: R$" + precoBase + "; adicional por pessoa: R$"
				+ precoPorPessoa + " >>> R$" + calcularDiaria() + " diária). Capacidade: " + this.qtdMaxPessoas + " pessoa(s)" + ". Pedidos: " + representarPedidos();
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

}