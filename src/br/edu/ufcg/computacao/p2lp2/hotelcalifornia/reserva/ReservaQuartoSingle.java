package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;

import java.time.LocalDateTime;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoSingle;

public class ReservaQuartoSingle extends Reserva {
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private RefeicaoController refeicaoController;

	public ReservaQuartoSingle(String idAtutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] refeicoes) {
		super(idAtutenticacao, idCliente, numQuarto, dataInicio, dataFim, refeicoes);
		
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController();
		this.refeicaoController = new RefeicaoController();
;
	}

	@Override
	public String exibirReserva() {
	String cliente = this.usuarioController.exibirUsuario(idCliente);
	String quartoSingle = this.quartoController.exibirQuarto(numQuarto);
	
	String saida = 
			"Reserva de quarto em favor de: + \n" + "[" +idCliente + "] " + cliente + "\n"+
	        "Detalhes da Instalação: " + "\n" + "[" + numQuarto + "]" + quartoSingle + "\n" + 
	        "Detalhes da Reserva:\n" + "- Periodo: " + dataInicio + "até" + dataFim + 
	        "\n- No. Hospedes: 01 pessoa(s)" + "\n" + 
	        "- Refeicoes incluidas: [ \n" + refeicoes + 
	        ;
		return saida;
	}

	@Override
	public double calcularVQR() {
		// TODO Auto-generated method stub
		return 0;
	}

}
