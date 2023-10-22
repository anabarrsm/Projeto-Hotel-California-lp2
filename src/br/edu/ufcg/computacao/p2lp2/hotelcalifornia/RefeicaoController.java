package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Classe controller de refeição.
 * @author José Lucas Silva Palmeira
 */

public class RefeicaoController {
    private HashMap<Long, Refeicao> refeicoes;
	private UsuarioController usuarioController;
	private long idRefeicao;
	
	public RefeicaoController(UsuarioController usuarioController) {
		this.refeicoes = new HashMap<>();
    	this.usuarioController = usuarioController; 
    	this.idRefeicao = 0; 
    }

    /**
     * Cria uma nova refeição e adiciona no ArrayList de refeições.
     * @param idAutenticacao / id da refeição. 
     * @param tipoRefeicao / tipo da refeição(café da manhã, almoço, janta).
     * @param titulo / titulo ou descrição da refeição.
     * @param horarioInicio / hora de inicio.
     * @param horarioFinal / hora de fim.
     * @param valor / valor da refeição.
     * @param disponivel / disponibilidade da refeição.
     * @return
     */
    public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo, LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel) {
 
    	
    	if(idAutenticacao.contains("GER") || idAutenticacao.contains("FUN")) {
    		if(usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
    			
        Refeicao r = new Refeicao(tipoRefeicao, titulo, horarioInicio, horarioFinal, valor, disponivel);
        idRefeicao ++;
        
        r.setIdRefeicao(idRefeicao);
        refeicoes.put(idRefeicao, r);
    
        
        return "Refeição adicionada!";
        
    	}
    		
    	}
    	return "Apenas gerentes e funcionários podem disponibilizar refeições"; 
    }
    
    

    /**
     * Altera os valores que podem ser alterados em uma refeição.
     * @param idRefeicao / id da refeição a ser alterada.
     * @param horarioInicio / novo horario de inicio.
     * @param horarioFinal / novo horario de fim.
     * @param disponivel / disponibilidade da refeição.
     * @return
     */
    public String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal, boolean disponivel) {
    	
    	if(refeicoes.containsKey(idRefeicao)) {
    		  Refeicao r = refeicoes.get(idRefeicao);
    		    if (r != null) {
    		        r.setHoraInicio(horarioInicio);
    		        r.setHoraFim(horarioFinal);
    		        r.setRefeicaoDisponivel(disponivel);
    		        
    		        return "Refeição alterada";
    		    }
    		    
    		    }
    	 return "Refeição não existe";
    	}
    

    public String exibirRefeicao(long idRefeicao) {
    	
    	if(refeicoes.containsKey(idRefeicao)) {
    		
    		Refeicao r = refeicoes.get(idRefeicao);
    		return r.toString();
    		
    	}
    
    	return "Refeição não disponível";
    		
    }
    
    public String[] listarRefeicoes() {
        int contador = 0;
        
        for (Refeicao r : refeicoes.values()) {
            if (r != null) {
                contador++;
            }
        }
        
        String[] retorno = new String[contador];
        contador = 0;
        
        for (Refeicao r : refeicoes.values()) {
            if (r != null) {
                retorno[contador] = r.toString();
                contador++;
            }
        }
        
        return retorno;
    }



    public Refeicao encontraRefeicao(Refeicao r1) {
        return refeicoes.get(r1.getIdRefeicao());
        
        
}
    }
