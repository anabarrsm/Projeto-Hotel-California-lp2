package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaAuditorio {
    private Usuario usuario;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private double valorPessoa;
    private boolean jaFoiPago;
    private int qtdConvidados;
    private int capacidadeAuditorio;

    public ReservaAuditorio(Usuario usuario, LocalDate dataInicial, LocalDate dataFinal, double valorPessoa, int qtdConvidados){
        capacidadeAuditorio = 150;
        if(valorPessoa <= capacidadeAuditorio){
            this.qtdConvidados = qtdConvidados;
        }else{
            throw new IllegalArgumentException("A CAPACIDADE MAXIMA É 150!");
        }
        this.usuario = usuario;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.valorPessoa = valorPessoa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getQtdConvidados(){
        return this.qtdConvidados;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public LocalDateTime getHoraFim() {
        return horaFim;
    }

}
