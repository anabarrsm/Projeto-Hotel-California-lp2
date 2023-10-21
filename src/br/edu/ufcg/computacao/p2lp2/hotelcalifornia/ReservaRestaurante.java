package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRestaurante {
    private double valorReservaRestaurante;
    private String clienteCadastrado;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Refeicao refeicao;
    private boolean pagamentoEfetuado;
    private int qtdPessoas;
    private int capacidadeRestaurante;
    private LocalTime horaInicial;
    private LocalTime horaFinal;

    public ReservaRestaurante(String clienteCadastrado, LocalDate dataInicial, LocalDate dataFinal, int qtdPessoas, Refeicao refeicaoServida, boolean pagamentoEfetuado){
        capacidadeRestaurante = 50;
        if(qtdPessoas<= capacidadeRestaurante){
            this.qtdPessoas = qtdPessoas;
        }else{
            throw new RuntimeException("QUANTIDADE DE PESSOAS CONVIDADAS NÃO DEVE EXCEDER A CAPACIDADE DO RESTAURANTE");
        }
        valorReservaRestaurante = qtdPessoas * refeicao.getValorPorPessoa();
        this.clienteCadastrado = clienteCadastrado;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.refeicao = refeicaoServida;
        this.pagamentoEfetuado = pagamentoEfetuado;
        this.horaInicial = refeicao.getHoraInicio();
        this.horaFinal = refeicao.getHoraFim();
    }

    public String situacaoPagamento(){
        if(this.pagamentoEfetuado){
            return "JÁ FOI PAGO.";
        }
        return "PENDENTE.";
    }

    // valor reserva = num pessoas x quantidade de dias x refeicao.
    public Double calculaPreco(){
        long diferencaEmDias = dataInicial.until(dataFinal).getDays();
        //double valorReserva = qtdPessoas * refeicao.getValorPorPessoa();
        double valorReserva = valorReservaRestaurante * diferencaEmDias;
        return valorReserva;
    }

    public double getValorReservaRestaurante() {
        return valorReservaRestaurante;
    }

    @Override
    public String toString(){
        return "[<id>]  Reserva de RESTAURANTE em favor de:\n " + clienteCadastrado + "\n" + "Detalhes da reserva: \n - Periodo: " + dataInicial + " ate " + dataFinal + "\n - Qtde. de Convidados: " + qtdPessoas + " pessoa(s) \n Refeicao incluida: " + refeicao + "VALOR TOTAL DA RESERVA: R$" + valorReservaRestaurante + " x" + dataInicial.until(dataFinal).getDays() + " (diarias)  => R$ " + calculaPreco() + "\n +  SITUACAO DO PAGAMENTO: " + situacaoPagamento() + "\n " ;
    }
}
