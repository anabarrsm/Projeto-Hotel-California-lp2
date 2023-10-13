package TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;

class QuartoControllerTest {
	
	private QuartoController quartoController;
	private String[] pedidos = {" 01 (uma) cama infantil", "01 (uma) roupa de cama adicional"};
	
	@BeforeEach
	void setUp() {
		this.quartoController = new QuartoController();
	}

	@Test
	void testUsuarioIndevido() {
		assertEquals(this.quartoController.disponibilizarQuartoSingle("GER1", 123 , 50.0, 200.0), "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS");
		assertEquals(this.quartoController.disponibilizarQuartoDouble("FUN5", 456 , 50.0, 200.0, pedidos), "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS");
		assertEquals(this.quartoController.disponibilizarQuartoFamily("CLI3", 789 , 50.0, 200.0, pedidos, 6), "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS");
	}
	
	@Test
	void testDisponibilizarQuartoSingle() {
		assertEquals(this.quartoController.disponibilizarQuartoSingle("ADM1", 301, 50.0, 100.0), "QUARTO SINGLE DISPONÍVEL");
		
	}

	@Test
	void testDisponibilizarQuartoDouble() {
		assertEquals(this.quartoController.disponibilizarQuartoDouble("ADM10", 301, 50.0, 100.0, pedidos), "QUARTO DOUBLE DISPONÍVEL");
		
	}
	
	@Test
	void testDisponibilizarQuartoFamily() {
		assertEquals(this.quartoController.disponibilizarQuartoFamily("ADM5", 502, 50.00, 100.0, pedidos, 5), "QUARTO FAMILY DISPONÍVEL");
		
	}

}
