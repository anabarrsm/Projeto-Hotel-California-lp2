package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;

public class ReservaQuartoFamily extends Reserva {
	
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private String idAutenticacao;
	private String idCliente;
	private int numQuarto;
	private String[] pedidos;
	private String[] idRefeicoes;
	private int numPessoas;
	private long idReserva;

	public ReservaQuartoFamily(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos, int numPessoas) {
		super(dataInicio, dataFim);
	
		this.quartoController = quartoController;
		this.usuarioController = usuarioController;
		this.idAutenticacao = idAutenticacao;
		this.idCliente = idCliente;
		this.numQuarto = numQuarto;
		this.idRefeicoes = idRefeicoes;
		this.pedidos = pedidos;

	}
	
	Quarto quarto = quartoController.getQuartos().get(numQuarto);
	Usuario usuario = usuarioController.retornaUsuarioPorId(idAutenticacao);

	@Override
	public double calculaValor() {
		return numPessoas;
//		double precoRefeicao = 0;
//		for (Refeicao r : this.refeicoes) {
//			precoRefeicao += r.getValor();
//		}
//		double qtdeDias = Math.ceil((int)ChronoUnit.HOURS.between(getDataInicio(), getDataFim()) / 24);
//		return (int)qtdeDias * ((int)quarto.getPrecoBase() + (int)this.numPessoas * 
//				(int)quarto.getPrecoPorPessoa()) + (int)qtdeDias * (int)this.numPessoas *
//				(int)precoRefeicao;
	}

	@Override
	public String toString() {
		return "[" + this.usuario.getId() + "]" + "Reserva de RESTAURANTE em favor de:" + "\n"
				+ "- " + this.usuario.toString() + "\n"
				+ "Detalhes da instalação:" + "\n"
				+ "- Período: " + this.dataInicio + " até " + this.dataFim + "\n"
				+ "- Qtde. de Convidados: " + this.numPessoas + " pessoa(s)" + "\n"
				+ "- Refeicao incluida: " + this.idRefeicoes + "\n"
				+ "VALOR TOTAL DA RESERVA: R$" + getValor() + " x" + ChronoUnit.DAYS.between(getDataInicio(), getDataFim()) + " (diarias) => R$" + calculaValor() + "\n"
				+ "SITUACAO DO PAGAMENTO: " + getSituacaoPagamento() + ".";
	}
	
	}