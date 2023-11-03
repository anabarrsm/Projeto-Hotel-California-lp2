package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoFamily;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoSingle;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

/**
 * Classe ReservaController responsável por fazer o controle das operações
 * relacionadas a Reserva dos Quartos
 * 
 * @author Maria Helena
 */
public class ReservaQuartoController {
	private ArrayList <ReservaQuarto> reservas;
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private long idReserva;

	public ReservaQuartoController(UsuarioController usuarioController, QuartoController quartoController) {
		this.reservas = new ArrayList<>();
		this.usuarioController = usuarioController;
		this.quartoController = quartoController;
		this.idReserva = 1;

	}

	public int quartosCadastrados() {
		HashMap<Integer, Quarto> quartos = quartoController.getQuartos();
		return quartos.size();
	}

	public String exibirQuartosCadastrados() {
		StringBuilder saida = new StringBuilder();
		HashMap<Integer, Quarto> quartos = quartoController.getQuartos();

		for (Quarto quarto : quartos.values()) {
			saida.append(quarto.toString()).append("\n"); // Adiciona uma quebra de linha após cada quarto
		}

		return saida.toString(); 
	}

	public String reservarQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes) {

		if (!idAutenticacao.startsWith("GER") && !idAutenticacao.startsWith("FUN")) {
			return "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR RESERVAS DE QUARTO";
		}
		if (!usuarioController.encontrarUsuarioPorId(idCliente)
				|| !usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			return "USUÁRIO NÃO CADASTRADO NO SISTEMA";
		}

		HashMap<Integer, Quarto> quartos = quartoController.getQuartos();

		if (!quartos.containsKey(numQuarto)) {
			return "QUARTO NÃO EXISTE NO SISTEMA";
		}

		Quarto quarto = quartos.get(numQuarto);

		if (quarto.isQuartoReservado()) {
			return "O QUARTO JÁ ESTÁ RESERVADO NO PERÍODO DESEJADO!";
		}

		if (!verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {
			return "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO!";
		}

		if (dataFim.isBefore(dataInicio)) {
			return "A DATA DE FIM DEVE SER POSTERIOR À DATA DE INÍCIO";
		}

		long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();

		if (diferencaEmHoras < 24) {
			return "O PERÍODO MÍNIMO DA RESERVA É DE 01 DIÁRIA (24 HORAS)";
		}

		ReservaQuartoSingle reservaQuartoSingle = new ReservaQuartoSingle(idCliente, numQuarto, dataInicio, dataFim,
				idRefeicoes);

		reservas.add(reservaQuartoSingle);

		this.idReserva = reservas.indexOf(reservaQuartoSingle) + 1;

		reservaQuartoSingle.setIdReserva(idReserva);

		quarto.setQuartoReservado(true);

		return "RESERVA QUARTO SINGLE REALIZADA";
	}

	public String reservarQuartoDouble(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos) { 

		if (!idAutenticacao.startsWith("GER") && !idAutenticacao.startsWith("FUN")) {
			return "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR RESERVAS DE QUARTO";
		}
		if (!usuarioController.encontrarUsuarioPorId(idCliente)
				|| !usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			return "USUÁRIO NÃO CADASTRADO NO SISTEMA";
		}

		HashMap<Integer, Quarto> quartos = quartoController.getQuartos();

		if (!quartos.containsKey(numQuarto)) {
			return "QUARTO NÃO EXISTE NO SISTEMA";
		}

		Quarto quarto = quartos.get(numQuarto);

		if (quarto.isQuartoReservado()) {
			return "O QUARTO JÁ ESTÁ RESERVADO NO PERÍODO DESEJADO!";
		}

		if (!verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {
			return "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO!";
		}

		if (dataFim.isBefore(dataInicio)) {
			return "A DATA DE FIM DEVE SER POSTERIOR À DATA DE INÍCIO";
		}

		long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();

		if (diferencaEmHoras < 24) {
			return "O PERÍODO MÍNIMO DA RESERVA É DE 01 DIÁRIA (24 HORAS)";
		}

		ReservaQuartoDouble reservaQuartoDouble = new ReservaQuartoDouble(idCliente, numQuarto, dataInicio, dataFim,
				idRefeicoes, pedidos);

		reservas.add(reservaQuartoDouble);

		this.idReserva = reservas.indexOf(reservaQuartoDouble) + 1;

		reservaQuartoDouble.setIdReserva(idReserva);

		quarto.setQuartoReservado(true);

		return "RESERVA QUARTO DOUBLE REALIZADA";
	}

	public String reservarQuartoFamily(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos, int numPessoas) {

		if (!idAutenticacao.startsWith("GER") && !idAutenticacao.startsWith("FUN")) {
			return "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR RESERVAS DE QUARTO";
		}
		if (!usuarioController.encontrarUsuarioPorId(idCliente)
				|| !usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			return "USUÁRIO NÃO CADASTRADO NO SISTEMA";
		}

		HashMap<Integer, Quarto> quartos = quartoController.getQuartos();

		if (!quartos.containsKey(numQuarto)) {
			return "QUARTO NÃO EXISTE NO SISTEMA";
		}

		Quarto quarto = quartos.get(numQuarto);

		if (numPessoas > quarto.getQtdMaxPessoas()) {
			return "O NUMERO DE PESSOAS SUPERA A QUANTIDADE MÁXIMA DE PESSOAS PERMITIDA";
		}
		if (quarto.isQuartoReservado()) {
			return "O QUARTO JÁ ESTÁ RESERVADO NO PERÍODO DESEJADO!";
		}

		if (!verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {
			return "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO!";
		}

		if (dataFim.isBefore(dataInicio)) {
			return "A DATA DE FIM DEVE SER POSTERIOR À DATA DE INÍCIO";
		}

		long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();

		if (diferencaEmHoras < 24) {
			return "O PERÍODO MÍNIMO DA RESERVA É DE 01 DIÁRIA (24 HORAS)";
		}

		ReservaQuartoFamily reservaQuartoFamily = new ReservaQuartoFamily(idCliente, numQuarto, dataInicio, dataFim,
				idRefeicoes, pedidos, numPessoas);

		reservas.add(reservaQuartoFamily);

		this.idReserva = reservas.indexOf(reservaQuartoFamily) + 1;

		reservaQuartoFamily.setIdReserva(idReserva);

		quarto.setQuartoReservado(true);

		return "RESERVA QUARTO FAMILY REALIZADA";
	}

	public boolean verificarDisponibilidade(int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim) {
		for (ReservaQuarto reserva : reservas) {
			if (reserva.getNumQuarto() == numQuarto) {
				if (dataInicio.isBefore(reserva.getDataFim()) && dataFim.isAfter(reserva.getDataInicio())) {
					return false; // Há uma sobreposição de datas
				}
			}
		}
		return true;
	}

	public ArrayList<ReservaQuarto> getReservas() {
		return reservas;
	}

	public String exibeReservas() {
		StringBuilder saida = new StringBuilder();

		for (ReservaQuarto reserva : reservas) {
			saida.append(reserva.toString()).append("\n");
		}

		return saida.toString();
	}

	public int qtdReservada() {
		return reservas.size();
	}



	public String[] listarReservaAtivasDoCliente(String idAutenticacao, String idCliente) {
		return null;

	}

	public String[] listarReservasAtivasDoClientePorTipo(String idAutenticaco, String idCliente, String tipo) {
		return null;

	}

	public String[] listarReservasAtivasPorTipo(String idAutenticacao, String tipo) {
		return null;

	}

	public String[] listarReservasAtivas(String idAutenticacao) {
		return null;

	}

	public String[] listarReservasTodas(String idAutenticacao) {
		return null;

	}

	public String cancelarReserva(String idCliente, String idReserva) {
		if(!idCliente.contains("CLI")) {
			throw new HotelCaliforniaException ("SOMENTE O PROPRIO CLIENTE PODERA CANCELAR A SUA RESERVA");
		}
		reservas.remove(idReserva);
		return "[CANCELADA]";
	}

}
