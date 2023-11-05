package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento;

import java.text.DecimalFormat;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.FormaDePagamento;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;

public class DinheiroPagamento extends FormaDePagamento{
	private String nomeTitular;
	private ReservaRestaurante reservaRestaurante;
	
	public DinheiroPagamento(String nomeTitular) {
        super("Dinheiro", 0.10);
        this.nomeTitular = nomeTitular;
    }
	
	public void efetuarPagamento(double valorReserva, int qtdeParcelas) {
		double valorComDesconto = valorReserva - (valorReserva * 0.10);
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		String saida = "SITUAÇÃO DO PAGAMENTO: " + reservaRestaurante.getSituacaoPagamento() + ".\n" +
	               "[" + id + "] Forma de pagamento: " + tipoDePagamento + " (" + df.format(percentualDesconto * 100) + "% de desconto em pagamentos). " +
	               "Total efetivamente pago: R$" + df.format(valorComDesconto) + " em 1x de R$" + df.format(valorComDesconto) + ".";
	}
	
	public String exibirPagamento(String saida) {
		return saida;
	}
	
	public String getNome() {
		return nomeTitular;
	}
	
}
