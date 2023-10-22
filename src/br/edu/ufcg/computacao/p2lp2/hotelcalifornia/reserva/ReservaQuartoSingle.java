package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;


public class ReservaQuartoSingle extends Reserva {

	public ReservaQuartoSingle(String idAtutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes) {
		super(idAtutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes);
	}

	@Override
	public String exibirReserva() {
		// TODO Auto-generated method stub
		return null;
	}
 
	

}
