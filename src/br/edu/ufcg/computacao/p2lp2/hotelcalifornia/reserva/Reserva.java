package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;

import java.time.LocalDateTime;

public abstract class Reserva {


		protected String idAutenticacao;
		protected String idCliente;
		protected int numQuarto;
		protected LocalDateTime dataInicio;
		protected LocalDateTime dataFim;
		protected String[] idRefeicoes;


		public Reserva(String idAtutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] refeicoes) {
			this.idAutenticacao = idAutenticacao;
			this.numQuarto = numQuarto;
			this.dataInicio = dataInicio;
			this.dataFim = dataFim;
			this.idRefeicoes = idRefeicoes;

			
		}

		public abstract String exibirReserva();
		
		public abstract double calcularVQR();

		public String getIdAutenticacao() {
			return idAutenticacao;
		}
		
		public String getIdCliente() {
			return idCliente;
		}
		
		public int getNumQuarto() {
			return numQuarto;
		}
		
		public LocalDateTime getDataInicio() {
			return dataInicio;
		}
		
		public LocalDateTime getDataFim() {
			return dataFim;
		}
		
		public String[] getIdRefeicoes() {
			return idRefeicoes;
		}
		
	}

