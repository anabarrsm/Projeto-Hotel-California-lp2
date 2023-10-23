package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservaRestaurante {
    private double valorReservaRestaurante;
    private String idCliente;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private Refeicao refeicao;
    private boolean pagamentoEfetuado;
    private int qtdPessoas;
    private int capacidadeRestaurante;
    private LocalTime horaInicial;
    private LocalTime horaFinal; 
    private int idReserva;
    
    public ReservaRestaurante(String idCliente, LocalDate dataInicio, LocalDate dataFinal, int qtdPessoas, Refeicao refeicao){
        this.idCliente = idCliente;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.refeicao = refeicao;
        this.idReserva = 0;
        // this.refeicao = refeicaoServida;
        //this.pagamentoEfetuado = pagamentoEfetuado;
       // this.horaInicial = refeicao.getHoraInicio(); 
       // this.horaFinal = refeicao.getHoraFim();
        
    	}
    	 
    
    //valorReservaRestaurante = qtdPessoas * refeicao.getValorPorPessoa();




	public long getIdReserva() {
		return idReserva;
	}


	public String situacaoPagamento(){
        if(this.pagamentoEfetuado){
            return "JÁ FOI PAGO.";
        }
        return "PENDENTE.";
    }

    // valor reserva = num pessoas x quantidade de dias x refeicao.
    public Double calculaPreco(){
        long diferencaEmDias = dataInicio.until(dataFinal).getDays();
        //double valorReserva = qtdPessoas * refeicao.getValorPorPessoa();
        double valorReserva = valorReservaRestaurante * diferencaEmDias;
        return valorReserva;
    }

    public double getValorReservaRestaurante() {
        return valorReservaRestaurante;
    }

    @Override
    public String toString(){
        return "[<id>]  Reserva de RESTAURANTE em favor de:\n " + idCliente + "\n" + "Detalhes da reserva: \n - Periodo: " + dataInicio + " ate " + dataFinal + "\n - Qtde. de Convidados: " + qtdPessoas + " pessoa(s) \n Refeicao incluida: " + refeicao + "VALOR TOTAL DA RESERVA: R$" + valorReservaRestaurante + " x" + dataInicial.until(dataFinal).getDays() + " (diarias)  => R$ " + calculaPreco() + "\n +  SITUACAO DO PAGAMENTO: " + situacaoPagamento() + "\n " ;
    }

    public void setIdReserva(int idReserva) {
    }
}
