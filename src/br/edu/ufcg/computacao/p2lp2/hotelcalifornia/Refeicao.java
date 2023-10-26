package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalTime;

/**
 * Classe que cria uma refeição no sistema.
 * 
 * @author José Lucas Silva Palmeira
 */

public class Refeicao {
	private long idRefeicao;

	private String tipoRefeicao;
	private String titulo;

	private LocalTime horarioInicio;
	private LocalTime horarioFinal;

	private double valor;

	private boolean refeicaoDisponivel;

	public Refeicao(String tipoRefeicao, String titulo, LocalTime horarioInicio, LocalTime horarioFinal, double valor,
			boolean refeicaoDisponivel) {
		this.idRefeicao = idRefeicao;
		this.titulo = titulo;
		this.tipoRefeicao = tipoRefeicao;
		this.horarioInicio = horarioInicio;
		this.horarioFinal = horarioFinal;
		this.valor = valor;
		this.refeicaoDisponivel = refeicaoDisponivel;

	}
	
	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}
	
	public LocalTime getHorarioFinal() {
		return horarioFinal;
	}

	public long getIdRefeicao() {
		return idRefeicao;
	}

	public void setIdRefeicao(long idRefeicao) {
		this.idRefeicao = idRefeicao;
	}

	public String isRefeicaoDisponivel() {
		if (refeicaoDisponivel) {
			return "VIGENTE";
		}
		return "INDISPONIVEL";
	}

	public void setRefeicaoDisponivel(boolean isDisponivel) {
		refeicaoDisponivel = isDisponivel;
	}

	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public double getValor() {
		return valor;
	}

	public void setHorarioFinal(LocalTime horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public void setValorPorPessoa(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		// [<id>] <tipoRefeicao>: <titulo> (<inicio> as <fim>). Valor por pessoa:
		// R$<valorPorPessoa>. <ativa?>
		return "[" + idRefeicao + "] " + tipoRefeicao + ": " + titulo + " (" + horarioInicio + " as " + horarioFinal
				+ "). Valor por pessoa: R$" + valor + ". " + (refeicaoDisponivel ? "VIGENTE." : "INDISPONIVEL.");
	}

}