package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaAuditorio {
    private String idAutenticacao;
    private String idUsuario;
    private long idAuditorio;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private boolean jaFoiPago;
    private int qtdConvidados;
    private int capacidadeAuditorio;

    // + reservarAuditorio(idAutenticacao: String, idCliente: String, idAuditorio: long, dataInicio: LocalDateTime, dataFim: LocalDateTime, qtdMaxPessoas: int): String

    public ReservaAuditorio(String idAutenticacao, String idUsuario, long idAuditorio,  LocalDateTime dataInicial, LocalDateTime dataFinal, int qtdConvidados){
        capacidadeAuditorio = 150;
        if(qtdConvidados <= capacidadeAuditorio){
            this.qtdConvidados = qtdConvidados;
        }else{
            throw new IllegalArgumentException("A CAPACIDADE MAXIMA É 150!");
        }
        this.idAutenticacao = idAutenticacao;
        this.idUsuario = idUsuario;
        this.idAuditorio = idAuditorio;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
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


}
