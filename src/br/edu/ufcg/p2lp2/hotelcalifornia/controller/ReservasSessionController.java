package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.util.ArrayList;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;

public class ReservasSessionController {
	private ReservaController reservaController;
	private ReservaRestauranteController reservaRestauranteController;
	private ReservaAuditorioController reservaAuditorioController;
	//private ArrayList<> reservas;
	
	
	ReservasSessionController(ReservaController reservaController, ReservaRestauranteController reservaRestauranteController, ReservaAuditorioController reservaAuditorioController){
		this.reservaController = reservaController;
		this.reservaRestauranteController = reservaRestauranteController;
		this.reservaAuditorioController = reservaAuditorioController;

		
	}

}
