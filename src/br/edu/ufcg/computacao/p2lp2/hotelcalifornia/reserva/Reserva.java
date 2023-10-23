package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Reserva {
		protected String idAutenticacao;
		protected String idCliente;
		protected int numQuarto;
		protected LocalDateTime dataInicio;
		protected LocalDateTime dataFim;
		protected String[] idRefeicoes;
		protected int idReserva;
		


		public Reserva(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] idRefeicoes) {
			this.numQuarto = numQuarto;
			this.dataInicio = dataInicio;
			this.dataFim = dataFim;
			this.idRefeicoes = idRefeicoes; 
			this.idReserva = 0;

			
		}

		public long getIdReserva() {
			return idReserva;
		}

		public void setIdReserva(int idReserva) {
			this.idReserva = idReserva;
		}

		public abstract String exibirReserva(); 
		
		
		public double getValorTotalRefeicoes() {
			double valorTotal = 0.0;
			for (String elem : this.idRefeicoes) {
				valorTotal += extrairValorDaString(elem);
			}
			return valorTotal;
		}

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
		
		public double extrairValorDaString(String inputString) {
		    // Use uma expressão regular para encontrar o valor após "R$"
		    Pattern pattern = Pattern.compile("R\\$(\\d+\\.\\d+)");
		    Matcher matcher = pattern.matcher(inputString);

		    if (matcher.find()) {
		        String valorStr = matcher.group(1);
		        double valor = Double.parseDouble(valorStr);
		        return valor;
		    }

		    // Se não encontrar um valor válido, retorne um valor padrão (por exemplo, -1)
		    return -1.0;
		}
		
	}

