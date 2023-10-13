package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalTime;

public class Refeicao {
    private String idRefeicao;
    private String tipoRefeicao;
    private String tituloRefeicao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private double valorPorPessoa;
    private boolean refeicaoDisponivel;

    public Refeicao(String idRefeicao, String tipo, String titulo, LocalTime horaInicio, LocalTime horaFim, double valorPorPessoa, boolean disponivel){
        this.idRefeicao = idRefeicao;
        this.tipoRefeicao = tipo;
        this.tituloRefeicao = titulo;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.valorPorPessoa = valorPorPessoa;
        this.refeicaoDisponivel = disponivel;
    }

    public void setValorPorPessoa(double valor){
        valorPorPessoa = valor;
    }

    public void setRefeicaoDisponivel(boolean isDisponivel){
        refeicaoDisponivel = isDisponivel;
    }
    public void setHoraInicio(LocalTime horaInicio){
        this.horaInicio = horaInicio;
    }

    public void setHoraFim(LocalTime horaFim){
        this.horaFim = horaFim;
    }
    public boolean isRefeicaoDisponivel(){
        return this.refeicaoDisponivel;
    }

    @Override
    public String toString(){
        //[<id>] <tipoRefeicao>: <titulo> (<inicio> as <fim>). Valor por pessoa: R$<valorPorPessoa>. <ativa?>
        return "[" + idRefeicao + "]" + tipoRefeicao + ": " + tituloRefeicao + "(" + horaInicio + " as " + horaFim + "). Valor por pessoa: R$ " + valorPorPessoa + ". " + refeicaoDisponivel + ".";
    }

}
