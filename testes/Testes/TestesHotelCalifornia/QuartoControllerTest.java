package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;

class QuartoControllerTest {
	
	private QuartoController quartoController;
	private UsuarioController usuarioController;
	private ReservaController reservaController;
	private String[] pedidos = {" 01 (uma) cama infantil", "01 (uma) roupa de cama adicional"};
	private String[] refeicoes = {"Cafe completo reforcado"};
	
	@BeforeEach
	void setUp() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController(usuarioController);
		this.reservaController = new ReservaController(usuarioController, quartoController);
		this.quartoController.disponibilizarQuartoSingle("ADM2", 601, 50.0, 100.0);
		this.quartoController.disponibilizarQuartoDouble("ADM3",155 , 60.0, 100.0, pedidos);
		this.quartoController.disponibilizarQuartoFamily("ADM4", 123, 50.0, 100.0, pedidos, 10);
		this.usuarioController.cadastrarUsuario("CLI2","OH DEUS", "FUN", "12345678");
		this.reservaController.reservarQuartoSingle("FUN1", "CLI2", 10, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 13, 14, 0, 0), refeicoes); // idReserva =  1
		this.usuarioController.cadastrarUsuario("ADM2","MARIA HELENA", "FUN", "123456");
		this.usuarioController.cadastrarUsuario("GER1","ANA LAURA", "CLI", "01234");
		this.usuarioController.cadastrarUsuario("ADM3", "LUCAS", "ADM", "789654");
	}

	@Test
	void testUsuarioIndevido() {
		assertEquals(this.quartoController.disponibilizarQuartoSingle("GER1", 123 , 50.0, 200.0), "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS");
		assertEquals(this.quartoController.disponibilizarQuartoDouble("FUN5", 456 , 50.0, 200.0, pedidos), "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS");
		assertEquals(this.quartoController.disponibilizarQuartoFamily("CLI3", 789 , 50.0, 200.0, pedidos, 6), "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS");
		assertEquals(this.quartoController.disponibilizarQuartoSingle("ADM5", 123 , 50.0, 200.0), "ESSE ADMINISTRADOR NÃO ESTÁ CADASTRADO NO SISTEMA");
		
	}
	
	@Test
	void testDisponibilizarQuarto() {
		assertEquals(this.quartoController.disponibilizarQuartoSingle("ADM3", 601, 50.0, 100.0), "QUARTO SINGLE DISPONÍVEL");
		assertEquals(this.quartoController.disponibilizarQuartoDouble("ADM3", 301, 50.0, 100.0, pedidos), "QUARTO DOUBLE DISPONÍVEL");
		assertEquals(this.quartoController.disponibilizarQuartoFamily("ADM3", 502, 50.00, 100.0, pedidos, 5), "QUARTO FAMILY DISPONÍVEL");
	}
	
	// testes da US03
	
//	@Test
//	void testReservarQuarto() {
//		assertEquals(this.reservaController.reservarQuartoSingle("GER1","CLI19" , 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "RESERVA QUARTO SINGLE REALIZADA");
//		assertEquals(this.reservaController.reservarQuartoDouble("FUN1", "CLI10", 155, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos), "RESERVA QUARTO DOUBLE REALIZADA");
//		assertEquals(this.reservaController.reservarQuartoFamily("FUN3", "CLI10", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos, 5), "RESERVA QUARTO FAMILY REALIZADA");
//}
//	
//	@Test
//	void testReservarAcimaLimitePessoas() {
//		assertEquals(this.reservaController.reservarQuartoFamily("FUN3", "CLI10", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos, 12), "O NUMERO DE PESSOAS SUPERA A QUANTIDADE MÁXIMA DE PESSOAS DESSE QUARTO");
//		
//	}
//	
//	@Test 
//	void testReservaIndevida() {
//		assertEquals(this.reservaController.reservarQuartoSingle("ADM1","CLI19" , 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO");
//		assertEquals(this.reservaController.reservarQuartoDouble("CLI1", "CLI10", 155, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes, pedidos), "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO");
//	}
//	 
//	void testReservaMenos24horas() {
//		assertEquals(this.reservaController.reservarQuartoSingle("FUN1", "CLI19", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 12, 17, 0, 0), refeicoes), "O período mínimo da reserva é de uma diária (24 horas).");
//		
//	}
//	
//	@Test  
//	void testReservaIndisponivel() {
//		this.reservaController.reservarQuartoSingle("FUN1", "CLI05", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 13, 14, 0, 0), refeicoes);
//		assertEquals(this.reservaController.reservarQuartoSingle("FUN1", "CLI05", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 13, 14, 0, 0), refeicoes), "O QUARTO NÃO ESTÁ DISPONÍVEL NO PERÍODO DESEJADO");
//	}
//
//	@Test
//	void testExibirReserva() {
//		assertEquals(this.reservaController.exibirReserva("FUN1",  0), "NAO SEI"); 
//}
}

