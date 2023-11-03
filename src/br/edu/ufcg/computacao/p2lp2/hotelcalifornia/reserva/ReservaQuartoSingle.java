package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.RefeicaoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;

public class ReservaQuartoSingle extends Reserva {
	private String[] refeicoes;
	private int numPessoas;
	private String idAutenticacao;
	private String idCliente;
	private long idReserva;
	private int numQuarto;
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private RefeicaoController refeicaoController;

	public ReservaQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes) {
		super(dataInicio, dataFim);
		
		this.usuarioController = usuarioController;
		this.quartoController = quartoController;
		this.refeicaoController = refeicaoController;
		this.idAutenticacao = idAutenticacao;
		this.idCliente = idCliente;
		this.numQuarto = numQuarto;
		this.refeicoes = idRefeicoes;
		this.idReserva = 0;
		this.numPessoas = 1; 
	}



	
	@Override
	public int calculaValor() {
		return numPessoas;

//		double precoRefeicao = 0;
//		for (String idRefeicao : this.refeicoes) {
//			Refeicao refeicao = refeicaoController.obterRefeicaoPeloId(idRefeicao);
//			precoRefeicao += refeicao.getValor();
//		}
//
//		double qtdeDias = Math.ceil(Duration.between(getDataInicio(), getDataFim()).toHours() / 24);
//		return (int) qtdeDias * ((int) quarto.getPrecoBase() + (int) this.numPessoas * (int) quarto.getPrecoPorPessoa())
//				+ (int) qtdeDias * (int) this.numPessoas * (int) precoRefeicao;
	}

	@Override
	public String toString() {
	 return "oi";
	}
}
