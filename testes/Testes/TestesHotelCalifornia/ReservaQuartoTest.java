package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;

class ReservaQuartoTest {
	
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservaController reservaController;
	private String[] pedidos = {" 01 (uma) cama infantil", "01 (uma) roupa de cama adicional"};
	private String[] refeicoes = {"Cafe completo reforcado"};
	
	@BeforeEach
	void setUp() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController(usuarioController);
		this.reservaController = new ReservaController(usuarioController, quartoController);
		this.usuarioController.cadastrarUsuario("GER1", "ANA LAURA", "CLI", "12345");
		this.usuarioController.cadastrarUsuario("FUN1", "HELENA", "CLI", "4566");
		this.usuarioController.cadastrarUsuario("ADM2", "LUCAS", "CLI", "00111");
		this.quartoController.disponibilizarQuartoSingle("ADM2", 601, 50.0, 100.0);
		this.quartoController.disponibilizarQuartoDouble("ADM2", 155, 50.0, 100.0, pedidos);
		this.quartoController.disponibilizarQuartoFamily("ADM2", 123, 50.0, 100, pedidos, 10);
		
		
	}

	
	@Test
	void testReservarQuarto() {
		assertEquals(this.reservaController.reservarQuartoSingle("GER1","CLI19" , 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "RESERVA QUARTO SINGLE REALIZADA");
	//	assertEquals(this.reservaController.reservarQuartoDouble("FUN1", "CLI10", 155, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos), "RESERVA QUARTO DOUBLE REALIZADA");
	//	assertEquals(this.reservaController.reservarQuartoFamily("FUN3", "CLI10", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos, 5), "RESERVA QUARTO FAMILY REALIZADA");
}
	
	@Test
	void testReservarAcimaLimitePessoas() {
		//assertEquals(this.reservaController.reservarQuartoFamily("FUN3", "CLI10", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos, 12), "O NUMERO DE PESSOAS SUPERA A QUANTIDADE MÁXIMA DE PESSOAS DESSE QUARTO");
		
	}
	
	@Test 
	void testReservaIndevida() {
		assertEquals(this.reservaController.reservarQuartoSingle("ADM1","CLI19" , 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO");
		//assertEquals(this.reservaController.reservarQuartoDouble("CLI1", "CLI10", 155, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes, pedidos), "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO");
	}
	 
	void testReservaMenos24horas() {
		assertEquals(this.reservaController.reservarQuartoSingle("FUN1", "CLI19", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 12, 17, 0, 0), refeicoes), "O período mínimo da reserva é de uma diária (24 horas).");
		
	}
	
	@Test  
	void testReservaIndisponivel() {
		this.reservaController.reservarQuartoSingle("FUN1", "CLI05", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 13, 14, 0, 0), refeicoes);
		assertEquals(this.reservaController.reservarQuartoSingle("FUN1", "CLI05", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 13, 14, 0, 0), refeicoes), "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO");
	}

	@Test
	void testExibirReserva() {
		assertEquals(this.reservaController.exibirReserva("FUN1",  0), "NAO SEI"); 
}

	}

