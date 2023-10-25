package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;


import java.time.LocalDateTime;
import java.util.Arrays;

public class ReservaQuartoFamily extends Reserva {
	

	private String[] pedidos;
	private int numPessoas;
	private long idReserva;

	public ReservaQuartoFamily(String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] refeicoes, String[] pedidos, int numPessoas) {
		
		super(idCliente, numQuarto, dataInicio, dataFim, refeicoes);
		this.pedidos = pedidos;
		this.numPessoas = numPessoas;
		this.idReserva = idReserva;
	}

	public long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(long idReserva) {
		this.idReserva = idReserva;
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

	@Override
	public String toString() {
		return "Reserva [id=" + idReserva + "]" +  "cliente=" + idCliente +  "quarto=" + numQuarto + " dataInicio=" + dataInicio + ", dataFim=" + dataFim + "] pedidos=" + Arrays.toString(pedidos) + ", numPessoas=" + numPessoas + "]";
	}
	}