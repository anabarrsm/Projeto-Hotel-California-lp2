package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;

public class ReservaRestaurante extends Reserva {
	private long idReservaRestaurante;
	private long diferencaEmDias;
	private String idCliente;
	private int qtdPessoas;
	private Refeicao refeicao;
	private String situacaoPagamento;
	private boolean isPago;
	Duration diff;

	public ReservaRestaurante(String idCliente, LocalDateTime dataInicio, LocalDateTime dataFim, int qtdPessoas, Refeicao refeicao) {
		super(dataInicio, dataFim);
		diferencaEmDias = dataInicio.until(dataFim, ChronoUnit.DAYS);//.getDays();
		this.idCliente = idCliente;
		this.qtdPessoas = qtdPessoas;
		this.refeicao = refeicao;
		this.situacaoPagamento = "PENDENTE";
		this.isPago = false;
		//this.idReservaRestaurante = idReservaRestaurante;
		Duration diff = Duration.between(dataInicio, dataFim);
	} 


	public String situacaoPagamento(){
        return situacaoPagamento;
    }
	
	public void marcarComoPaga() {
        this.situacaoPagamento = "PAGA";
        this.isPago = true;
    }
	
	public boolean getIsPago() {
		return isPago;
	}

	public long getIdReservaRestaurante() {
		return idReservaRestaurante;
	}


	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}


	public void setIdReservaRestaurante(long idReservaRestaurante) {
		this.idReservaRestaurante = idReservaRestaurante;
		
	
	}


	@Override
	public double calculaValor() {
		//valor reserva = num pessoas x quantidade de dias x refeicao.
        double valorReserva = qtdPessoas * refeicao.getValor() * diferencaEmDias;
        return valorReserva;
    }


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
