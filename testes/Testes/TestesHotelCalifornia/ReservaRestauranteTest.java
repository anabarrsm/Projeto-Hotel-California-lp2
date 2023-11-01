package Testes.TestesHotelCalifornia;
import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.RefeicaoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaRestauranteController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRestauranteTest {
	private UsuarioController usuarioController;
	private RefeicaoController refeicaoController;
	private ReservaRestauranteController reservaRestauranteController;
	private LocalTime lt;
	private LocalTime lt2;

	@BeforeEach
	void setUp() {
		this.usuarioController = new UsuarioController();
		this.refeicaoController = new RefeicaoController(usuarioController);
		this.reservaRestauranteController = new ReservaRestauranteController(usuarioController, refeicaoController );
		
		//cadastrando Usuarios
		this.usuarioController.cadastrarUsuario("ADM1", "Lara", "CLI", 5696); // [CLI2] Lara
		this.usuarioController.cadastrarUsuario("ADM1", "Joao", "FUN", 7899); // [FUN3] Joao
		this.usuarioController.cadastrarUsuario("ADM1", "Maria", "GER", 1111); // [GER4] Maria
	}
	
	
//	@Test
//	public void reservarRestaurante() {
//		assertEquals(this.reservaRestauranteController.reservarRestaurante("FUN3", "CLI2", , null, 0, null))
//	}
//    @Test
//    public void testaToString(){
//        String retorno = restaurante1.toString();
//        assertEquals(retorno, "[<id>]  Reserva de RESTAURANTE em favor de: Lucas \n Detalhes da reserva: \n - Periodo: "  + datainicial + " ate " + dataFinal + "\n - Qtde. de Convidados: 50 pessoa(s) \n Refeicao incluida: " + refeicao1 + "\nVALOR TOTAL DA RESERVA: R$ " + restaurante1.getValorReservaRestaurante() + "x" + datainicial.until(dataFinal).getDays() + " (diarias)  => R$ " + restaurante1.calculaPreco() + "\n +  SITUACAO DO PAGAMENTO: " + restaurante1.situacaoPagamento());
//    }
}
 