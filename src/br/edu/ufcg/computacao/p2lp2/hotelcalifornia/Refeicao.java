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

    public Refeicao(String tipo, String titulo, LocalTime horaInicio, LocalTime horaFim, double valorPorPessoa, boolean disponivel){
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
            throw new NullPointerException("TITULO DA REFEIÇÃO NÃO PODE SER NULO");
        }

        if(horaInicio == null) {
            throw new NullPointerException("HORÁRIO INICIAL NÃO DEVE SER NULO");
        }

        if(horaFim == null) {
            throw new NullPointerException("HORÁRIO FINAL NÃO DEVE SER NULO");
        }

        if(horaFim.isBefore(horaInicio)){
            throw new IllegalArgumentException("HORÁRIO FINAL DEVE SER POSTERIOR AO HORÁRIO INICIAL");
        }

        this.idRefeicao = 0;
        //this.tipoRefeicao = tipo;
        this.tituloRefeicao = titulo;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.valorPorPessoa = valorPorPessoa;
        this.refeicaoDisponivel = disponivel;
    }

    public long getIdRefeicao() {
		return idRefeicao;
	}

	public void setIdRefeicao(long idRefeicao) {
		this.idRefeicao = idRefeicao;
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public double getValorPorPessoa() {
        return valorPorPessoa;
    }

    @Override
    public String toString(){
        //[<id>] <tipoRefeicao>: <titulo> (<inicio> as <fim>). Valor por pessoa: R$<valorPorPessoa>. <ativa?>
        return "[" + idRefeicao + "] " + tipoRefeicao + ": " + tituloRefeicao + " (" + horaInicio + " as " + horaFim + "). Valor por pessoa: R$" + valorPorPessoa + ". " + isRefeicaoDisponivel() + ".";
    }



}
