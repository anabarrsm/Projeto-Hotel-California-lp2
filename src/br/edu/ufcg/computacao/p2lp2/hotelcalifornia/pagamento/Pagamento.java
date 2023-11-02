package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.pagamento;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Pagamento {
	protected long idReserva;
	protected String idCliente;
	protected String tipoDePagamento;
	protected double valorEfetivamentePago;
	protected String nomeTitular;
	
	public Pagamento(long idReserva, String idCliente, String tipoDePagamento, double valorEfetivamentePago, String nomeTitular) {
		this.idReserva = idReserva;
		this.idCliente = idCliente;
		this.tipoDePagamento = tipoDePagamento;
		this.valorEfetivamentePago = valorEfetivamentePago;
		this.nomeTitular = nomeTitular;
	}
	
	public long getIdReserva() {
        return idReserva;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getTipoPagamento() {
        return tipoDePagamento;
    }

    public double getValorEfetivamentePago() {
        return valorEfetivamentePago;
    }
    public String getNome() {
    	return nomeTitular;
    }

}
