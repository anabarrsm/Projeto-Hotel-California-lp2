package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservaRestaurante {
	private long idReservaRestaurante;
	
	private String idCliente;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int qtdPessoas;
	private String idRefeicao;
	private String situacaoPagamento;
	private LocalTime horaInicio;
	private LocalTime horaFim;


	public ReservaRestaurante(String idCliente, LocalDate dataIncio, LocalDate dataFim, int qtdPessoas, String idRefeicao, LocalTime horaInicio, LocalTime horaFim) {
		this.idCliente = idCliente;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.qtdPessoas = qtdPessoas;
		this.idRefeicao = idRefeicao;
		this.situacaoPagamento = situacaoPagamento;
		this.idReservaRestaurante = idReservaRestaurante;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
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


	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}


	public void setIdReservaRestaurante(long idReservaRestaurante) {
		this.idReservaRestaurante = idReservaRestaurante;
		
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	@Override
   public String toString(){
        return "[<id>]  Reserva de RESTAURANTE em favor de:\n " + idCliente + "\n" + "Detalhes da reserva: \n - Periodo: " + dataInicio + " ate " + dataFim + "\n - Qtde. de Convidados: " + qtdPessoas + " pessoa(s) \n Refeicao incluida: " + idRefeicao + "VALOR TOTAL DA RESERVA: R$" + valorReservaRestaurante + " x" + dataInicio.until(dataFim).getDays() + " (diarias)  => R$ " + calculaPreco() + "\n +  SITUACAO DO PAGAMENTO: " + situacaoPagamento() + "\n " ;
    }

}
