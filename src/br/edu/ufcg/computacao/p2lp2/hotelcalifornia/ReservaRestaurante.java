package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRestaurante {
    private String clienteCadastrado;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Refeicao refeicao;
    private boolean pagamentoEfetuado;
    private int qtdPessoas;
    private int capacidadeRestaurante;

    public ReservaRestaurante(String clienteCadastrado, LocalDate dataInicial, LocalDate dataFinal, int qtdPessoas, Refeicao refeicaoServida, boolean pagamentoEfetuado){
        capacidadeRestaurante = 50;
        this.clienteCadastrado = clienteCadastrado;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;

        if(qtdPessoas<= capacidadeRestaurante){
            this.qtdPessoas = qtdPessoas;
        }else{
            throw new RuntimeException("QUANTIDADE DE PESSOAS CONVIDADAS NÃO DEVE EXCEDER A CAPACIDADE DO RESTAURANTE");
        }
        this.refeicao = refeicaoServida;
        this.pagamentoEfetuado = pagamentoEfetuado;
    }

    public String situacaoPagamento(){
        if(this.pagamentoEfetuado){
            return "JÁ FOI PAGO.";
        }
        return "PENDENTE.";
    }
}
