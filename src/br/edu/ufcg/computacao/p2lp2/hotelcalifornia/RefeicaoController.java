package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Classe controller de refeição.
 * @author José Lucas Silva Palmeira
 */

public class RefeicaoController {
    private ArrayList<Refeicao> refeicoes = new ArrayList<>();

    public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo, LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel) {
        long id = Long.parseLong(idAutenticacao);
        Refeicao r = new Refeicao(id, tipoRefeicao, titulo, horarioInicio, horarioFinal, valor, disponivel);
        refeicoes.add(r);
        return "Refeição adicionada!";
    }

    public String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal, boolean disponivel) {
        for (Refeicao r: refeicoes) {
            if(r.getId() == idRefeicao){
                 r.setHoraInicio(horarioInicio);
                 r.setHoraFim(horarioFinal);
                 r.setRefeicaoDisponivel(disponivel);
            }
           return "Refeição alterada";
        }
        return "Refeição não existe";
    }

    public String exibirRefeicao(long idRefeicao) {
        for (Refeicao r: refeicoes) {
            if(r.getId() == idRefeicao){
                return r.toString();
            }
        }
        return null;
    }

    public String[] listarRefeicoes() {
        // ArrayList retorno1 = new ArrayList();
        int contador = 0;
        String[] retorno = new String[refeicoes.size()];
        for (Refeicao r:refeicoes
             ) {retorno[contador] = r.toString() + "\n";
        }
        return retorno;
    }

}
