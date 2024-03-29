package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

/**
 * Classe controller de refeição.
 * 
 * @author José Lucas Silva Palmeira
 */

public class RefeicaoController {
	private ArrayList<Refeicao> refeicoes;
	private UsuarioController usuarioController;

	private long idRefeicao;
	public double valorReservaQuarto;

	public RefeicaoController(UsuarioController usuarioController) {
		this.refeicoes = new ArrayList<>();
		this.usuarioController = usuarioController;
		this.idRefeicao = 1;
	}

	/**
	 * Cria uma nova refeição e adiciona no ArrayList de refeições.
	 * 
	 * @param idAutenticacao / id da refeição.
	 * @param tipoRefeicao   / tipo da refeição(café da manhã, almoço, janta).
	 * @param titulo         / titulo ou descrição da refeição.
	 * @param horarioInicio  / hora de inicio.
	 * @param horarioFinal   / hora de fim.
	 * @param valor          / valor da refeição.
	 * @param disponivel     / disponibilidade da refeição.
	 * @return
	 */
	public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo,
			LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel) { 
		
		
		String tipoRefeicaoFormatado = formatarTipoRefeicao(tipoRefeicao);

		if(temRefeicao(titulo)) {
			throw new HotelCaliforniaException("REFEICAO JA EXISTE");
		}

		if (!usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}
		 
		if ((!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN"))) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA REFEICAO");
		}

		if (!tipoRefeicao.equals("CAFE_DA_MANHA") && !tipoRefeicao.equals("ALMOCO") && !tipoRefeicao.equals("JANTAR")) {
			throw new HotelCaliforniaException("TIPO INVÁLIDO");
		}

		if (horarioFinal.isBefore(horarioInicio)) {
			throw new HotelCaliforniaException("HORARIO DE FIM DEVE SER POSTERIOR AO HORARIO DE INICIO");
		}

		Refeicao refeicao = new Refeicao(tipoRefeicaoFormatado, titulo, horarioInicio, horarioFinal, valor, disponivel);

		refeicoes.add(refeicao);

		this.idRefeicao = refeicoes.indexOf(refeicao) + 1;

		refeicao.setIdRefeicao(idRefeicao);

		refeicao.setRefeicaoDisponivel(disponivel);

		return refeicao.toString();

	}

	public ArrayList<Refeicao> getRefeicoes() {
		return refeicoes;
	}
	
	
	public boolean temRefeicao(String titulo) {
		for(int i = 0; i < refeicoes.size(); i++) {
			if(refeicoes.get(i).getTitulo().equals(titulo)) {
				return true;
			}
		}
			
		return false;
	}

	/**
	 * Altera os valores que podem ser alterados em uma refeição.
	 * 
	 * @param idRefeicao    / id da refeição a ser alterada.
	 * @param horarioInicio / novo horario de inicio.
	 * @param horarioFinal  / novo horario de fim.
	 * @param disponivel    / disponibilidade da refeição.
	 * @return
	 */

	public String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal,
			double valorPorPessoa, boolean disponivel) {
		Refeicao refeicao = encontrarRefeicaoPorId(idRefeicao);
		if (refeicao == null) {
			throw new HotelCaliforniaException("REFEICAO NAO EXISTE");
		}

		if (horarioFinal.isBefore(horarioInicio)) {
			throw new HotelCaliforniaException("HORARIO DE FIM DEVE SER POSTERIOR AO HORARIO DE INICIO");
		}

		refeicao.setHorarioInicio(horarioInicio);
		refeicao.setHorarioFinal(horarioFinal);
		refeicao.setValorPorPessoa(valorPorPessoa);
		refeicao.setRefeicaoDisponivel(disponivel);

		return refeicao.toString();
	}

	private Refeicao encontrarRefeicaoPorId(long idRefeicao) {
		for (Refeicao refeicao : refeicoes) {
			if (refeicao.getIdRefeicao() == idRefeicao) {
				return refeicao;
			}
		}
		return null;
	}

	public String exibirRefeicaoPorId(long idRefeicao) { 
		for (Refeicao refeicao : refeicoes) {
			if (refeicao.getIdRefeicao() == idRefeicao) {
				return refeicao.toString();
			}
		}
		return "REFEIÇÃO NÃO DISPONÍVEL";
	}

	public String[] listarRefeicoes() {
		String[] listaRefeicoes = new String[refeicoes.size()];
		for (int i = 0; i < refeicoes.size(); i++) {
			listaRefeicoes[i] = refeicoes.get(i).toString();
		}

		return listaRefeicoes;
	}

	public Refeicao obterRefeicaoPeloId(String refeicao) {
	    Long idRefeicao = Long.parseLong(refeicao);
	    
	    for (Refeicao cadaRefeicao : refeicoes) {
	        if (cadaRefeicao.getIdRefeicao() == idRefeicao) {
	            return cadaRefeicao; // Use "cadaRefeicao" em vez de "refeicao"
	        }
	    }
	    
	    return null;
	}
	
	
	public String formatarTipoRefeicao(String tipoRefeicao) {
	    if(tipoRefeicao.equals("CAFE_DA_MANHA")) {
	    	return "Cafe-da-manha" ;
	    } else if (tipoRefeicao.equals("ALMOCO")) {
	    	return "Almoco";
	    } else if (tipoRefeicao.equals("JANTAR")) {
	    	return "Jantar";
	    }
	    return "TIPO INEXISTENTE";
	}

}

