package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaAuditorio;

public class ReservaAuditorioController {
    private ArrayList<ReservaAuditorio> reservaAuditorios;
    private UsuarioController usuarioController;
    private int capacidadeAuditorio;
    private int idReservaAuditorio;

    public ReservaAuditorioController(UsuarioController usuarioController) {
        reservaAuditorios = new ArrayList<>();
        usuarioController = usuarioController;
        capacidadeAuditorio = 150;
        this.idReservaAuditorio = 1;
    }

    public String reservarAuditorio(String idAutenticacao, String idCliente, long idAuditorio, LocalDateTime dataInicio, LocalDateTime dataFim, int qtdPessoas) {

        if ((!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN"))) {
            return "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR A RESERVA DO AUDITORIO";
        }

        if (!idCliente.contains("CLI")) {
            return "RESERVAS SÓ PODEM SER FEITAS PARA CLIENTES";
        }

        if (!usuarioController.encontrarUsuarioPorId(idAutenticacao) || !usuarioController.encontrarUsuarioPorId(idCliente)) {
            return "USUÁRIO NÃO CADASTRADO";
        }

        if (!verificarDisponibilidadeAuditorio(dataInicio, dataFim)) {
            return "O RESTAURANTE JÁ ESTÁ RESERVADO NESTE PERÍODO";
        }


        if (qtdPessoas > capacidadeAuditorio) {
            return "A QUANTIDADE DE PESSOAS EXCEDE A CAPACIDADE DO AUDITORIO";
        }

        LocalDateTime dataMinimaReserva = LocalDateTime.now().plusDays(1);
        if (!dataInicio.isAfter(dataMinimaReserva)) {
            return "A RESERVA DO AUDITORIO DEVE TER PELO MENOS UM DIA DE ANTECEDENCIA.";
        }

        ReservaAuditorio reservaAuditorio = new ReservaAuditorio(idAutenticacao, idCliente, idAuditorio, dataInicio, dataFim, qtdPessoas);
        reservaAuditorios.add(reservaAuditorio);

        this.idReservaAuditorio = reservaAuditorios.indexOf(reservaAuditorio) + 1;

        reservaAuditorio.setIdAuditorio(idReservaAuditorio);

        return "RESERVA DE AUDITORIO REALIZADA";
    }

    private boolean verificarDisponibilidadeAuditorio(LocalDateTime dataInicio, LocalDateTime dataFim) {
        for (ReservaAuditorio reservas : reservaAuditorios) {
            if (!dataInicio.isAfter(reservas.getDataFinal()) && !dataFim.isBefore(reservas.getDataInicial())) {
                return false;
            }
        }

        return true;
    }
}

