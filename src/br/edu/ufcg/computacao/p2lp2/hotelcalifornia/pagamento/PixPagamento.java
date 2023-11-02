package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento;

public class PixPagamento extends Pagamento {
	private String nomeTitular;
	private String cpf;
	private String banco;
	
	public PixPagamento(long idReserva, String idCliente, double valorEfetivamentePago, String nomeTitular, String cpf, String banco) {
		super(idReserva, idCliente, "Pix", valorEfetivamentePago, nomeTitular);
		this.cpf = cpf;
		this.banco = banco;
	}
	
    public String getCpf() {
        return cpf;
    }

    public String getBanco() {
        return banco;
    }
}
