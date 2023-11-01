package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.RefeicaoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaRestauranteController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


class ReservaRestauranteControllerTest {
	private ReservaRestauranteController reservaRestauranteController;
	private UsuarioController usuarioController;
	private RefeicaoController refeicaoController;
	private ReservaController reservaController;
	private QuartoController quartoController;

	@BeforeEach
	public void setUp() {
			this.usuarioController = new UsuarioController();
	        this.quartoController = new QuartoController(usuarioController);
	        this.reservaController = new ReservaController(usuarioController, quartoController);
	        this.refeicaoController = new RefeicaoController(usuarioController);
	        this.reservaRestauranteController = new ReservaRestauranteController(usuarioController, refeicaoController);
	        
	        
	      //cadastrando Usuarios
			this.usuarioController.cadastrarUsuario("ADM1", "Laura", "CLI", 5696); // [CLI2] Laura
			this.usuarioController.cadastrarUsuario("ADM1", "Joao", "FUN", 7899); // [FUN3] Joao
			this.usuarioController.cadastrarUsuario("ADM1", "Maria", "GER", 1111); // [GER4] Maria
	        this.usuarioController.cadastrarUsuario("FUN3", "Julia", "CLI", 33333); //[CLI5] Julia
	}
	
	@Test
	public void testReservarRestauranteComCapacidadeAdequada() {
		
		LocalDateTime dataAtual = LocalDateTime.now().plusDays(10);
		LocalDateTime dataInicioValida= dataAtual.plusDays(4);

		String refeicao = "Almoço";
		String resultado = reservaRestauranteController.reservarRestaurante("GER4", "CLI2", dataInicioValida, dataAtual, 2, refeicao);
        assertEquals("RESERVA RESTAURANTE REALIZADA", resultado);
	}
	
	@Test
    public void testClienteNaoPodeCadastrarReservaRestaurante() {
        LocalDateTime dataInicial = LocalDateTime.now();
        LocalDateTime dataFinal = LocalDateTime.now().plusDays(1); // Reserva com 1 dia de antecedência
        String refeicao = "Almoço"; 
        String resultado = reservaRestauranteController.reservarRestaurante("CLI2", "CLI2", dataInicial, dataFinal, 3, refeicao);
        assertEquals("APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR A RESERVA DO RESTAURANTE", resultado);
    }
	
	@Test
    public void testAdminNaoPodeCadastrarReservaRestaurante() {
        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFim = LocalDateTime.now().plusDays(1); 
        String refeicao = "Jantar";
        String resultado = reservaRestauranteController.reservarRestaurante("ADM1", "CLI2", dataInicio, dataFim, 4, refeicao);
        assertEquals("APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR A RESERVA DO RESTAURANTE", resultado);
    }
	
	@Test
    public void testNaoEPossivelReservarRestauranteJaExisteReserva() {
        LocalDateTime dataAtual= LocalDateTime.now();
        LocalDateTime dataInicioValida= dataAtual.plusDays(2);
        String refeicao = "Almoço"; 
        reservaRestauranteController.reservarRestaurante("GER4", "CLI2", dataInicioValida, dataAtual, 3, refeicao);
        String resultado = reservaRestauranteController.reservarRestaurante("GER4", "CLI5", dataInicioValida, dataAtual, 4, refeicao);
        assertEquals("O RESTAURANTE JÁ ESTÁ RESERVADO NESTE PERÍODO", resultado);
    }

}