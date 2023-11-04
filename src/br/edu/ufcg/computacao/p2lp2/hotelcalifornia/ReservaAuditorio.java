package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaAuditorio extends Reserva {
    private String idAutenticacao;
    private String idUsuario;
    private long idAuditorio;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private String jaFoiPago;
    private int qtdConvidados;

    // + reservarAuditorio(idAutenticacao: String, idCliente: String, idAuditorio: long, dataInicio: LocalDateTime, dataFim: LocalDateTime, qtdMaxPessoas: int): String

    public ReservaAuditorio(String idAutenticacao, String idUsuario, long idAuditorio,  LocalDateTime dataInicial, LocalDateTime dataFinal, int qtdConvidados){
        this.qtdConvidados = qtdConvidados;
        this.idAutenticacao = idAutenticacao;
        this.idUsuario = idUsuario;
        this.idAuditorio = idAuditorio;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        jaFoiPago = "SITUACAO DO PAGAMENTO: REALIZADO.";
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public int getQtdConvidados(){
        return this.qtdConvidados;
    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public void setIdAuditorio(long idAuditorio) {
        this.idAuditorio = idAuditorio;
    }

    @Override
    public int calculaValor() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}
