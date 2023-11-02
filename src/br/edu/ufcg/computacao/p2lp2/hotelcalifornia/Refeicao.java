package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

	public Refeicao(String tipoRefeicaoFormatado, String titulo, LocalTime horarioInicio, LocalTime horarioFinal,
			double valor, boolean refeicaoDisponivel) {
		this.idRefeicao = idRefeicao;
		this.titulo = titulo;
		this.tipoRefeicao = tipoRefeicaoFormatado;
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

	public String getTitulo() {
		return titulo;
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
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h'mm");
		        String horario = horarioInicio.format(formatter) + " as " + horarioFinal.format(formatter);
		        return String.format("[%d] %s: %s (%s). Valor por pessoa: R$%.2f. %s",
		                idRefeicao, tipoRefeicao, titulo, horario, valor, refeicaoDisponivel ? "VIGENTE" : "INDISPONIVEL");
	}
}