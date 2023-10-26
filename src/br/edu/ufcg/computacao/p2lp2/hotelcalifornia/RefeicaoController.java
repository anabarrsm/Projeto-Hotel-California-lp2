package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

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

		if (idAutenticacao == null || idAutenticacao.isEmpty()
				|| (!idAutenticacao.contains("GER") && !idAutenticacao.contains("FUN"))) {
			return "APENAS GERENTES E FUNCIONÁRIOS PODEM DISPONIBILIZAR REFEIÇÕES";
		}

		if (!tipoRefeicao.equals("Café-da-manhã") && !tipoRefeicao.equals("Almoço") && !tipoRefeicao.equals("Jantar")) {
			return "TIPO DE REFEIÇÃO INVÁLIDO";
		}

		if (horarioFinal.isBefore(horarioInicio)) {
			return "O HORÁRIO DE FIM DEVE SER POSTERIOR AO HORÁRIO DE INÍCIO";
		}

		if (!usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			return "USUÁRIO NÃO CADASTRADO";
		}

		Refeicao refeicao = new Refeicao(tipoRefeicao, titulo, horarioInicio, horarioFinal, valor, disponivel);

		refeicoes.add(refeicao);

		this.idRefeicao = refeicoes.indexOf(refeicao) + 1;

		refeicao.setIdRefeicao(idRefeicao);

		refeicao.setRefeicaoDisponivel(disponivel);

		return "REFEIÇÃO DISPONIBILIZADA COM SUCESSO";

	}

	public ArrayList<Refeicao> getRefeicoes() {
		return refeicoes;
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
			return "REFEIÇÃO NÃO ENCONTRADA";
		}

		if (horarioFinal.isBefore(horarioInicio)) {
			return "O HORÁRIO DE FIM DEVE SER POSTERIOR AO HORÁRIO DE INÍCIO";
		}

		refeicao.setHorarioInicio(horarioInicio);
		refeicao.setHorarioFinal(horarioFinal);
		refeicao.setValorPorPessoa(valorPorPessoa);
		refeicao.setRefeicaoDisponivel(disponivel);

		return "REFEIÇÃO ALTERADA!";
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

}