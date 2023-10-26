package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestauranteController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


class ReservaRestauranteControllerTest {
	private ReservaRestauranteController reservaRestauranteController;

	@BeforeEach
	public void setUp() {
		 	UsuarioController usuarioController = new UsuarioController();
	        QuartoController quartoController = new QuartoController(usuarioController);
	        ReservaController reservaController = new ReservaController(usuarioController, quartoController);
	        RefeicaoController refeicaoController = new RefeicaoController(usuarioController);
	        reservaRestauranteController = new ReservaRestauranteController(usuarioController, refeicaoController);
	}
	
	@Test
	public void testReservarRestauranteComCapacidadeAdequada() {
		LocalDateTime dataInicio = LocalDateTime.now();
		LocalDateTime dataFim = LocalDateTime.now().plusDays(2);
		String refeicao = "Almoço";
		String resultado = reservaRestauranteController.reservarRestaurante("GER1", "CLI1", dataInicio, dataFim, 2, refeicao);
        assertEquals("Reserva Restaurante Realizada!", resultado);
	}
	
	@Test
    public void testClienteNaoPodeCadastrarReservaRestaurante() {
        LocalDateTime dataInicial = LocalDateTime.now();
        LocalDateTime dataFinal = LocalDateTime.now().plusDays(1); // Reserva com 1 dia de antecedência
        String refeicao = "Almoço"; 
        String resultado = reservaRestauranteController.reservarRestaurante("CLI1", "CLI1", dataInicial, dataFinal, 3, refeicao);
        assertEquals("APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR A RESERVA DO RESTAURANTE", resultado);
    }
	
	@Test
    public void testAdminNaoPodeCadastrarReservaRestaurante() {
        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFim = LocalDateTime.now().plusDays(1); 
        String refeicao = "Jantar";
        String resultado = reservaRestauranteController.reservarRestaurante("ADMIN1", "CLI2", dataInicio, dataFim, 4, refeicao);
        assertEquals("APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR A RESERVA DO RESTAURANTE", resultado);
    }
	
	@Test
    public void testNaoEPossivelReservarRestauranteJaExisteReserva() {
        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFim = LocalDateTime.now().plusDays(1);
        String refeicao = "Almoço"; 
        reservaRestauranteController.reservarRestaurante("GER1", "CLI1", dataInicio, dataFim, 3, refeicao);
        String resultado = reservaRestauranteController.reservarRestaurante("GER1", "CLI2", dataInicio, dataFim, 4, refeicao);
        assertEquals("O restaurante já está reservado neste período.", resultado);
    }

}