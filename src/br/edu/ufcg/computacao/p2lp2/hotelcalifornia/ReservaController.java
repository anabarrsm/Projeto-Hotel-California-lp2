package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoFamily;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoSingle;

public class ReservaController {
	private HashMap<Integer, Reserva> reservas;
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private int idReserva;
	
	
	public ReservaController(UsuarioController usuarioController, QuartoController quartoController) {
		this.reservas = new HashMap<>();
		this.usuarioController = usuarioController;
		this.quartoController = quartoController; 
		this.idReserva = 0;  


	}

	public String reservarQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] idRefeicoes) {

			long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();

			if (diferencaEmHoras < 24) {
				throw new IllegalArgumentException ("O período mínimo da reserva é de uma diária (24 horas).");
			}

			if (dataFim.isBefore(dataInicio)) {
				throw new IllegalArgumentException ("A data de fim deve ser posterior à data de início");
			}

			if (!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN")) {
				throw new IllegalArgumentException ("Apenas gerentes ou funcionários podem reservar um quarto");
			}
				
			if(usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			
				HashMap<Integer, Quarto> quartos = quartoController.getQuartos();	
						
					if (quartos.containsKey(numQuarto)) {
							Quarto quarto = quartos.get(numQuarto);
				
					if (!quarto.isQuartoReservado()) {
		
							if (verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {
								
								ReservaQuartoSingle reservaQuartoSingle = new ReservaQuartoSingle(idCliente,
										numQuarto, dataInicio, dataFim, idRefeicoes);
								
								this.idReserva++;
								 
								reservaQuartoSingle.setIdReserva(idReserva);
								
								reservas.put(idReserva, reservaQuartoSingle);

								quarto.setQuartoReservado(true);
								
								return "RESERVA QUARTO SINGLE REALIZADA";
								
							}
		} else {
					return "Quarto indisponível para reserva";
	        }
		
			return "Quarto não encontrado!!";
			
	        }
	        return "Quarto não cadastrado!";
	    }
	    return "Cliente não encontrado!!";
	}


	public String reservarQuartoDouble(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos) {

		if (idAutenticacao.contains("GER") || idAutenticacao.contains("FUN")) {

			long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();

			if (diferencaEmHoras < 24) {
				return "O período mínimo da reserva é de uma diária (24 horas).";
			}
			if (dataFim.isBefore(dataInicio)) {
				return "A data de fim deve ser posterior à data de início";
			}
			
			HashMap<Integer, Quarto> quartos = quartoController.getQuartos();	

			if (quartos.containsKey(numQuarto)) {
				Quarto quarto = quartos.get(numQuarto);

				if (quarto.isQuartoReservado()) {
					return "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO";
					
				}if (!quarto.isQuartoReservado()) {	
					
					if (verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {
				}

						ReservaQuartoDouble reservaQuartoDouble = new ReservaQuartoDouble(idAutenticacao, idCliente,
								numQuarto, dataInicio, dataFim, idRefeicoes, pedidos);
						
						this.idReserva++;
						
						
					
						reservas.put(idReserva, reservaQuartoDouble);

						quarto.setQuartoReservado(true);

						

						return "RESERVA QUARTO DOUBLE REALIZADA";
					} 

			} else {
				return "O QUARTO NÃO EXISTE";
			}
		}

		return "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO";
	}

	public String reservarQuartoFamily(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos, int numPessoas) {

		if (idAutenticacao.contains("GER") || idAutenticacao.contains("FUN")) {

			long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();

			if (diferencaEmHoras < 24) {
				return "O período mínimo da reserva é de uma diária (24 horas).";
			}
			if (dataFim.isBefore(dataInicio)) {
				return "A data de fim deve ser posterior à data de início";
			}

			HashMap<Integer, Quarto> quartos = quartoController.getQuartos();	
			
			if (quartos.containsKey(numQuarto)) {
				Quarto quarto = quartos.get(numQuarto);

				if (numPessoas > quarto.getQtdMaxPessoas()) {
					return "O NUMERO DE PESSOAS SUPERA A QUANTIDADE MÁXIMA DE PESSOAS DESSE QUARTO";

				}

				if (quarto.isQuartoReservado()) {
					return "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO";
				}
				
				if (!quarto.isQuartoReservado()) {
					
	
					if (verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {

						ReservaQuartoFamily reservaQuartoFamily = new ReservaQuartoFamily(idAutenticacao, idCliente,
								numQuarto, dataInicio, dataFim, idRefeicoes, pedidos, numPessoas);
						reservas.put(idReserva, reservaQuartoFamily);

						quarto.setQuartoReservado(true);

						this.idReserva++;

						return "RESERVA QUARTO FAMILY REALIZADA";
					}
				}

			} else {
				return "O QUARTO NÃO EXISTE";
			}
		}

		return "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO";
	}

	public boolean verificarDisponibilidade(int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim) {
		for (Reserva reserva : reservas.values()) {
			if (reserva.getNumQuarto() == numQuarto) {
				if (dataInicio.isBefore(reserva.getDataFim()) && dataFim.isAfter(reserva.getDataInicio()))
					;
				return false;
			}
		}

		return true;

	}

	public double calcularVQR(int numQuarto, long idReserva) {

		HashMap<Integer, Quarto> quartos = quartoController.getQuartos();	
		
		Quarto quarto = quartos.get(numQuarto);
		Reserva reserva = reservas.get(idReserva);		
		
		LocalDateTime dataInicio = reserva.getDataInicio();
		LocalDateTime dataFim = reserva.getDataFim();

		double VB = quarto.getPrecoBase();
		double VP = quarto.getPrecoPorPessoa();
		int QH = quarto.getQtdMaxPessoas();

		long diferencaEmHoras = Duration.between(dataInicio, dataFim).toDays();
		double ND = Math.ceil(diferencaEmHoras);
		
		double SRI = reserva.getValorTotalRefeicoes();
		
		double VRQ = ND * (VB + QH * VP) + ND * QH * SRI;
		return VRQ; 

	} 

	public String exibirReserva(String idAutenticacao, long idReserva) { 
		String saida = "";
		if(!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN")) {
		return "APENAS GERENTES E FUNCIONÁRIOS POSSUEM AUTORIZAÇÃO PARA LISTAR RESERVA";
		} else {
			
			
			
			if(reservas.containsKey(idReserva)) {
			
			Reserva reserva = reservas.get(idReserva);
			String idCliente = reserva.getIdCliente(); 
			String cliente = usuarioController.exibirUsuario(idCliente);
			
			saida = reserva.exibirReserva();
			
			 return saida; 
			}
		}
		
		return "nao tem esse reserva no array de reservas";
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

//	public HashMap<Integer, Quarto> getQuartos() {
//		return quartos;
//	}
}


