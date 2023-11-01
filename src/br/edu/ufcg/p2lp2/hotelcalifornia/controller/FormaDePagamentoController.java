package br.edu.ufcg.p2lp2.hotelcalifornia.controller;
import java.util.List;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.FormaDePagamento;

import java.util.ArrayList;

public class FormaDePagamentoController {
	private List<FormaDePagamento> formasDePagamento;
	
	public FormaDePagamentoController() {
		formasDePagamento = new ArrayList<>();
	}
	
	public String disponibilizarFormaDePagamento(String idAutenticacao, String tipoDePagamento, double percentualDesconto) {
		if(idAutenticacao.contains("ADM")) {
			int novoId = formasDePagamento.size() + 1;
			FormaDePagamento novaForma = new FormaDePagamento(novoId, tipoDePagamento, percentualDesconto);
			formasDePagamento.add(novaForma);
			
			return "Forma de pagamento disponibilizada com sucesso!";
		}
		
		return "Apenas ADM pode disponibilizar formas de pagamento!";		
	}
	
	public String alterarFormaDePagamento(String idAutenticacao, int id, String tipoDePagamento, double percentualDesconto) {
		if(idAutenticacao.contains("ADM")) {
			for(FormaDePagamento f : formasDePagamento) {
				if(f.getId() == id) {
					f.setTipo(tipoDePagamento);
					f.setPercentual(percentualDesconto);
					return "Forma de pagamento alterada com sucesso!";
				}
			}
			return "Forma de pagamento não encontrada";
		}
		return "Apenas ADM pode alterar formas de pagamento!";
	}
}
