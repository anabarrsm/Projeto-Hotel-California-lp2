package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento;

import java.text.DecimalFormat;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.FormaDePagamento;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.*;

public class PixPagamento extends FormaDePagamento {
	private ReservaRestaurante reservaRestaurante;
	private String nomeTitular;
	private String cpf;
	private String banco;
	
	public PixPagamento(String nomeTitular, String cpf, String banco) {
		super("Pix", 0.5);
		this.nomeTitular = nomeTitular;
		this.cpf = cpf;
		this.banco = banco;
	}
	
    public String getCpf() {
        return cpf;
    }

    public String getBanco() {
        return banco;
    }
    
    public String getNome() {
    	return nomeTitular;
    }
    
    public void efetuarPagamento(double valorReserva, int qtdeParcelas) {
    	double valorComDesconto = valorReserva - (valorReserva * 0.5);
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		String saida = "SITUAÇÃO DO PAGAMENTO: " + reservaRestaurante.getSituacaoPagamento() + ".\n" +
	               "[" + id + "] Forma de pagamento: " + tipoDePagamento + " (" + df.format(percentualDesconto * 100) + "% de desconto em pagamentos). " +
	               "Total efetivamente pago: R$" + df.format(valorComDesconto) + " em 1x de R$" + df.format(valorComDesconto) + ".";
    }
    
    public String exibirPagamento(String saida) {
    	return saida;
    }
}
