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
	Duration diff;

	public ReservaRestaurante(String idCliente, LocalDateTime dataInicio, LocalDateTime dataFim, int qtdPessoas, Refeicao refeicao) {
		super(dataInicio, dataFim);
		diferencaEmDias = dataInicio.until(dataFim, ChronoUnit.DAYS);//.getDays();
		this.idCliente = idCliente;
		this.qtdPessoas = qtdPessoas;
		this.refeicao = refeicao;
		this.situacaoPagamento = "PENDENTE";
		//this.idReservaRestaurante = idReservaRestaurante;
		Duration diff = Duration.between(dataInicio, dataFim);
	} 


	public String situacaoPagamento(){
        return situacaoPagamento;
    }

//    valor reserva = num pessoas x quantidade de dias x refeicao.
//    public Double calculaPreco(){
//       long diferencaEmDias = dataInicio.until(dataFinal).getDays();
//        //double valorReserva = qtdPessoas * refeicao.getValorPorPessoa();
//        double valorReserva = valorReservaRestaurante * diferencaEmDias;
//        return valorReserva;
//    }
//
//    public double getValorReservaRestaurante() {
//        return valorReservaRestaurante;
//    }

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
