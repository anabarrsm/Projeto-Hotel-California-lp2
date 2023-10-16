package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRestaurante {
    private String clienteCadastrado;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Refeicao refeicao;
    private boolean pagamentoEfetuado;

    public String situacaoPagamento(){
        if(this.pagamentoEfetuado){
            return "JÁ FOI PAGO.";
        }
        return "PENDENTE.";
    }
}
