package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;

public class ReservaRestauranteController {

	private UsuarioController usuarioController;
	private ReservaController reservaController;
	private RefeicaoController refeicaoController;
	private ArrayList<ReservaRestaurante> reservasRestaurante;
	private int capacidadeRestaurante;
	private long idReservaRestaurante;

	public ReservaRestauranteController(UsuarioController usuarioController,
 RefeicaoController refeicaoController) {

		this.usuarioController = usuarioController;
		this.refeicaoController = refeicaoController;
		this.reservasRestaurante = new ArrayList<>();
		this.capacidadeRestaurante = 50;
		this.idReservaRestaurante = 1;

	}

	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDateTime dataInicio,
			LocalDateTime dataFim, int qtdPessoas, String idRefeicao) {
	 	
    	if ((!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN"))) {
            return "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR A RESERVA DO RESTAURANTE";
        }
    	
    	if(!idCliente.contains("CLI")) {
    		return "RESERVAS SÓ PODEM SER FEITAS PARA CLIENTES";
    	}

        if(!usuarioController.encontrarUsuarioPorId(idAutenticacao) || !usuarioController.encontrarUsuarioPorId(idCliente)) {
        	return "USUÁRIO NÃO CADASTRADO";
        }

      if (!verificarDisponibilidadeRestaurante(dataInicio, dataFim, idRefeicao)) {
    	  return "O RESTAURANTE JÁ ESTÁ RESERVADO NESTE PERÍODO";
      }
    
        
        if(qtdPessoas > capacidadeRestaurante) {
        	return "A QUANTIDADE DE PESSOAS EXCEDE A CAPACIDADE DO RESTAURANTE";
        }
        
        LocalDateTime dataMinimaReserva = LocalDateTime.now().plusDays(1);
        if (!dataInicio.isAfter(dataMinimaReserva)) {
            return "A reserva do restaurante deve ser feita com pelo menos um dia de antecedência.";
        }

		obterFimRefeicao(idRefeicao);
        ReservaRestaurante reservaRestaurante = new ReservaRestaurante(idCliente, dataInicio, dataFim, qtdPessoas, idRefeicao);
        
        reservasRestaurante.add(reservaRestaurante);

		this.idReservaRestaurante = reservasRestaurante.indexOf(reservaRestaurante) + 1;

		reservaRestaurante.setIdReservaRestaurante(idReservaRestaurante);

		return "RESERVA RESTAURANTE REALIZADA";
	}
        
     
	
	private boolean verificarDisponibilidadeRestaurante(LocalDateTime dataInicio, LocalDateTime dataFim, String idRefeicao) {
	    for (ReservaRestaurante reserva : reservasRestaurante) {
	        if (!dataInicio.isAfter(reserva.getDataFim()) && !dataFim.isBefore(reserva.getDataInicio())) {
	            return false;
	        }
	    }
	    
	    return true;
	}

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
//		
