package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservaRestaurante {
	private String idRRefeicao;
	private String idCliente;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private int qtdPessoas;
	private String idRefeicao;
	private String situacaoPagamento;

	public ReservaRestaurante(String idCliente, LocalDateTime dataIncio, LocalDateTime dataFim, int qtdPessoas,
			String idRefeicao) {
		this.idCliente = idCliente;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.qtdPessoas = qtdPessoas;
		this.idRefeicao = idRefeicao;
		this.situacaoPagamento = situacaoPagamento;
	}

// 
//	public long getIdReserva() {
//		return idReserva;
//	}
//
//
//	public String situacaoPagamento(){
//        if(this.pagamentoEfetuado){
//            return "JÁ FOI PAGO.";
//        }
//        return "PENDENTE.";
//    }
//
//    // valor reserva = num pessoas x quantidade de dias x refeicao.
//    public Double calculaPreco(){
//        long diferencaEmDias = dataInicio.until(dataFinal).getDays();
//        //double valorReserva = qtdPessoas * refeicao.getValorPorPessoa();
//        double valorReserva = valorReservaRestaurante * diferencaEmDias;
//        return valorReserva;
//    }
//
//    public double getValorReservaRestaurante() {
//        return valorReservaRestaurante;
//    }

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

//	@Override
//    public String toString(){
//        return "[<id>]  Reserva de RESTAURANTE em favor de:\n " + idCliente + "\n" + "Detalhes da reserva: \n - Periodo: " + dataInicio + " ate " + dataFim + "\n - Qtde. de Convidados: " + qtdPessoas + " pessoa(s) \n Refeicao incluida: " + refeicao + "VALOR TOTAL DA RESERVA: R$" + valorReservaRestaurante + " x" + dataInicio.until(dataFinal).getDays() + " (diarias)  => R$ " + calculaPreco() + "\n +  SITUACAO DO PAGAMENTO: " + situacaoPagamento() + "\n " ;
//    }
//
//    public void setIdReserva(int idReserva) {
//    }
}
