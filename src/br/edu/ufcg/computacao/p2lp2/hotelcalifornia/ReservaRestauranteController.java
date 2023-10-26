package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ReservaRestauranteController {

	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservaController reservaController;
	private RefeicaoController refeicaoController; 
	private HashMap<String, ReservaRestaurante> reservasRestaurante;
	private int capacidadeRestaurante;
	private String idReserva;

	public ReservaRestauranteController(UsuarioController usuarioController, QuartoController quartoController,
			ReservaController reservaController, RefeicaoController refeicaoController) {

		this.usuarioController = usuarioController;
		this.quartoController = quartoController;
		this.reservaController = reservaController;
		this.refeicaoController = refeicaoController; 
		this.reservasRestaurante = new HashMap<String, ReservaRestaurante>();
		this.capacidadeRestaurante = 50;
		this.idReserva = "1";

	}

	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDate dataInicio,
			LocalDate dataFim, int qtdPessoas, String refeicao) {
	 	
    	if ((!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN"))) {
            return "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR A RESERVA DO RESTAURANTE";
        }
    	
    	if(!idCliente.contains("CLI")) {
    		return "RESERVAR SÓ PODEM SER FEITAS PARA CLIENTES";
    	}

        if(!usuarioController.encontrarUsuarioPorId(idAutenticacao) || !usuarioController.encontrarUsuarioPorId(idCliente)) {
        	return "USUÁRIO NÃO CADASTRADO";
        }
	}
}

}
//        if (!verificarDisponibilidadeRestaurante(dataInicio, dataFim, refeicao)) {
//            return "O RESTAURANTE NÃO ESTÁ DISPONÍVEL PARA RESERVA NO PERÍODO DESEJADO";
//        }
     
//    	
//    			
//        Refeicao refeicao = new Refeicao(tipoRefeicao, titulo, horarioInicio, horarioFinal, valor, disponivel);
//        
//        refeicoes.add(refeicao);
//        
//        this.idRefeicao = refeicoes.indexOf(refeicao) + 1;
//        
//        refeicao.setIdRefeicao(idRefeicao);
//        
//        refeicao.setRefeicaoDisponivel(disponivel);
//        
//        
//        return "REFEIÇÃO DISPONIBILIZADA COM SUCESSO";
        
    	

//	public boolean verificarDisponibilidadeRestaurante(LocalDate dataInicio, LocalDate dataFim, String refeicao) {
//		ArrayList<Refeicao> refeicoes = refeicaoController.getRefeicoes();
//		LocalDateTime horaIncioRefeicao = refeicoes.get(refeicao)
//		
//	    for (ReservaRestaurante reserva : reservasRestaurante.values()) {
//	    	
//	        LocalDateTime reservaInicio = reserva.getDataInicio();
//	        LocalDateTime reservaFim = reserva.getDataFim();
//
//	        // Verificar se o período da reserva atual conflita com alguma reserva existente
//	        if ((dataInicio.isEqual(reservaInicio) || dataInicio.isAfter(reservaInicio)) && dataInicio.isBefore(reservaFim)) {
//	            return false; // Existe uma reserva conflitante
//	        }
//
//	        if ((dataFim.isEqual(reservaInicio) || dataFim.isAfter(reservaInicio)) && dataFim.isBefore(reservaFim)) {
//	            return false; // Existe uma reserva conflitante
//	        }
//	    }
//
//	    // Se nenhum conflito foi encontrado, o restaurante está disponível
//	    return true;
//	}
//		
