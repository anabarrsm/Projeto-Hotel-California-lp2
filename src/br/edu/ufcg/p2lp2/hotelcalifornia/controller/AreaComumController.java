package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.time.LocalTime;
import java.util.HashMap;


import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.AreaComum;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

public class AreaComumController {
	
	private UsuarioController usuarioController;
	private HashMap <Long, AreaComum> areas;
	private long idAreaComum;

	
	public AreaComumController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
		this.areas = new HashMap<>();
		this.idAreaComum = 1;
	}
	
	public String disponibilizarAreaComum (String idAutenticacao, String tipoAreaComum, String titulo, LocalTime horarioInicio, LocalTime horarioFinal, double valorPessoa, boolean disponivel, int qtdMaxPessoas) {
		if(!idAutenticacao.contains("ADM")) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA AREA COMUM");
		}
		
		if(!usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}
		
		if ((horarioFinal.isBefore(horarioInicio)) || (horarioFinal.equals(horarioInicio))) {
			throw new HotelCaliforniaException("HORARIO DE FIM DEVE SER POSTERIOR AO HORARIO DE INICIO");
		}	
		
		if (verificaTitulo(titulo)) {
			throw new HotelCaliforniaException("AREA COMUM JA EXISTE");
		}
		
		AreaComum area = new AreaComum(titulo, tipoAreaComum, horarioInicio, horarioFinal, valorPessoa, disponivel, qtdMaxPessoas);
		
		area.setId(this.idAreaComum);
		
		this.areas.put(idAreaComum, area);
		
		this.idAreaComum++;	
		
		return area.toString();
		
		
	}

	private boolean verificaTitulo(String titulo) {
		for (Long area: this.areas.keySet()) {
			if (this.areas.get(area).getTitulo().equals(titulo)) {
				return true;
			}
		}
		return false;
	}
	
	
	public String alterarAreaComum(String idAutenticacao, long idAreaComum, LocalTime novoHorarioInicio, LocalTime novoHorarioFinal, double novoPreco, int capacidadeMax, boolean ativa) {
		if (!usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}
		if (!idAutenticacao.contains("ADM")) {
			throw new HotelCaliforniaException("NAO E POSSIVEL PARA USUARIO CADASTRAR UMA AREA COMUM");
		}
		if ((novoHorarioFinal.isBefore(novoHorarioInicio)) || (novoHorarioFinal.equals(novoHorarioInicio))) {
			throw new HotelCaliforniaException("HORARIO DE FIM DEVE SER POSTERIOR AO HORARIO DE INICIO");
		}
		if (verificaId(idAreaComum) == false) {
			throw new HotelCaliforniaException("AREA COMUM NAO EXISTE");
		}
		AreaComum area = this.areas.get(idAreaComum);
		area.setHorarioInicio(novoHorarioInicio);
		area.setHorarioFim(novoHorarioFinal);
		area.setQtdeMaxPessoas(capacidadeMax);
		area.setValorPessoa(novoPreco);
		mudaDisponibilidade(area, ativa);
		
		return area.toString();
	}
	
	private void mudaDisponibilidade(AreaComum area, boolean d) {
		if (d == true) {
			area.setDisponivel("VIGENTE.");
		} else {
			area.setDisponivel("INDISPONIVEL.");
		}
	}

	private boolean verificaId(long id) {
		if (this.areas.containsKey(id)) {
			return true;
		}
		return false;
	}
	
	
	
	public String exibirAreaComum(long idAreaComum) {
		if (verificaId(idAreaComum) == false) {
			throw new HotelCaliforniaException("AREA COMUM NAO EXISTE");
		}
		return this.areas.get(idAreaComum).toString();
	}
	
	public String[] listarAreasComuns() {
		String[] arr = new String[this.areas.size()];
		int i = 0;
		for (long l : this.areas.keySet()) {
			arr[i] = this.areas.get(l).toString();
			i++;
		}
		return arr;
	}
	
}
