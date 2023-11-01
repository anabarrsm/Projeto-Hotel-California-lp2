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
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoSingle;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.RefeicaoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaRestauranteController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

public class HotelCaliforniaSistema {
	
	private List<Usuario> usuarios;
	private Map<Integer, Quarto> quartos;
	private List<Reserva> reservas;

	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservaController reservaController;
	private RefeicaoController refeicaoController; 

	public HotelCaliforniaSistema() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController(usuarioController);
		this.reservaController = new ReservaController(usuarioController, quartoController);
		this.refeicaoController = new RefeicaoController(usuarioController);
		//this.restauranteController = new ReservaRestauranteController(usuarioController, refeicaoController);
		
		this.usuarios= new ArrayList<>();
		this.quartos = new HashMap <>();
		this.reservas = new ArrayList<>();
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
	 
	public String[] listaQUarto() {
		return this.quartoController.listaQuartos();
	}
	
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

	public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo,
			LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel) {
		return this.refeicaoController.disponibilizarRefeicao(idAutenticacao, tipoRefeicao, titulo, horarioInicio,
				horarioFinal, valor, disponivel);
	}

	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDateTime dataInicio,
			LocalDateTime dataFim, int qtdPessoas, String idRefeicao) {
		return this.reservarRestaurante(idAutenticacao, idCliente, dataInicio, dataFim, qtdPessoas, idRefeicao);
		}
	
	
	
	public String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal, double valor,
			boolean disponivel) {
		return this.refeicaoController.alterarRefeicao(idRefeicao, horarioInicio, horarioFinal, valor, disponivel);
	}

	public String exibirRefeicao(int idRefeicao) {
		return this.refeicaoController.exibirRefeicaoPorId(idRefeicao);
	}

}