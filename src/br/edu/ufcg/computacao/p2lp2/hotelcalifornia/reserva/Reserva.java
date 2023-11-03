package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;


import java.time.LocalDateTime;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;

public abstract class Reserva {
	
		protected UsuarioController usuarioController;
		protected String situacaoPagamento;
		protected LocalDateTime dataInicio;
		protected LocalDateTime dataFim;
		protected double valor;
		protected long idReserva;
		
		public Reserva(LocalDateTime dataInicio, LocalDateTime dataFim) {
			this.usuarioController = usuarioController;
			this.situacaoPagamento = "PENDENTE";
			this.dataFim = dataFim;
			this.dataInicio = dataInicio;
			this.valor = 0;
			this.idReserva = 0;
		}

		public long getIdReserva() {
			return idReserva;
		}

		public void setIdReserva(long idReserva) {
			this.idReserva = idReserva;
		}

		public double getValor() {
			return valor;
		}

		public String getSituacaoPagamento() {
			return situacaoPagamento;
		}


		public void setSituacaoPagamento(String situacaoPagamento) {
			this.situacaoPagamento = situacaoPagamento;
		}

		public LocalDateTime getDataInicio() {
			return dataInicio;
		}
		
		public LocalDateTime getDataFim() {
			return dataFim;
		}
	

		public abstract int calculaValor();
		
		public abstract String toString();

		public void setValor(double valor) {
			this.valor = valor;
		}
		
		
		public Usuario retornaUsuario(String idCliente) {
			return usuarioController.retornaUsuarioPorId(idCliente);
			
		}

	}

