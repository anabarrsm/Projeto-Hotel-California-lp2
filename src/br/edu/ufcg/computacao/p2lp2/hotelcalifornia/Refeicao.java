package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalTime;

/**
 * Classe que cria uma refeição no sistema.
 * @author José Lucas Silva Palmeira
 */

public class Refeicao {
    private long idRefeicao;
    private String tipoRefeicao;
    private String tituloRefeicao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private double valorPorPessoa;
    private boolean refeicaoDisponivel;

    public Refeicao(long idRefeicao, String tipo, String titulo, LocalTime horaInicio, LocalTime horaFim, double valorPorPessoa, boolean disponivel){
        switch (tipo){
            case "Café-da-manhã":
                tipoRefeicao = tipo;
                break;
            case "Almoço":
                tipoRefeicao = tipo;
                break;
            case "Jantar":
                tipoRefeicao = tipo;
                break;
            default:
                throw new IllegalArgumentException("NÃO É UM TIPO POSSÍVEL");
        }
        //if(tipo == null) {
        //    throw new NullPointerException("TIPO DE REFEIÇÃO NÃO PODE SER NULO");
        //}

        if(titulo == null)  {
            throw new NullPointerException("TITULO DE REFEIÇÃO NÃO PODE SER NULO");
        }

        if(horaInicio == null) {
            throw new NullPointerException("REFEIÇÃO DEVE TER HORA DE INICIO");
        }

        if(horaFim == null) {
            throw new NullPointerException("REFEIÇÃO DEVE TER HORA DE FIM");
        }

        this.idRefeicao = idRefeicao;
        //this.tipoRefeicao = tipo;
        this.tituloRefeicao = titulo;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.valorPorPessoa = valorPorPessoa;
        this.refeicaoDisponivel = disponivel;
    }

    public long getId(){
        return this.idRefeicao;
    }

    // sets para valores que podem ser alterados nos controllers, de acordo com o metódo alterarRefeição.
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
    public String isRefeicaoDisponivel(){
        if(refeicaoDisponivel){
            return "VIGENTE";
        }
        return "INDISPONIVEL";
    }

    @Override
    public String toString(){
        //[<id>] <tipoRefeicao>: <titulo> (<inicio> as <fim>). Valor por pessoa: R$<valorPorPessoa>. <ativa?>
        return "[" + idRefeicao + "]" + tipoRefeicao + ": " + tituloRefeicao + " (" + horaInicio + " as " + horaFim + "). Valor por pessoa: R$ " + valorPorPessoa + ". " + isRefeicaoDisponivel() + ".";
    }

}
