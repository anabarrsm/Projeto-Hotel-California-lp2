package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;

import java.time.LocalDateTime;

public class ReservaQuartoDouble extends Reserva {
	private String[] pedidos;

	public ReservaQuartoDouble(String idAtutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] refeicoes, String[] pedidos) {
		
		super(idAtutenticacao, idCliente, numQuarto, dataInicio, dataFim, refeicoes);
		this.pedidos = pedidos;

	}

	@Override
	public String exibirReserva() {
		
		return null;
	}

	@Override
	public double calcularVQR() {
		// TODO Auto-generated method stub
		return 0;
	}

}
