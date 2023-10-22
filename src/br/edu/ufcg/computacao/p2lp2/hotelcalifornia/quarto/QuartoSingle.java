package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto;

public class QuartoSingle extends Quarto {
	

	public QuartoSingle(String idAutenticacao, int idQuartoNum, double precoPorPessoa, double precoBase) {
		super(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
		
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


 