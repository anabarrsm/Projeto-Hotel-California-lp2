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

	public ReservaQuartoSingle(String idAtutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes) {
		super(idAtutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes);
	}
 
	@Override
	public String exibirReserva() {
		String cliente = this.usuarioController.exibirUsuario(idCliente);
		String quartoSingle = this.quartoController.exibirQuarto(numQuarto);

		String saida = 
		return saida;
	}

}
