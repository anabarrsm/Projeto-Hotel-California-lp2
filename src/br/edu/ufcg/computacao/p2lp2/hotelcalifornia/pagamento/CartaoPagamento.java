package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento;

public class CartaoPagamento extends Pagamento {
	private String nomeTitular;
	private String numCartao;
	private String validade;
	private String codigoDeSeguranca;
	private int qtdeParcelas;
	
	public CartaoPagamento(long idReserva, String idCliente, String nomeTitular, String numCartao, String validade, String codigoDeSeguranca, int qtdeParcelas, double valorEfetivamentePago) {
		super(idReserva, idCliente, "Cartão", valorEfetivamentePago);
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
    
    public String getNome() {
    	return nomeTitular;
    }
}
