package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.util.List;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.FormaDePagamento;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

import java.util.ArrayList;

public class FormaDePagamentoController {
	private UsuarioController usuarioController;
	private List<FormaDePagamento> formasDePagamento;

	public FormaDePagamentoController(UsuarioController usuarioController) {
		formasDePagamento = new ArrayList<>();
		this.usuarioController = usuarioController;
	}

	public String disponibilizarFormaDePagamento(String idAutenticacao, String tipoDePagamento,
			double percentualDesconto) {

		if (!idAutenticacao.contains("ADM")) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA FORMA DE PAGAMENTO");
		}

		if (!usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}

		String tipoPagamentoFormatado = formataTipo(tipoDePagamento);

		int novoId = formasDePagamento.size() + 1;
		FormaDePagamento novaForma = new FormaDePagamento(novoId, tipoPagamentoFormatado, percentualDesconto);

		if (jahExiste(tipoPagamentoFormatado, percentualDesconto)) {
			throw new HotelCaliforniaException("FORMA DE PAGAMENTO JA EXISTE");
		}

		formasDePagamento.add(novaForma);

		return novaForma.toString();
	}

	private boolean jahExiste(String tipoPagamentoFormatado, double percentualDesconto) {
	    for (FormaDePagamento forma : formasDePagamento) {
	        if (forma.getTipo().equals(formataTipo(tipoPagamentoFormatado)) && forma.getPercentual() == percentualDesconto) {
	            return true;
	        }
	    }
	    return false;
	}


	public String alterarFormaDePagamento(String idAutenticacao, int id, String tipoDePagamento,
			double percentualDesconto) {
		if (idAutenticacao.contains("ADM")) {

			String tipoPagamentoFormatado = formataTipo(tipoDePagamento);

			for (FormaDePagamento f : formasDePagamento) {
				if (f.getId() == id) {
					f.setTipo(tipoPagamentoFormatado);
					f.setPercentual(percentualDesconto);
					return f.toString();
				}
			}
		}
		return "Apenas ADM pode alterar forma de pagamento";

	}

	private String formataTipo(String tipoDePagamento) {
		if (tipoDePagamento.equals("CARTAO_DE_CREDITO")) {
			return "CARTAO DE CREDITO";
		} else if (tipoDePagamento.equals("Cartão")) {
			return "CARTAO DE CREDITO";
		} else if (tipoDePagamento.equals("Dinheiro")) {
			return "DINHEIRO";
		}

		return tipoDePagamento;
	}

	public String exibirFormaDePagamento(int id) {
		for (FormaDePagamento f : formasDePagamento) {
			if (f.getId() == id) {
				return f.toString();
			}
		}
		return "Forma de pagamento não encontrada!";
	}

	public String[] listarFormasDePagamentos() {
		
	    String[] listaFormas = new String[formasDePagamento.size()];

	    for (int i = 0; i < formasDePagamento.size(); i++) {
	        listaFormas[i] = formasDePagamento.get(i).toString();
	    }

	    return listaFormas;
	}
}