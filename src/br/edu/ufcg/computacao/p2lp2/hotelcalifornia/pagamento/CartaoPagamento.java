package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento;

import java.text.DecimalFormat;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.*;

public class CartaoPagamento extends FormaDePagamento {
	private String nomeTitular;
	private String numCartao;
	private String validade;
	private String codigoDeSeguranca;
	private int qtdeParcelas;
	private ReservaRestaurante reservaRestaurante;
	
	public CartaoPagamento(String nomeTitular, String numCartao, String validade, String codigoDeSeguranca, int qtdeParcelas) {
		super("Cartão", 0);
		this.nomeTitular = nomeTitular;
		this.numCartao = numCartao;
		this.validade = validade;
		this.codigoDeSeguranca = codigoDeSeguranca;
		this.qtdeParcelas = qtdeParcelas;
	}

    public String getNumeroCartao() {
    	if(numCartao.length() != 16) {
    		throw new IllegalArgumentException("NÚMERO DE CARTÃO INVÁLIDO");
    	}
        return numCartao;
    }

    public String getValidade() {
    	if(validade.length() != 7 || !validade.matches("\\\\d{2}/\\\\d{4}")) {
    		throw new IllegalArgumentException("DATA DE VALIDADE INVÁLIDA");
    	}
        return validade;
    }

    public String getCodigoSeguranca() {
    	if (codigoDeSeguranca.length() != 3 || !codigoDeSeguranca.matches("\\d{3}")) {
            throw new IllegalArgumentException("CÓDIGO DE SEGURANÇA INVÁLIDO");
        }
        return codigoDeSeguranca;
    }

    public int getNumeroParcelas() {
    	if(qtdeParcelas > 12) {
    		throw new IllegalArgumentException("O MÁXIMO DE PARCELAS SÃO 12");
    	}
    	return qtdeParcelas;
    }
    
    public void efetuarPagamento(long idReserva, double valorReserva, int qtdeParcelas) {
    	double valorDaParcela = valorReserva / qtdeParcelas;
    	double valorComDesconto = valorReserva;
    	
    	DecimalFormat df = new DecimalFormat("#.##");
    	
    	String saida = "SITUAÇÃO DO PAGAMENTO: " + reservaRestaurante.getSituacaoPagamento() + ".\n" + "[" + id + "] Forma de pagamento: " + tipoDePagamento + " (" + df.format(percentualDesconto * 100) + "% de desconto em pagamentos). " +
    	        "Total efetivamente pago: R$" + df.format(valorComDesconto) + " em " + qtdeParcelas + "x de R$" + df.format(valorDaParcela) + ".";
    }
    
    public String getNome() {
    	return nomeTitular;
    }
    
    
    public String exibirPagamento(String saida) {
    	return saida;
    }
}
