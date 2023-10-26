package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoSingle;

public class HotelCaliforniaSistema {

	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservaController reservaController;
	private RefeicaoController refeicaoController;
	private ReservaRestauranteController restauranteController;

	public HotelCaliforniaSistema() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController(usuarioController);
		this.reservaController = new ReservaController(usuarioController, quartoController);
		this.refeicaoController = new RefeicaoController(usuarioController);
		this.restauranteController = new ReservaRestauranteController(usuarioController, quartoController,
				reservaController, refeicaoController);

	}

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

	public String disponibilizarQuartoSingle(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase) {
		return this.quartoController.disponibilizarQuartoSingle(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
	}

	public String disponibilizarQuartoDouble(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase, String[] pedidos) {
		return this.quartoController.disponibilizarQuartoDouble(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase,
				pedidos);
	}

	public String disponibilizarQuartoFamily(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase, String[] pedidos, int qtdMaxPessoas) {
		return this.quartoController.disponibilizarQuartoFamily(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase,
				pedidos, qtdMaxPessoas);
	}

	public String reservarQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes) {
		return this.reservaController.reservarQuartoSingle(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim,
				idRefeicoes);
	}

	public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo,
			LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel) {
		return this.refeicaoController.disponibilizarRefeicao(idAutenticacao, tipoRefeicao, titulo, horarioInicio,
				horarioFinal, valor, disponivel);
	}

	public String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal, double valor,
			boolean disponivel) {
		return this.refeicaoController.alterarRefeicao(idRefeicao, horarioInicio, horarioFinal, valor, disponivel);
	}

	public String exibirRefeicao(int idRefeicao) {
		return this.refeicaoController.exibirRefeicaoPorId(idRefeicao);
	}

}