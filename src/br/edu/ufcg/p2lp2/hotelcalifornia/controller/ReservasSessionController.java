package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaAuditorio;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoFamily;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoSingle;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservasSessionController;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.FormaDePagamentoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.PagamentoController;

public class ReservasSessionController {
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private RefeicaoController refeicaoController;
	private ReservaAuditorio reservaAuditorio;
	private ArrayList<Reserva> reservas;
	private long idReserva;
	private final int capacidadeRestaurante;
	private final int capacidadeAuditorio;

	public ReservasSessionController(UsuarioController usuarioController, QuartoController quartoController,
			RefeicaoController refeicaoController) {
		this.usuarioController = usuarioController;
		this.quartoController = quartoController;
		this.refeicaoController = refeicaoController;
		this.reservas = new ArrayList<>();
		this.idReserva = 1;
		this.capacidadeRestaurante = 50;
		this.capacidadeAuditorio = 150;
	}

	/**
	 * @author maria helena us03 reserva de quartos
	 */
	// ReservaQuartoController

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

	/**
	 * método responsável por criar uma reserva de quarto do tipo Single
	 * 
	 * @param idAutenticacao id do usuario que realizará a reserva
	 * @param idCliente      id do cliente que quer reservar o quarto
	 * @param numQuarto      numero do quarto que será reservado
	 * @param dataInicio     data no início da reserva
	 * @param dataFim        data do fim da reserva
	 * @param idRefeicoes    array de strings que contém os id's das refeições
	 * @return a representação textual do quarto Single
	 */

	public String reservarQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes) {

		if (!idAutenticacao.startsWith("GER") && !idAutenticacao.startsWith("FUN")) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");

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

		ReservaQuartoSingle reservaQuartoSingle = new ReservaQuartoSingle(idAutenticacao, idCliente, numQuarto,
				dataInicio, dataFim, idRefeicoes);

		reservas.add(reservaQuartoSingle);

		this.idReserva++;

		reservaQuartoSingle.setIdReserva(idReserva);

		quarto.setQuartoReservado(true);

		return reservaQuartoSingle.toString();
	}

	/**
	 * 
	 * método responsável por criar uma reserva de quarto do tipo Double
	 * 
	 * @param idAutenticacao id do usuario que realizará a reserva
	 * @param idCliente      id do cliente que quer reservar o quarto
	 * @param numQuarto      numero do quarto que será reservado
	 * @param dataInicio     data no início da reserva
	 * @param dataFim        data do fim da reserva
	 * @param idRefeicoes    array de strings que contém os id's das refeições
	 * @param pedidos        pedidos que o cliente faz
	 * @return a representação textual do quarto double
	 */

	public String reservarQuartoDouble(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos) {

		if (!idAutenticacao.startsWith("GER") && !idAutenticacao.startsWith("FUN")) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
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

		ReservaQuartoDouble reservaQuartoDouble = new ReservaQuartoDouble(idAutenticacao, idCliente, numQuarto,
				dataInicio, dataFim, idRefeicoes, pedidos);

		reservas.add(reservaQuartoDouble);

		this.idReserva++;

		reservaQuartoDouble.setIdReserva(idReserva);

		quarto.setQuartoReservado(true);

		return "RESERVA QUARTO DOUBLE REALIZADA";
	}

	/**
	 * método responsável por criar uma reserva de quarto do tipo Family
	 * 
	 * @param idAutenticacao id do usuario que realizará a reserva
	 * @param idCliente      id do cliente que quer reservar o quarto
	 * @param numQuarto      numero do quarto que será reservado
	 * @param dataInicio     data no início da reserva
	 * @param dataFim        data do fim da reserva
	 * @param idRefeicoes    array de strings que contém os id's das refeições
	 * @param pedidos        pedidos que o cliente faz
	 * @param numPessoas     quantidade de pessoas que ocupará o quarto
	 * @return a representação textual do quarto family
	 */

	public String reservarQuartoFamily(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos, int numPessoas) {

		if (!idAutenticacao.startsWith("GER") && !idAutenticacao.startsWith("FUN")) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
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
			throw new HotelCaliforniaException("CAPACIDADE EXCEDIDA");
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

		ReservaQuartoFamily reservaQuartoFamily = new ReservaQuartoFamily(idAutenticacao, idCliente, numQuarto,
				dataInicio, dataFim, idRefeicoes, pedidos, numPessoas);

		reservas.add(reservaQuartoFamily);

		this.idReserva++;

		reservaQuartoFamily.setIdReserva(idReserva);

		quarto.setQuartoReservado(true);

		return "RESERVA QUARTO FAMILY REALIZADA";
	}

	/**
	 * verifica se a reserva está disponível no perído desejado
	 * 
	 * @param numQuarto
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public boolean verificarDisponibilidade(int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim) {
		for (Reserva reserva : reservas) {
			if (dataInicio.isBefore(reserva.getDataFim()) && dataFim.isAfter(reserva.getDataInicio()))
				return false;
		}
		return true;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	/**
	 * exibe as reservas
	 * 
	 * @return representação textual das reservas
	 */
	public String exibeReserva() {
		StringBuilder saida = new StringBuilder();

		for (Reserva reserva : reservas) {
			saida.append(reserva.toString()).append("\n");
		}

		return saida.toString();
	}

	public int qtdReservada() {
		return reservas.size();
	}

	/**
	 * @author maria helena
	 * @param idAutenticacao
	 * @param idReserva
	 * @return
	 */

	public String[] exibirReserva(String idAutenticacao, long idReserva) {
		if (idAutenticacao.contains("ADM")) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO EXIBIR/LISTAR RESERVA(S) DO CLIENTE");
		}

		if (!temReserva(idReserva)) {
			throw new HotelCaliforniaException("RESERVA NAO ENCONTRADA");
		}

		return pegaReserva(idReserva);

	}

	public boolean temReserva(long idReserva) {
		for (int i = 0; i < reservas.size(); i++) {
			if (reservas.get(i).getIdReserva() == (idReserva)) {
				return true;
			}
		}
		return false;
	}

	public String[] pegaReserva(long idReserva) {
		String[] listaReservas = new String[reservas.size()];

		for (int i = 0; i < reservas.size(); i++) {
			if (reservas.get(i).getIdReserva() == (idReserva)) {
				long valorLong = reservas.get(i).getIdReserva();
				String valorString = Long.toString(valorLong);
				listaReservas[i] = valorString;
			}
		}
		return listaReservas;

	}

	public String[] listarReservaAtivasDoCliente(String idAutenticacao, String idCliente) {
		if (idAutenticacao.contains("ADM")) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO EXIBIR/LISTAR RESERVA(S) DO CLIENTE");
		}

		if (!temReserva(idReserva)) {
			throw new HotelCaliforniaException("RESERVA NAO ENCONTRADA");
		}

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

	/**
	 * @author maria helena metodo que faz o cancelamento de uma reseva
	 * @param idCliente cliente que fará o cancelamento
	 * @param idReserva id da reserva que ser cancelada
	 * @return "[CANCELADA]" caso a reserva seja cancelada com sucesso. Lança uma
	 *         exceção caso o usuário não seja o dono da reserva;
	 */

	public String cancelarReserva(String idCliente, String idReserva) {
		if (!idCliente.contains("CLI")) {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA CANCELAR A SUA RESERVA");
		}
		reservas.remove(idReserva);
		return "[CANCELADA]";
	}

	// Reserva Restaurante

	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDateTime dataInicio,
			LocalDateTime dataFim, int qtdPessoas, String idRefeicao) {

		if ((!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN"))) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
		}

		if (!idCliente.contains("CLI")) {
			return "RESERVAS SÓ PODEM SER FEITAS PARA CLIENTES";
		}

		if (!usuarioController.encontrarUsuarioPorId(idAutenticacao)
				|| !usuarioController.encontrarUsuarioPorId(idCliente)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}

//	     if (!verificarDisponibilidadeRestaurante(dataInicio, dataFim, idRefeicao)) {
//	    	  return "O RESTAURANTE JÁ ESTÁ RESERVADO NESTE PERÍODO";
//	     }
		//

		if (qtdPessoas > capacidadeRestaurante) {
			throw new HotelCaliforniaException("CAPACIDADE EXCEDIDA");
		}

		LocalDateTime dataMinimaReserva = LocalDateTime.now().plusDays(1);
		if (!dataInicio.isAfter(dataMinimaReserva)) {
			throw new HotelCaliforniaException("NECESSARIO ANTECEDENCIA MINIMA DE 01 (UM) DIA");
		}

		obterFimRefeicao(idRefeicao);
		ReservaRestaurante reservaRestaurante = new ReservaRestaurante(idCliente, dataInicio, dataFim, qtdPessoas,
				idRefeicao);

		reservas.add(reservaRestaurante);

		this.idReserva++;

		reservaRestaurante.setIdReservaRestaurante(idReserva);

		return reservaRestaurante.toString();
	}

//		private boolean verificarDisponibilidadeRestaurante(LocalDateTime dataInicio, LocalDateTime dataFim, String idRefeicao) {
//		    for (ReservaRestaurante reserva : reservasRestaurante) {
//		        if (reserva.getIdRefeicao().equals(idRefeicao) && !dataInicio.isAfter(reserva.getDataFim()) && !dataFim.isBefore(reserva.getDataInicio())) {
//		            return false;
//		        }
//		    }
//		    
//		    return true;
//		}

	private LocalTime obterFimRefeicao(String idRefeicao) {
		Refeicao refeicao = refeicaoController.obterRefeicaoPeloId(idRefeicao);

		if (refeicao != null) {

			LocalTime fimRefeicao = refeicao.getHorarioFinal();
			return fimRefeicao;
		} else {
			return null;
		}
	}

	private LocalTime obterInicioRefeicao(String idRefeicao) {
		Refeicao refeicao = refeicaoController.obterRefeicaoPeloId(idRefeicao);

		if (refeicao != null) {

			LocalTime inicioRefeicao = refeicao.getHorarioInicio();
			return inicioRefeicao;
		} else {
			return null;
		}
	}

	public String reservarAuditorio(String idAutenticacao, String idCliente, long idAuditorio, LocalDateTime dataInicio,
			LocalDateTime dataFim, int qtdPessoas) {
		if ((!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN"))) {
			return "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR A RESERVA DO AUDITORIO";
		}

		if (!idCliente.contains("CLI")) {
			return "RESERVAS SÓ PODEM SER FEITAS PARA CLIENTES";
		}

		if (!usuarioController.encontrarUsuarioPorId(idAutenticacao)
				|| !usuarioController.encontrarUsuarioPorId(idCliente)) {
			return "USUÁRIO NÃO CADASTRADO";
		}

		if (!verificarDisponibilidadeAuditorio(dataInicio, dataFim, null)) {
			return "O RESTAURANTE JÁ ESTÁ RESERVADO NESTE PERÍODO";
		}

		if (qtdPessoas > capacidadeAuditorio) {
			return "A QUANTIDADE DE PESSOAS EXCEDE A CAPACIDADE DO AUDITORIO";
		}

		LocalDateTime dataMinimaReserva = LocalDateTime.now().plusDays(1);
		if (!dataInicio.isAfter(dataMinimaReserva)) {
			return "A RESERVA DO AUDITORIO DEVE TER PELO MENOS UM DIA DE ANTECEDENCIA.";
		}

		ReservaAuditorio reservaAuditorio = new ReservaAuditorio(idAutenticacao, idCliente, idAuditorio, dataInicio,
				dataFim, qtdPessoas);
		reservas.add(reservaAuditorio);

		this.idReserva = reservas.indexOf(reservaAuditorio) + 1;

		reservaAuditorio.setIdAuditorio(idReserva);

		return "RESERVA DE AUDITORIO REALIZADA";
	}

	private boolean verificarDisponibilidadeAuditorio(LocalDateTime dataInicio, LocalDateTime dataFim, ReservaAuditorio[] reservaAuditorios) {
		for (ReservaAuditorio reservas : reservaAuditorios) {
			if (!dataInicio.isAfter(reservas.getDataFinal()) && !dataFim.isBefore(reservas.getDataInicial())) {
				return false;
			}
		}

		return true;
	}
}
