package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

public class QuartoSingle extends Quarto {

	public QuartoSingle(String idAutenticacao, int idQuartoNum, double precoPorPessoa, double precoBase) {
		super(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
	}

	@Override
	public String exibirQuarto() {
		return "[" + idQuartoNum + "]" + "Quarto Single (custo basico: R$" + precoBase + "; adicional por pessoa: R$"
				+ precoPorPessoa + " >>> R$" + calcularDiaria() + " diária)";
	}

	@Override
	double calcularDiaria() {
		double diaria = precoBase + precoPorPessoa;
		return diaria;
	}

}


