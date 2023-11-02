package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento;

public class CartaoPagamento extends Pagamento {
	private String numCartao;
	private String validade;
	private String codigoDeSeguranca;
	private int qtdeParcelas;
	
	public CartaoPagamento(long idReserva, String idCliente, String nomeTitular, String numCartao, String validade, String codigoDeSeguranca, int qtdeParcelas, double valorEfetivamentePago) {
		super(idReserva, idCliente, "Cartão", valorEfetivamentePago, nomeTitular);
		this.numCartao = numCartao;
		this.validade = validade;
		this.codigoDeSeguranca = codigoDeSeguranca;
		this.qtdeParcelas = qtdeParcelas;
	}

    public String getNumeroCartao() {
        return numCartao;
    }

    public String getValidade() {
        return validade;
    }

    public String getCodigoSeguranca() {
        return codigoDeSeguranca;
    }

    public int getNumeroParcelas() {
        return qtdeParcelas;
    }
}
