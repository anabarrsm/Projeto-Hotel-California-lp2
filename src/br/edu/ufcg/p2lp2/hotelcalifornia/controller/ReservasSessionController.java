package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.util.ArrayList;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.ReservaQuarto;

public class ReservasSessionController {
	private ReservaQuartoController reservaController;
	private ReservaRestauranteController reservaRestauranteController;
	private ReservaAuditorioController reservaAuditorioController;
	//private ArrayList<> reservas;
	
	
	ReservasSessionController(ReservaQuartoController reservaController, ReservaRestauranteController reservaRestauranteController, ReservaAuditorioController reservaAuditorioController){
		this.reservaController = reservaController;
		this.reservaRestauranteController = reservaRestauranteController;
		this.reservaAuditorioController = reservaAuditorioController;

		
	}

	
	
}
