package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AreaComum {
	
	private long id;
	private String titulo;
	private String tipo;
	private LocalTime horarioInicio;
	private LocalTime horarioFim;
	private double valorPessoa;
	private String disponivel;
	private int qtdMaxPessoas;
	
	public AreaComum(String titulo, String tipo, LocalTime horarioInicio, LocalTime horarioFim, double valorPessoa,
			boolean disponivel, int qtdMaxPessoas) {
		
		this.titulo = titulo;
		this.tipo = tipo;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
		this.valorPessoa = valorPessoa;
		this.qtdMaxPessoas = qtdMaxPessoas;
		if (disponivel == true) {
			this.disponivel = "VIGENTE.";
		} else {
			this.disponivel = "INDISPONIVEL.";
		}
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public LocalTime getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(LocalTime horarioFim) {
		this.horarioFim = horarioFim;
	}

	public String getTitulo() {
		return titulo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setValorPessoa(double valorPessoa) {
		this.valorPessoa = valorPessoa;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public void setQtdeMaxPessoas(int qtdeMaxPessoas) {
		this.qtdMaxPessoas = qtdeMaxPessoas;
	}
	
	@Override
	public String toString() {
	    String valorPorPessoaStr;
	    
	    if (valorPessoa == 0) {
	        valorPorPessoaStr = "Grátis";
	    } else {
	        valorPorPessoaStr = String.format("R$%.2f", valorPessoa);
	    }

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h'mm");
	    
	    return "[" + this.id + "] " + this.tipo + ": " + this.titulo + " (" + this.horarioInicio.format(formatter) + " as " + this.horarioFim.format(formatter) + ")." +
	            " Valor por pessoa: " + valorPorPessoaStr + ". Capacidade: " + this.qtdMaxPessoas + " pessoa(s). " + this.disponivel;
	}


}
