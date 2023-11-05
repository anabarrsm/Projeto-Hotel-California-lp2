package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

/**
 * @author Ana Laura Barros de Melo - 122210847
 * Classe que implementa a forma de pagamento
 */

import java.util.List;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.FormaDePagamento;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento.*;

import java.util.ArrayList;

public class FormaDePagamentoController {
	private UsuarioController usuarioController;
	private CartaoPagamento cartaoPagamento;
	private DinheiroPagamento dinheiroPagamento;
	private ReservaRestaurante reservaRestaurante;
	private PixPagamento pixPagamento;
	private List<FormaDePagamento> formasDePagamento;

	public FormaDePagamentoController(UsuarioController usuarioController) {
		formasDePagamento = new ArrayList<>();
		this.usuarioController = usuarioController;
	}
	
	/**
	 * Disponibiliza forma de pagamento
	 * @param idAutenticacao
	 * @param tipoDePagamento
	 * @param percentualDesconto
	 * @return
	 */

	public String disponibilizarFormaDePagamento(String idAutenticacao, String tipoDePagamento,
			double percentualDesconto) {
		/**
		 * Lança exceção caso o autenticante não seja adm
		 */

		if (!idAutenticacao.contains("ADM")) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA FORMA DE PAGAMENTO");
		}
		
		/**
		 * Lança exceção caso o usuário não exista
		 */

		if (!usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}

		String tipoPagamentoFormatado = formataTipo(tipoDePagamento);
		
		if (jahExiste(tipoPagamentoFormatado, percentualDesconto)) {
			throw new HotelCaliforniaException("FORMA DE PAGAMENTO JA EXISTE");
		}
		
		FormaDePagamento novaForma;
		
		switch(tipoPagamentoFormatado) {
		case "CARTAO DE CREDITO":
			novaForma = new CartaoPagamento(cartaoPagamento.getNome(), cartaoPagamento.getNumeroCartao(), cartaoPagamento.getValidade(), cartaoPagamento.getCodigoSeguranca(), cartaoPagamento.getNumeroParcelas());
			break;
			
		case "DINHEIRO":
			novaForma = new DinheiroPagamento(dinheiroPagamento.getNome());
			break;
			
		case "PIX":
			novaForma = new PixPagamento(pixPagamento.getNome(), pixPagamento.getCpf(), pixPagamento.getBanco());
			break;
		default:
			throw new HotelCaliforniaException("TIPO DE PAGAMENTO DESCONHECIDO");
		}
		
		novaForma.setTipo(tipoPagamentoFormatado);
		novaForma.setPercentual(percentualDesconto);
		formasDePagamento.add(novaForma);
		
		return novaForma.exibirPagamento("SITUAÇÃO DO PAGAMENTO: PENDENTE");
		
		
	}
	
	/**
	 * Método que verifica se já existe forma de pagamento
	 * @param tipoPagamentoFormatado
	 * @param percentualDesconto
	 * @return
	 */

	private boolean jahExiste(String tipoPagamentoFormatado, double percentualDesconto) {
	    for (FormaDePagamento forma : formasDePagamento) {
	        if (forma.getTipo().equals(formataTipo(tipoPagamentoFormatado)) && forma.getPercentual() == percentualDesconto) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * Altera forma de pagamento
	 * @param idAutenticacao
	 * @param id
	 * @param tipoDePagamento
	 * @param percentualDesconto
	 * @return
	 */


	public String alterarFormaDePagamento(String idAutenticacao, int id, String tipoDePagamento,
			double percentualDesconto) {
		if (idAutenticacao.contains("ADM")) {

			String tipoPagamentoFormatado = formataTipo(tipoDePagamento);

			for (FormaDePagamento f : formasDePagamento) {
				if (f.getId() == id) {
					f.setTipo(tipoPagamentoFormatado);
					f.setPercentual(percentualDesconto);
					return f.exibirPagamento("SITUAÇÃO DO PAGAMENTO: PENDENTE");
				}
			}
		}
		return "Apenas ADM pode alterar forma de pagamento";

	}

	private String formataTipo(String tipoDePagamento) {
		if (tipoDePagamento.equals("CARTAO_DE_CREDITO")) {
			return "CARTAO DE CREDITO";
		} else if (tipoDePagamento.equals("Cartão")) {
			return "CARTAO DE CREDITO";
		} else if (tipoDePagamento.equals("Dinheiro")) {
			return "DINHEIRO";
		}

		return tipoDePagamento;
	}
	
	/**
	 * Exibe forma de pagamento
	 * @param id
	 * @return
	 */
	
	public String pagarReservaComDinheiro(String idCliente, long idReserva, String nomeTitular) {
		if (!idCliente.contains("CLI")) {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA PAGAR A SUA RESERVA");
		}
		
		if(reservaRestaurante.getIsPago() == true) {
			throw new HotelCaliforniaException("RESERVA JA FOI PAGA");
		}
		
		double valorReserva = reservaRestaurante.getValor();
		double valorComDesconto = valorReserva -(valorReserva * 0.10);
		
		
		
		return "SITUACAO DO PAGAMENTO: REALIZADO";
	}

	public String exibirFormaDePagamento(int id) {
		for (FormaDePagamento f : formasDePagamento) {
			if (f.getId() == id) {
				return f.exibirPagamento("SITUAÇÃO DO PAGAMENTO: PENDENTE");
			}
		}
		return "Forma de pagamento não encontrada!";
	}
	
	/**
	 * lista as formas de pagamentos
	 * @return
	 */

	public String[] listarFormasDePagamentos() {
		
	    String[] listaFormas = new String[formasDePagamento.size()];

	    for (int i = 0; i < formasDePagamento.size(); i++) {
	        listaFormas[i] = formasDePagamento.get(i).exibirPagamento("SITUAÇÃO DO PAGAMENTO: PENDENTE");
	    }

	    return listaFormas;
	}
}