package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;


import java.time.LocalDateTime;

public class ReservaQuartoFamily extends Reserva {
	private String[] pedidos;
	private int numPessoas;

	public ReservaQuartoFamily(String idAtutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] refeicoes, String[] pedidos, int numPessoas) {
		
		super(idAtutenticacao, idCliente, numQuarto, dataInicio, dataFim, refeicoes);
		this.pedidos = pedidos;
		this.numPessoas = numPessoas;
	}

	@Override
	public String exibirReserva() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calcularVQR() {
		// TODO Auto-generated method stub
		return 0;
	}

}