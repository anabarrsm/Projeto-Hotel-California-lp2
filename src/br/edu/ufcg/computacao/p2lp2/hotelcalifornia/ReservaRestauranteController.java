package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.util.HashMap;

public class ReservaRestauranteController {

	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservaController reservaController;
	private RefeicaoController refeicaoController;
	private HashMap<Integer, ReservaRestaurante> reservasRestaurante;
	private int capacidadeRestaurante;
	private int idReserva;

	public ReservaRestauranteController(UsuarioController usuarioController, QuartoController quartoController,
			ReservaController reservaController, RefeicaoController refeicaoController) {

		this.usuarioController = usuarioController;
		this.quartoController = quartoController;
		this.reservaController = reservaController;
		this.refeicaoController = refeicaoController;
		this.reservasRestaurante = new HashMap<Integer, ReservaRestaurante>();
		this.capacidadeRestaurante = 50;
		this.idReserva = 1;

	}

	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDate dataInicio,
			LocalDate dataFim, int qtdPessoas, String refeicao) {

		if (idAutenticacao.contains("GER") || idAutenticacao.contains("FUN")) { 
			
			if(usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
				if (qtdPessoas > capacidadeRestaurante) {
					throw new RuntimeException(
							"QUANTIDADE DE PESSOAS CONVIDADAS NÃO DEVE EXCEDER A CAPACIDADE DO RESTAURANTE");
				}

				ReservaRestaurante reservaRestaurante = new ReservaRestaurante(idCliente, dataInicio, dataFim, qtdPessoas, refeicao);
				this.idReserva ++; 
				
				reservaRestaurante.setIdReserva(idReserva);
				reservasRestaurante.put(idReserva, reservaRestaurante);
				
				
				return "Reserva Restaurante Realizada!";		
			}
			
			return "Usuario não cadastrado";
			
			}
		
		return "Apenas gerente e funcionário podem efetuar Reserva";
	}

}
