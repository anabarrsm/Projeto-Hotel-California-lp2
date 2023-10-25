package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

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

	public QuartoSingle(int idQuartoNum, double precoPorPessoa, double precoBase) {
		super(idQuartoNum, precoPorPessoa, precoBase);

	}

	@Override
	public String exibirQuarto() {
		return "[" + idQuartoNum + "]" + " Quarto Single (custo basico: R$" + precoBase + "; adicional por pessoa: R$"
				+ precoPorPessoa + " >>> R$" + calcularDiaria() + " diária)";
	}

	@Override
	public double calcularDiaria() {
		double diaria = precoBase + (getQtdMaxPessoas() * precoPorPessoa);
		return diaria;
	}

	@Override
	public int getQtdMaxPessoas() {
		return 1;
	}

}
