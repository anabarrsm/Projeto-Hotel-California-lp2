package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;

public class ReservaRestaurante extends Reserva {
	private long idReservaRestaurante;
	
	private String idCliente;
	private int qtdPessoas;
	private String idRefeicao;
	private String situacaoPagamento;
	Duration diff;

	public ReservaRestaurante(String idCliente, LocalDateTime dataInicio, LocalDateTime dataFim, int qtdPessoas, String idRefeicao) {
		super(dataInicio, dataFim);
		
		this.idCliente = idCliente;
		this.qtdPessoas = qtdPessoas;
		this.idRefeicao = idRefeicao;
		this.situacaoPagamento = situacaoPagamento;
		this.idReservaRestaurante = idReservaRestaurante;
		Duration diff = Duration.between(dataInicio, dataFim);
	} 


//	public String situacaoPagamento(){
//        if(this.pagamentoEfetuado){
//            return "JÁ FOI PAGO."; 
//        }
//        return "PENDENTE.";
//    }

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
	public int calculaValor() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
