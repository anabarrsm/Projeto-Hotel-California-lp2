package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoFamily;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoSingle;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoFamily;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuartoSingle;

/**
 * @author maria helena cria Quarto Controller
 * 
 */
public class QuartoController {
	private HashMap<String, Quarto> quartos;
	private HashMap<Long, Reserva> reservas;
	private long idReserva;
	
	public QuartoController() {
		this.quartos = new HashMap<>();
		this.reservas = new HashMap<>();
		this.idReserva = 1;
		
	}

	public String disponibilizarQuartoSingle(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase) {
		if (idAutenticacao == null || idAutenticacao.isEmpty()) {
			throw new IllegalArgumentException("ID DE AUTENTICAÇÃO INVÁLIDO");
		}

		if (idQuartoNum <= 0) {
			throw new IllegalArgumentException("ID DO QUARTO INVÁLIDO");
		}

		if (precoPorPessoa < 0 || precoBase < 0) {
			throw new IllegalArgumentException("OS PREÇOS NÃO PODEM SER NEGATIVOS");
		}
		
		if(idAutenticacao.contains("ADM")) {
		QuartoSingle quartoSingle = new QuartoSingle(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
		quartos.put(idAutenticacao, quartoSingle);
	return "QUARTO SINGLE DISPONÍVEL";
		}
		return "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS";
		
	}

	public String disponibilizarQuartoDouble(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase, String[] pedidos) {
		if (idAutenticacao == null || idAutenticacao.isEmpty()) {
			throw new IllegalArgumentException("ID DE AUTENTICAÇÃO INVÁLIDO");
		}

		if (idQuartoNum <= 0) {
			throw new IllegalArgumentException("ID DO QUARTO INVÁLIDO");
		}

		if (precoPorPessoa < 0 || precoBase < 0) {
			throw new IllegalArgumentException("OS PREÇOS NÃO PODEM SER NEGATIVOS");
		}
		
		if(idAutenticacao.contains("ADM")) {
			QuartoDouble quartoDouble = new QuartoDouble(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase, pedidos);
			quartos.put(idAutenticacao, quartoDouble);
			return "QUARTO DOUBLE DISPONÍVEL";
	}

		return "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS";
	}

	public String disponibilizarQuartoFamily(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase, String[] pedidos, int qtdMaxPessoas) {
		if (idAutenticacao == null || idAutenticacao.isEmpty()) {
			throw new IllegalArgumentException("ID DE AUTENTICAÇÃO INVÁLIDO");
		}

		if (idQuartoNum <= 0) {
			throw new IllegalArgumentException("ID DO QUARTO INVÁLIDO");
		}

		if (precoPorPessoa < 0 || precoBase < 0) {
			throw new IllegalArgumentException("OS PREÇOS NÃO PODEM SER NEGATIVOS");
		}

		if (qtdMaxPessoas < 0 || qtdMaxPessoas > 10) {
			throw new IllegalArgumentException("QUANTIDADE DE PESSOAS INVÁLIDAS");
		}
		
		if(idAutenticacao.contains("ADM")) {

		QuartoFamily quartoFamily = new QuartoFamily(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase, pedidos,
				qtdMaxPessoas);
		quartos.put(idAutenticacao, quartoFamily);
		return "QUARTO FAMILY DISPONÍVEL";
		} 
		
		return "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS";
	}

	public String exibirQuarto(int idQuartoNum) {

		if (!quartos.containsKey(idQuartoNum)) {
			throw new IllegalArgumentException("ESSE QUARTO NÃO ESTÁ DISPONÍVEL");
		}

		Quarto quarto = quartos.get(idQuartoNum);
		return quarto.exibirQuarto();

	}

	public String[] listarQuartos() {
		int tamanhoArray = quartos.size();
		String[] quartosArray = new String[tamanhoArray];

		int i = 0;
		for (String idQuartoNum : quartos.keySet()) {
			Quarto quarto = quartos.get(idQuartoNum);
			quartosArray[i] = quarto.exibirQuarto();
			i++;

		}
		return quartosArray;
	}
	
	// us03
	
	public String reservarQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] idRefeicoes) {
		if(idAutenticacao.contains("GER") || idAutenticacao.contains("FUN")){
			long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();
		    if(diferencaEmHoras < 24) {
			     return "O período mínimo da reserva é de uma diária (24 horas)."; 
		     }
		     if(dataFim.isBefore(dataInicio)) {
			     return "A data de fim deve ser posterior à data de início";
		     }

		     if(quartos.containsKey(numQuarto)) {
		    	 Quarto quarto = quartos.get(numQuarto);
			
			 if (quarto.isQuartoReservado() == false) {
				 if(verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {
				ReservaQuartoSingle reservaQuartoSingle = new ReservaQuartoSingle(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes);
				reservas.put(idReserva, reservaQuartoSingle);
				quarto.setQuartoReservado(true);
				this.idReserva ++;
				return "RESERVA QUARTO SINGLE REALIZADA";
				
			}else {
				
				return "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO";
			}
					
		}
		     }else {
		    	 return "O QUARTO NÃO EXISTE";
		     }
		}
		
		return "APENAS GERENTE E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO";
	}
	
	public String reservarQuartoDouble (String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos) {
		if(idAutenticacao.contains("GER") || idAutenticacao.contains("FUN")){
			
			long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();
			
		    if(diferencaEmHoras < 24) {
			     return "O período mínimo da reserva é de uma diária (24 horas)."; 
		     }
		     if(dataFim.isBefore(dataInicio)) {
			     return "A data de fim deve ser posterior à data de início";
		     }

		     if(quartos.containsKey(numQuarto)) {
		    	 Quarto quarto = quartos.get(numQuarto);
			
			 if (quarto.isQuartoReservado() == false) {
				 if(verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {
					 
				ReservaQuartoDouble reservaQuartoDouble = new ReservaQuartoDouble(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes, pedidos);
				reservas.put(idReserva, reservaQuartoDouble);
				
				quarto.setQuartoReservado(true);
				
				this.idReserva ++;
				return "RESERVA QUARTO DOUBLE REALIZADA";
				
			}else {
				
				return "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO";
			}
					
		}
		     }else {
		    	 return "O QUARTO NÃO EXISTE";
		     }
		}
		
		return "APENAS GERENTE E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO";
	}

	public String reservarQuartoFamily (String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos, int numPessoas) {
		
		// verificar quantidade de pessoas 
		
		if(idAutenticacao.contains("GER") || idAutenticacao.contains("FUN")){
			
			long diferencaEmHoras = Duration.between(dataInicio, dataFim).toHours();
			
		    if(diferencaEmHoras < 24) {
			     return "O período mínimo da reserva é de uma diária (24 horas)."; 
		     }
		     if(dataFim.isBefore(dataInicio)) {
			     return "A data de fim deve ser posterior à data de início";
		     }

		     if(quartos.containsKey(numQuarto)) {
		    	 Quarto quarto = quartos.get(numQuarto);
			
			 if (quarto.isQuartoReservado() == false) {
				 if(verificarDisponibilidade(numQuarto, dataInicio, dataFim)) {
					 
				ReservaQuartoFamily reservaQuartoFamily = new ReservaQuartoFamily(idAutenticacao, idCliente, numQuarto, dataInicio, dataFim, idRefeicoes, pedidos, numPessoas);
				reservas.put(idReserva, reservaQuartoFamily);
				
				quarto.setQuartoReservado(true);
				
				this.idReserva ++;
				return "RESERVA QUARTO FAMILY REALIZADA";
				
			}else {
				
				return "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO";
			}
					
		}
		     }else {
		    	 return "O QUARTO NÃO EXISTE";
		     }
		}
		
		return "APENAS GERENTE E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO";
	}

	private boolean verificarDisponibilidade(int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim) {	
		for(Reserva reserva: reservas.values()) {
			if(reserva.getNumQuarto() == numQuarto) {
				if(dataInicio.isBefore(reserva.getDataFim()) && dataFim.isAfter(reserva.getDataInicio()));
				return false;
			}
	}

	return true;
	
}
	
	public double calcularVQRSingle (int numQuarto, long idReserva) {
		Quarto quarto = quartos.get(numQuarto);
		Reserva reserva = reservas.get(idReserva);
		LocalDateTime dataInicio = reserva.dataInicio;
		LocalDateTime dataFim = reserva.dataFim;
		Refeicao refeicao = Refeicao.getId(idReserva);
		
		double valorBasico = quarto.getPrecoBase();
		double valorPessoa = quarto.getPrecoPorPessoa();
		int quantHospedes = 1; 
		long diferencaEmHoras = Duration.between(dataInicio, dataFim).toDays();
		double diarias = Math.ceil(diferencaEmHoras);
		//double refeicoes = 
		return diarias * (valorBasico + quantHospedes + valorPessoa) + diarias * quantHospedes; 
		
	}
	public String exibirReserva(String idAutenticacao, long idReserva) {
		return idAutenticacao;
		
	}
}


