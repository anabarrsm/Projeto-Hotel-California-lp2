package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

import java.text.DecimalFormat;

/**
 * Classe que representa um Quarto Single, uma subclasse de Quarto.
 * 
 * Um Quarto Single é um tipo de quarto que acomoda uma única pessoa. Ele possui
 * um custo base e um custo adicional por pessoa, e sua diária é calculada
 * somando o custo base ao custo adicional por pessoa multiplicado pelo número
 * de pessoas.
 * 
 * @author Maria Helena
 */

public class QuartoSingle extends Quarto {

	public QuartoSingle(int idQuartoNum, double precoBase, double precoPorPessoa) {
		super(idQuartoNum, precoBase, precoPorPessoa);

	}

	@Override
	public String exibirQuarto() {

		String saida = String.format("[%d] Quarto Single (custo básico: R$%.2f; por pessoa R$%.2f >>> R$%.2f diária)",
				idQuartoNum, precoBase, precoPorPessoa, calcularDiaria());

		return saida;
	}

	@Override
	public double calcularDiaria() {
		double diaria = (precoBase + (getQtdMaxPessoas() * precoPorPessoa));
		return diaria;
	}

	@Override
	public int getQtdMaxPessoas() {
		return 1;
	}

	@Override
	public String toString() {
		return "QuartoSingle [idQuartoNum=" + idQuartoNum + "]";
	}

}
