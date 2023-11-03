package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaQuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;
 
class QuartoControllerTest {
	 
	private QuartoController quartoController;
	private UsuarioController usuarioController;
	private String[] pedidos = {" 01 (uma) cama infantil", "01 (uma) roupa de cama adicional"};
	private String[] refeicoes = {"Cafe completo reforcado"};
	
	@BeforeEach
	void setUp() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController(usuarioController);
		
		//cadastrando Usuários
		this.usuarioController.cadastrarUsuario("ADM1","Maria Helena", "FUN", 111111); // [FUN2] Maria Helena
		
//		this.quartoController.disponibilizarQuartoSingle("FUN2", 601, 50.0, 100.0);
//		this.quartoController.disponibilizarQuartoDouble("FUN2",155 , 60.0, 100.0, pedidos);
//		this.quartoController.disponibilizarQuartoFamily("ADM1", 123, 50.0, 100.0, pedidos, 10);
//		
		
	}
//
//	@Test
//	void testUsuarioIndevido() {
//		assertEquals(this.quartoController.disponibilizarQuartoSingle("GER4", 123 , 50.0, 200.0), "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS");
//		assertEquals(this.quartoController.disponibilizarQuartoDouble("FUN2", 456 , 50.0, 200.0, pedidos), "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS");
//		assertEquals(this.quartoController.disponibilizarQuartoFamily("CLI2", 789 , 50.0, 200.0, pedidos, 6), "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS");
//		assertEquals(this.quartoController.disponibilizarQuartoSingle("ADM5", 123 , 50.0, 200.0), "ESSE ADMINISTRADOR NÃO ESTÁ CADASTRADO NO SISTEMA");
//		
//	}
	
	@Test
	void testDisponibilizarQuarto() {
		//assertEquals(this.quartoController.disponibilizarQuartoSingle("ADM1", 601, 50.0, 100.0), "QUARTO SINGLE DISPONÍVEL");
		//assertEquals(this.quartoController.disponibilizarQuartoDouble("ADM1", 301, 80.0, 20.0, pedidos), "QUARTO DOUBLE DISPONÍVEL");
		assertEquals(this.quartoController.disponibilizarQuartoFamily("ADM1", 502, 20.0, 80.0, pedidos, 10), "QUARTO FAMILY DISPONÍVEL");
	}
	
	
	
}

