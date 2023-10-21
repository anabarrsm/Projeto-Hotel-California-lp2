package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;

import java.time.Duration;
import java.time.LocalDateTime;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoSingle;

public class ReservaQuartoSingle extends Reserva {
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private RefeicaoController refeicaoController;

	public ReservaQuartoSingle(String idAtutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes) {
		super(idAtutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes);

		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController();
		this.refeicaoController = new RefeicaoController();
		;
	}

	@Override
	public String exibirReserva() {
		String cliente = this.usuarioController.exibirUsuario(idCliente);
		String quartoSingle = this.quartoController.exibirQuarto(numQuarto);

		String saida = "Detalhes da Instalação: " + "\n" +
				"[" + numQuarto + "]" + quartoSingle + "\n"
				+ "Detalhes da Reserva:\n" +
				"- Periodo: " + dataInicio + "até" + dataFim
				+ "\n- No. Hospedes: 01 pessoa(s)" + "\n" + 
				"- Refeicoes incluidas: [" + idRefeicoes + "] \n" ;
		return saida;
	}

}
