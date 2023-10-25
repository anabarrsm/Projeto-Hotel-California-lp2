package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;

public class ReservaQuartoSingle extends Reserva {

	private long idReserva;
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private RefeicaoController refeicaoController;

	public ReservaQuartoSingle(String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim,
			String[] idRefeicoes) {
		super(idCliente, numQuarto, dataInicio, dataFim, idRefeicoes);

		this.idReserva = 0;
		this.usuarioController = usuarioController;
		this.quartoController = quartoController;
		this.refeicaoController = refeicaoController;

	}

	public long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(long idReserva) { 
		this.idReserva = idReserva;
	}

	@Override
	public double calcularVQR() {

		double valorBasico = quartoController.getQuartos().get(numQuarto).getPrecoBase();
		double valorPorPessoa = quartoController.getQuartos().get(numQuarto).getPrecoPorPessoa();
		int qtdHospedes = 1;
		long diferencaEmDias = Duration.between(dataInicio, dataFim).toDays();
		double numeroDiarias = Math.ceil(diferencaEmDias);
		double somaRefeicoes = 0;
		
		for (String refeicao : idRefeicoes) {
			double valorPorPessoaRefeicao = extrairValorPorPessoa(refeicao);
			somaRefeicoes += valorPorPessoaRefeicao;
		}

		double valorReservaQuarto = numeroDiarias * (valorBasico + qtdHospedes * valorPorPessoa)
				+ numeroDiarias * qtdHospedes * somaRefeicoes;

		return valorReservaQuarto;
	}

	private double extrairValorPorPessoa(String refeicao) {
	    String[] partes = refeicao.split("Valor por pessoa: R\\$");
	    if (partes.length >= 2) {
	        String valorPorPessoaString = partes[1].split("\\.")[0]; // Extrair o valor por pessoa da string
	        try {
	            return Double.parseDouble(valorPorPessoaString);
	        } catch (NumberFormatException e) {
	            // Lidar com erros de formatação
	        }
	    }
	    return 0; // Valor padrão se não conseguir extrair o valor
	}


	@Override
	public String exibirReserva() {


		String cliente = "";
		ArrayList<Usuario> usuariosCadastrados = this.usuarioController.getUsuarios();
		for (int i = 0; i < usuariosCadastrados.size(); i++) {
			if (usuariosCadastrados.get(i).equals(idCliente)) {
				cliente = usuariosCadastrados.get(i).toString();
			}
		}
		
		Quarto quarto = quartoController.getQuartos().get(numQuarto);
		
		
		double somaRefeicoes = 0.0;
		for (String refeicao : idRefeicoes) {
			double valorPorPessoaRefeicao = extrairValorPorPessoa(refeicao);
			somaRefeicoes += valorPorPessoaRefeicao;
		}
		
		long diferencaEmDias = Duration.between(dataInicio, dataFim).toDays();
		double numeroDiarias = Math.ceil(diferencaEmDias);

		 
		double total = quarto.getPrecoBase() + quarto.getPrecoPorPessoa() + somaRefeicoes + numeroDiarias;
		
		String saida = "[" + idReserva + "]" + "Reserva de quarto em favor de: "
				 + "\n" + "-" + cliente 
				 + "\n" + "Detalhes da instalação:"
				 + "\n" + quarto 
				 + "\n" + "Detalhes da Reserva:"
				 + "\n" + "-Periodo: " + dataInicio + " até " + dataFim
				 + "\n" + "- No Hospedes: 01 pessoa(s)" 
				 + "\n" + "- Refeiceos incluidas: " + Arrays.toString(idRefeicoes)
				 + "\n" + "VALOR TOTAL DA RESERVA: R$" + quarto.getPrecoBase() + quarto.getPrecoPorPessoa() + somaRefeicoes + " x" + numeroDiarias +" (diarias) => R$" + total
				 + "\n" + "SITUCAO DO PAGAMENTO: " + getSituacaDoPagamento();

		return saida;
	}
	
	public String toString() {
	  return "Reserva [id=" + idReserva + ", cliente=" + idCliente + ", quarto=" + numQuarto + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + "]";
	}
}
