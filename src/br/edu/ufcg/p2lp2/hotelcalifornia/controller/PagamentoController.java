package br.edu.ufcg.p2lp2.hotelcalifornia.controller;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento.CartaoPagamento;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento.DinheiroPagamento;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento.Pagamento;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento.PixPagamento;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;

public class PagamentoController {
	private List<Pagamento> pagamentos;
	private long proximoPagamentoId = 1;
	private ArrayList <Reserva> reservas;
	private ReservaRestaurante reservaRestaurante;
	
	
	public PagamentoController() {
		pagamentos = new ArrayList<>();
	}
			
	public String pagarReservaComDinheiro(String idCliente, long idReserva, String nomeTitular, ReservaRestaurante reservaRestaurante) {
		if (!idCliente.contains("CLI")) {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA PAGAR A SUA RESERVA");
		}
		
		if(reservaRestaurante.getIsPago() == true) {
			throw new HotelCaliforniaException("RESERVA JA FOI PAGA");
		}
		
		double desconto = reservaRestaurante.calculaValor() * 0.10;
		double valorEfetivamentePago = reservaRestaurante.calculaValor() - desconto;
		
		Pagamento pagamento = new DinheiroPagamento(idReserva, idCliente, valorEfetivamentePago, nomeTitular);
		pagamentos.add(pagamento);
		
		reservaRestaurante.marcarComoPaga();
		
		return "SITUACAO DO PAGAMENTO: REALIZADO";
	}
	
	public String pagarReservaComPix(String idCliente, long idReserva, String nomeTitular, String cpf, String banco) {
		if (!idCliente.contains("CLI")) {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA PAGAR A SUA RESERVA");
		}
		
		if(reservaRestaurante.getIsPago() == true) {
			throw new HotelCaliforniaException("RESERVA JA FOI PAGA");
		}
		
		double desconto = reservaRestaurante.calculaValor() * 0.05;
		double valorEfetivamentePago = reservaRestaurante.calculaValor() - desconto;
		
		Pagamento pagamento = new PixPagamento(idReserva, idCliente, valorEfetivamentePago, cpf, banco, nomeTitular);
		
		pagamentos.add(pagamento);
		
		reservaRestaurante.marcarComoPaga();
		
		return "SITUACAO DO PAGAMENTO: REALIZADO";
	}
	
	public String pagarReservaComCartao(String idCliente, long idReserva, String nomeTitular, String numCartao, String validade, String codigoDeSeguranca, int qtdeParcelas) {
		if (!idCliente.contains("CLI")) {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA PAGAR A SUA RESERVA");
		}
		
		if(reservaRestaurante.getIsPago() == true) {
			throw new HotelCaliforniaException("RESERVA JA FOI PAGA");
		}
		
		double valorEfetivamentePago = reservaRestaurante.calculaValor();
		
		Pagamento pagamento = new CartaoPagamento(idReserva, idCliente, nomeTitular, numCartao, validade, codigoDeSeguranca, qtdeParcelas, valorEfetivamentePago);
		pagamentos.add(pagamento);
		
		reservaRestaurante.marcarComoPaga();
		
		return "SITUACAO DO PAGAMENTO: REALIZADO";
	}
}
 