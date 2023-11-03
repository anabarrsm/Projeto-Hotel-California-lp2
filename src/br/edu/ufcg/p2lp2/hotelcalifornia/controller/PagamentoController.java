package br.edu.ufcg.p2lp2.hotelcalifornia.controller;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento.Pagamento;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuarto;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

public class PagamentoController {
	private List<Pagamento> pagamentos;
	private long proximoPagamentoId = 1;
	private ArrayList <ReservaQuarto> reservas;
	
	
	public PagamentoController() {
		pagamentos = new ArrayList<>();
	}
	
	public ReservaQuarto encontrarReservaPorId(long idReserva) {
        for (ReservaQuarto reserva : reservas) {
            if (reserva.getIdReserva() == idReserva) {
                return reserva;
            }
        }
        return null; 
    }
			
	public String pagarReservaComDinheiro(String idCliente, long idReserva, String nomeTitular, ReservaQuarto[] reservas) {
		ReservaQuarto reserva = encontrarReservaPorId(idReserva);
		
		if(!reserva.getIdCliente().equals(idCliente)) {
			throw new HotelCaliforniaException("SOMENTE O PROPRIO CLIENTE PODERA PAGAR A SUA RESERVA");
		}
		
		double desconto = reserva.get
	}
}
 