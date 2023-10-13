package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalTime;
import java.util.ArrayList;

public class RefeicaoController {
    private ArrayList<Refeicao> refeicoes = new ArrayList<>();

    public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo,
                                         LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel) {
        return null;
    }

    public String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal,
                                  boolean disponivel) {
        return null;
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
