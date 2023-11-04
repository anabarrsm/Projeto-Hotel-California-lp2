package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento;

public class DinheiroPagamento extends Pagamento{
	public DinheiroPagamento(long idReserva, String idCliente, double valorEfetivamentePago, String nomeTitular) {
        super(idReserva, idCliente, "Dinheiro", valorEfetivamentePago, nomeTitular);
    }
}
