package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestauranteController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;


class ReservaRestauranteControllerTest {
	private ReservaRestauranteController reservaRestauranteController;

	@BeforeEach
	public void setUp() {
		 UsuarioController usuarioController = new UsuarioController();
	        QuartoController quartoController = new QuartoController();
	        ReservaController reservaController = new ReservaController(quartoController);
	        RefeicaoController refeicaoController = new RefeicaoController();
	        reservaRestauranteController = new ReservaRestauranteController(usuarioController, quartoController, reservaController, refeicaoController);
	}
	
	@Test
	public void testReservarRestauranteComCapacidadeAdequada() {
		LocalDate dataInicial = LocalDate.now();
		LocalDate dataFinal = LocalDate.now().plusDays(2);
		String refeicao = "Almoço";
		String resultado = reservaRestauranteController.reservarRestaurante("GER1", "CLI1", dataInicial, dataFinal, 2, refeicao);
        assertEquals("Reserva Restaurante Realizada!", resultado);
	}
	
	@Test
    public void testClienteNaoPodeCadastrarReservaRestaurante() {
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = LocalDate.now().plusDays(1); // Reserva com 1 dia de antecedência
        String refeicao = "Almoço"; 
        String resultado = reservaRestauranteController.reservarRestaurante("CLI1", "CLI1", dataInicial, dataFinal, 3, refeicao);
        assertEquals("Apenas gerente e funcionário podem efetuar Reserva", resultado);
    }
	
	@Test
    public void testAdminNaoPodeCadastrarReservaRestaurante() {
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = LocalDate.now().plusDays(1); 
        String refeicao = "Jantar";
        String resultado = reservaRestauranteController.reservarRestaurante("ADMIN1", "CLI2", dataInicial, dataFinal, 4, refeicao);
        assertEquals("Apenas gerente e funcionário podem efetuar Reserva", resultado);
    }
	
	@Test
    public void testNaoEPossivelReservarRestauranteJaExisteReserva() {
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = LocalDate.now().plusDays(1);
        String refeicao = "Almoço"; 
        reservaRestauranteController.reservarRestaurante("GER1", "CLI1", dataInicial, dataFinal, 3, refeicao);
        String resultado = reservaRestauranteController.reservarRestaurante("GER1", "CLI2", dataInicial, dataFinal, 4, refeicao);
        assertEquals("O restaurante já está reservado neste período.", resultado);
    }

}
