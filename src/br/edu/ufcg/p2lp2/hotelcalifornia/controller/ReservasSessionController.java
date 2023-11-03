package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoFamily;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoSingle;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

public class ReservasSessionController {
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private RefeicaoController refeicaoController;

	private ArrayList<Reserva> reservas;
	private long idReserva;
	
	private int capacidadeRestaurante;

	public ReservasSessionController(UsuarioController usuarioController, QuartoController quartoController, RefeicaoController refeicaoController) {
	    this.usuarioController = usuarioController;
	    this.quartoController = quartoController;
	    this.refeicaoController = refeicaoController;
	    this.reservas = new ArrayList<>();
	    this.idReserva = 1;
	    this.capacidadeRestaurante = 50;
	}


	//ReservaQuartoController
	
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

		ReservaQuartoSingle reservaQuartoSingle = new ReservaQuartoSingle(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim,
				idRefeicoes);

		reservas.add(reservaQuartoSingle);

		this.idReserva = reservas.indexOf(reservaQuartoSingle) + 1;

		reservaQuartoSingle.setIdReserva(idReserva);

		quarto.setQuartoReservado(true);

		return reservaQuartoSingle.toString();
	}

	public String reservarQuartoDouble(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos) { 

		if (!idAutenticacao.startsWith("GER") && !idAutenticacao.startsWith("FUN")) {
			 throw new HotelCaliforniaException ("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
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

		ReservaQuartoDouble reservaQuartoDouble = new ReservaQuartoDouble(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim,
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
			 throw new HotelCaliforniaException ("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
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

		ReservaQuartoFamily reservaQuartoFamily = new ReservaQuartoFamily(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim,
				idRefeicoes, pedidos, numPessoas);

		reservas.add(reservaQuartoFamily);

		this.idReserva = reservas.indexOf(reservaQuartoFamily) + 1;

		reservaQuartoFamily.setIdReserva(idReserva);

		quarto.setQuartoReservado(true);

		return "RESERVA QUARTO FAMILY REALIZADA";
	}

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

	public String exibeReservas() {
		StringBuilder saida = new StringBuilder();

		for (Reserva reserva : reservas) {
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
	
	// Cancelar Reserva

	public String cancelarReserva(String idCliente, String idReserva) {
		if(!idCliente.contains("CLI")) {
			throw new HotelCaliforniaException ("SOMENTE O PROPRIO CLIENTE PODERA CANCELAR A SUA RESERVA");
		}
		reservas.remove(idReserva);
		return "[CANCELADA]";
	}

	// Reserva Restaurante
	
	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDateTime dataInicio,
			LocalDateTime dataFim, int qtdPessoas, String idRefeicao) {
	 	
    	if ((!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN"))) {
            throw new HotelCaliforniaException ("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA RESERVA");
        }
    	
    	if(!idCliente.contains("CLI")) {
    		return "RESERVAS SÓ PODEM SER FEITAS PARA CLIENTES";
    	}

        if(!usuarioController.encontrarUsuarioPorId(idAutenticacao) || !usuarioController.encontrarUsuarioPorId(idCliente)) {
        	throw new HotelCaliforniaException("USUARIO NAO EXISTE");
        }

//      if (!verificarDisponibilidadeRestaurante(dataInicio, dataFim, idRefeicao)) {
//    	  return "O RESTAURANTE JÁ ESTÁ RESERVADO NESTE PERÍODO";
//      }
    
        
        if(qtdPessoas > capacidadeRestaurante) {
        	throw new HotelCaliforniaException ("CAPACIDADE EXCEDIDA");
        }
        
        LocalDateTime dataMinimaReserva = LocalDateTime.now().plusDays(1);
        if (!dataInicio.isAfter(dataMinimaReserva)) {
            throw new HotelCaliforniaException ("NECESSARIO ANTECEDENCIA MINIMA DE 01 (UM) DIA");
        }

		obterFimRefeicao(idRefeicao);
        ReservaRestaurante reservaRestaurante = new ReservaRestaurante(idCliente, dataInicio, dataFim, qtdPessoas, idRefeicao);
        
        reservas.add(reservaRestaurante);

		this.idReserva = reservas.indexOf(reservaRestaurante) + 1;

		reservaRestaurante.setIdReservaRestaurante(idReserva);

		return reservaRestaurante.toString();
	}
        
     
//	
//	private boolean verificarDisponibilidadeRestaurante(LocalDateTime dataInicio, LocalDateTime dataFim, String idRefeicao) {
//	    for (ReservaRestaurante reserva : reservasRestaurante) {
//	        if (!dataInicio.isAfter(reserva.getDataFim()) && !dataFim.isBefore(reserva.getDataInicio())) {
//	            return false;
//	        }
//	    }
//	    
//	    return true;
//	}

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
}
