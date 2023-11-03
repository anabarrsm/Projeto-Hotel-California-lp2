package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoSingle;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.AreaComumController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.FormaDePagamentoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.RefeicaoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaAuditorioController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaQuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaRestauranteController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

public class HotelCaliforniaSistema {
	
	private List<Usuario> usuarios;
	private Map<Integer, Quarto> quartos;
	private List < Refeicao> refeicoes;
	private List<ReservaQuarto> reservas;

	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservaQuartoController reservaController;
	private RefeicaoController refeicaoController; 
	private FormaDePagamentoController formaDePagamentoController;
	private AreaComumController areaComumController;
	private ReservaAuditorioController auditorioController;

	public HotelCaliforniaSistema() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController(usuarioController);
		this.reservaController = new ReservaQuartoController(usuarioController, quartoController);
		this.refeicaoController = new RefeicaoController(usuarioController);
		this.formaDePagamentoController = new FormaDePagamentoController(usuarioController);
		this.areaComumController = new AreaComumController(usuarioController);
		//this.restauranteController = new ReservaRestauranteController(usuarioController, refeicaoController);
		
		this.usuarios= new ArrayList<>();
		this.quartos = new HashMap <>();
		this.refeicoes = new ArrayList<>();
		this.reservas = new ArrayList<>();
	} 

	
	// US01

	public String cadastrarUsuario(String idAutenticacao, String nome, String tipoUsuario, long documento) {
		return this.usuarioController.cadastrarUsuario(idAutenticacao, nome, tipoUsuario, documento);

	}


	public String atualizarUsuario(String idAutenticacao, String idUsuario, String novoTipoUsuario) {
		return this.usuarioController.atualizarUsuario(idAutenticacao, idUsuario, novoTipoUsuario);
	}

	public String exibirUsuario(String idUsuario) {
		return this.usuarioController.exibirUsuario(idUsuario);

	}

	public String[] listarUsuarios() { 
		return this.usuarioController.listarUsuarios();
	}

	//US02
	
	public String disponibilizarQuartoSingle(String idAutenticacao, int idQuartoNum, double precoBase,
			double precoPorPessoa) {
		return this.quartoController.disponibilizarQuartoSingle(idAutenticacao, idQuartoNum, precoBase, precoPorPessoa);
	}

	public String disponibilizarQuartoDouble(String idAutenticacao, int idQuartoNum, double precoBase,
			double precoPorPessoa, String[] pedidos) {
		return this.quartoController.disponibilizarQuartoDouble(idAutenticacao, idQuartoNum, precoBase, precoPorPessoa,
				pedidos);
	}

	public String disponibilizarQuartoFamily(String idAutenticacao, int idQuartoNum, double precoBase,
			double precoPorPessoa, String[] pedidos, int qtdMaxPessoas) {
		return this.quartoController.disponibilizarQuartoFamily(idAutenticacao, idQuartoNum, precoBase, precoPorPessoa,
				pedidos, qtdMaxPessoas);
	}

	public String exibirQuarto(int i) {
		return this.quartoController.exibirQuarto(i);
		
	}
	 
	public String[] listarQuartos() {
		return this.quartoController.listarQuartos();
	}
	
	//US03
	
	public String reservarQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes) {
		return this.reservaController.reservarQuartoSingle(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes);
	}
	
	public String reservarQuartoDouble(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos) {
		return this.reservaController.reservarQuartoDouble(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes, pedidos);
	}
	
	public String reservarQuartoFamily(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos, int numPessoas) {
		return this.reservaController.reservarQuartoFamily(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes, pedidos, numPessoas);
	}

	//US04
	public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo,
			LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel) {
		return this.refeicaoController.disponibilizarRefeicao(idAutenticacao, tipoRefeicao, titulo, horarioInicio,
				horarioFinal, valor, disponivel);
	}

	//US05
	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDateTime dataInicio,
			LocalDateTime dataFim, int qtdPessoas, String idRefeicao) {
		return this.reservarRestaurante(idAutenticacao, idCliente, dataInicio, dataFim, qtdPessoas, idRefeicao);
		}
	
	
	public String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal, double valor,
			boolean disponivel) {
		return this.refeicaoController.alterarRefeicao(idRefeicao, horarioInicio, horarioFinal, valor, disponivel);
	}

	public String exibirRefeicao(long idRefeicao) {
		return this.refeicaoController.exibirRefeicaoPorId(idRefeicao);
	}
	 public String[] listarRefeicoes() {
		 return this.refeicaoController.listarRefeicoes();	
		 
	 }
	 
	 // US07
	 public String disponibilizarFormaDePagamento(String idAutenticacao, String formaPagamento, double percentualDesconto ) {
		return this.formaDePagamentoController.disponibilizarFormaDePagamento(idAutenticacao, formaPagamento, percentualDesconto);
	 }
	 
	 public String alterarFormaDePagamento(String idAutenticacao, int idFormaPagamento, String formaPagamento, double percentualDesconto) {
		return  this.formaDePagamentoController.alterarFormaDePagamento(idAutenticacao, idFormaPagamento, formaPagamento, percentualDesconto);
	 }
	 
	 public String exibirFormaPagamento(int idFormaPagamento) {
		return this.formaDePagamentoController.exibirFormaDePagamento(idFormaPagamento);
	 }
	 
	 public String[] listarFormasPagamentos() {
		return this.formaDePagamentoController.listarFormasDePagamentos();
	 }
	 
	 //US09
	 
	 public String cancelarReserva(String idCliente, String idReserva) {
		 return this.reservaController.cancelarReserva(idCliente, idReserva);
	 }
	//US10
	 
	 public String disponibilizarAreaComum (String idAutenticacao, String tipoAreaComum, String titulo, LocalTime horarioInicio, LocalTime horarioFinal, double valorPessoa, boolean disponivel, int qtdMaxPessoas) {
		 return this.areaComumController.disponibilizarAreaComum(idAutenticacao, tipoAreaComum, titulo, horarioInicio, horarioFinal, valorPessoa, disponivel, qtdMaxPessoas);
	 }
	 
	 public String alterarAreaComum(String idAutenticacao, long idAreaComum, LocalTime novoHorarioInicio, LocalTime novoHorarioFinal, double novoPreco, int capacidadeMax, boolean ativa) {
		 return this.areaComumController.alterarAreaComum(idAutenticacao, idAreaComum, novoHorarioInicio, novoHorarioFinal, novoPreco, capacidadeMax, ativa);
	 }
	 
	 public String exibirAreaComum(long idAreaComum){
		 return this.areaComumController.exibirAreaComum(idAreaComum);
	 }
	 
	 public String[] listarAreasComuns() {
		  return this.areaComumController.listarAreasComuns();
	 }
	 
	 //US011
	 
	 public String reservarAuditorio(String idAutenticacao, String idCliente, long idAuditorio, LocalDateTime dataInicio, LocalDateTime dataFim, int qtdMaxPessoas) {
		 return this.reservarAuditorio(idAutenticacao, idCliente, idAuditorio, dataInicio, dataFim, qtdMaxPessoas);
	 }
	 
}