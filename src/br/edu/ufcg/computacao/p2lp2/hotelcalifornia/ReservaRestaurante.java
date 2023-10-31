package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservaRestaurante {
	private long idReservaRestaurante;
	
	private String idCliente;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private int qtdPessoas;
	private String idRefeicao;
	private String situacaoPagamento;
	Duration diff;

	public ReservaRestaurante(String idCliente, LocalDateTime dataIncio, LocalDateTime dataFim, int qtdPessoas, String idRefeicao) {
		this.idCliente = idCliente;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.qtdPessoas = qtdPessoas;
		this.idRefeicao = idRefeicao;
		this.situacaoPagamento = situacaoPagamento;
		this.idReservaRestaurante = idReservaRestaurante;
		Duration diff = Duration.between(dataIncio, dataFim);
	}


	public String situacaoPagamento(){
        if(this.pagamentoEfetuado){
            return "JÁ FOI PAGO.";
        }
        return "PENDENTE.";
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
   public String toString(){
        return "[<id>]  Reserva de RESTAURANTE em favor de:\n " + idCliente + "\n" + "Detalhes da reserva: \n - Periodo: " + dataInicio + " ate " + dataFim + "\n - Qtde. de Convidados: " + qtdPessoas + " pessoa(s) \n Refeicao incluida: " + idRefeicao + "VALOR TOTAL DA RESERVA: R$" + valorReservaRestaurante + " x" + diff + " (diarias)  => R$ " + calculaPreco() + "\n +  SITUACAO DO PAGAMENTO: " + situacaoPagamento() + "\n " ;
    }

}
