package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ReservaRestauranteController {
	
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservaController reservaController;
	private RefeicaoController refeicaoController;
	private HashMap <Long, ReservaRestaurante> reservasRestaurante;
	private int capacidadeRestaurante;
	
	public ReservaRestauranteController(UsuarioController usuarioController, QuartoController quartoController,
			ReservaController reservaController, RefeicaoController refeicaoController) {
		
		this.usuarioController = usuarioController;
		this.quartoController = quartoController;
		this.reservaController = reservaController;
		this.refeicaoController = refeicaoController;
		this.reservasRestaurante = new HashMap<>();
		this.capacidadeRestaurante = 50;
		
		
	}
	
	
	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDateTime dataInicio, LocalDateTime dataFim, int qtdPessoas, String refeicao) {
		
		if(idAutenticacao.contains("GER")|| idAutenticacao.contains("FUN")){
			
		        if(qtdPessoas > capacidadeRestaurante){
		            throw new RuntimeException("QUANTIDADE DE PESSOAS CONVIDADAS NÃO DEVE EXCEDER A CAPACIDADE DO RESTAURANTE");
		        } else {
		        	
		        }
			
		}
		
		return refeicao;
		
	}

}
