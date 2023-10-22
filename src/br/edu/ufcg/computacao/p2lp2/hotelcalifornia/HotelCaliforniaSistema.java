package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoSingle;

public class HotelCaliforniaSistema {
	
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private RefeicaoController refeicaoController;
	private HashMap<Long, Reserva> reservas;
	
	private long idReserva;

		public HotelCaliforniaSistema() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController();
		this.reservas = new HashMap<>();
		this.idReserva = 1;
	}

	public String cadastrarUsuario(String idAutenticacao, String nome, String tipoUsuario, String documento) {
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

		if (idAutenticacao.contains("GER") || idAutenticacao.contains("FUN")) {

			long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();

			if (diferencaEmHoras < 24) {
				return "O período mínimo da reserva é de uma diária (24 horas).";
			}

			if (dataFim.isBefore(dataInicio)) {
				return "A data de fim deve ser posterior à data de início";
			}
			
			if (quartoController.getQuartos().containsKey(numQuarto)) {
				Quarto quarto = quartoController.getQuartos().get(numQuarto);

				if (quarto.isQuartoReservado()) {
					return "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO";
				}

				else {

					if (quartoController.verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {
						
						ReservaQuartoSingle reservaQuartoSingle = new ReservaQuartoSingle(idAutenticacao, idCliente,
								numQuarto, dataInicio, dataFim, idRefeicoes);
						reservas.put(idReserva, reservaQuartoSingle);

						quarto.setQuartoReservado(true);
						this.idReserva++;

						return "RESERVA QUARTO SINGLE REALIZADA";

					} 

				}
			} else {
				return "O QUARTO NÃO EXISTE";
			}
		}

		return "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO";
	}
	

}
