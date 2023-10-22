package br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;


public class ReservaQuartoSingle extends Reserva {

	private long idReserva;
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private RefeicaoController refeicaoController;

	
	public ReservaQuartoSingle(String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] idRefeicoes) {
		super(idCliente, numQuarto, dataInicio, dataFim, idRefeicoes);
		this.idReserva = 0;
		this.usuarioController = usuarioController;
		this.quartoController = quartoController;
		this.refeicaoController = refeicaoController;
		
	}

	public long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(long idReserva) {
		this.idReserva = idReserva;
	}
	
	
	

	@Override
	public String exibirReserva() {
//		[<id>] Reserva de quarto em favor de:
//			- <usuario>
//			Detalhes da instalacao:
//			- <instalacao>
//			Detalhes da reserva:
//			- Periodo: <inicio> ate <fim>
//			- No. Hospedes: <numeroDeHospedes> pessoa(s)
//			- Refeicoes incluidas: [<refeicoes>]
//			VALOR TOTAL DA RESERVA: R$<valor> x<qtdDiarias> (diarias) => R$<valorTotal>
//			SITUACAO DO PAGAMENTO: PENDENTE.
//		UsuarioController usuariosCadastrados = 
		
		String cliente = "";
		ArrayList<Usuario> usuariosCadastrados = this.usuarioController.getUsuarios();
		for(int i = 0; i< usuariosCadastrados.size(); i++) {
			if(usuariosCadastrados.get(i).equals(idCliente)) {
				cliente =  usuariosCadastrados.get(i).toString();		
			}
		}
		
		
		String saida = "[" + idReserva + "]" + "Reserva de quarto em favor de: " + "\n"
		 + "-" + cliente + "\n"
//		 + "Detalhes da instalação:" + "\n" 
//		 + quarto + "\n" + 
//		 + "Detalhes da Reserva:" + "\n" 
//		 + "-Periodo: " + dataInicio + " até " + dataFim + "\n"
//		 + "- No Hospedes: 01 pessoa(s)" + "\n"
//		 + "- Refeiceos incluidas: " + idRefeicoes + "\n"
//		 + "VALOR TOTAL DA RESERVA: "
		 ;
		
		return saida;
	}
 
	

}
