package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;
 

/**
 * @author maria helena
 * 
 * testes unitários da classe QuartoContoller
 */
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
		this.usuarioController.cadastrarUsuario("ADM1", "OIOI", "GER", 456); //[GER3]
		this.usuarioController.cadastrarUsuario("ADM1", "blalal", "CLI", 4569878);
		
		
	}

	@Test
	void testUsuarioIndevidoQuartoSingle() {
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			quartoController.disponibilizarQuartoSingle("GER3", 101, 80.0, 20.0);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("USUARIO NAO E ADMINISTRADOR"));
		
		HotelCaliforniaException qsc = assertThrows(HotelCaliforniaException.class, () -> {
			quartoController.disponibilizarQuartoSingle("CLI4", 101, 80.0, 20.0);
		});
		assertTrue(qsc.getMessage().toUpperCase().contains("USUARIO NAO E ADMINISTRADOR"));
		
		HotelCaliforniaException qsf = assertThrows(HotelCaliforniaException.class, () -> {
			quartoController.disponibilizarQuartoSingle("FUN2", 101, 80.0, 20.0);
		});
		assertTrue(qsf.getMessage().toUpperCase().contains("USUARIO NAO E ADMINISTRADOR"));
	

	}
	
	
	@Test
	void testIndevidoQuartoDouble() {
		
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			quartoController.disponibilizarQuartoDouble("GER3", 101, 80.0, 20.0, pedidos);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("USUARIO NAO E ADMINISTRADOR"));
		
		HotelCaliforniaException qsc = assertThrows(HotelCaliforniaException.class, () -> {
			quartoController.disponibilizarQuartoDouble("CLI4", 101, 80.0, 20.0, pedidos);
		});
		assertTrue(qsc.getMessage().toUpperCase().contains("USUARIO NAO E ADMINISTRADOR"));
		
		HotelCaliforniaException qsf = assertThrows(HotelCaliforniaException.class, () -> {
			quartoController.disponibilizarQuartoDouble("FUN2", 101, 80.0, 20.0, pedidos);
		});
		assertTrue(qsf.getMessage().toUpperCase().contains("USUARIO NAO E ADMINISTRADOR"));
		
	}
	
	@Test
	void testIndevidoQuartoFamily() {
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			quartoController.disponibilizarQuartoFamily("GER3", 101, 80.0, 20.0, pedidos, 2);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("USUARIO NAO E ADMINISTRADOR"));
		
		HotelCaliforniaException qsc = assertThrows(HotelCaliforniaException.class, () -> {
			quartoController.disponibilizarQuartoFamily("CLI4", 101, 80.0, 20.0, pedidos, 7);
		});
		assertTrue(qsc.getMessage().toUpperCase().contains("USUARIO NAO E ADMINISTRADOR"));
		
		HotelCaliforniaException qsf = assertThrows(HotelCaliforniaException.class, () -> {
			quartoController.disponibilizarQuartoFamily("FUN2", 101, 80.0, 20.0, pedidos, 6);
		});
		assertTrue(qsf.getMessage().toUpperCase().contains("USUARIO NAO E ADMINISTRADOR"));
	}
	
	
	@Test
	void testDisponibilizarQuarto() {
		String quartoSingle = this.quartoController.disponibilizarQuartoSingle("ADM1", 601, 50.0, 100.0);
		String quartoDouble = this.quartoController.disponibilizarQuartoDouble("ADM1", 301, 80.0, 20.0, pedidos);
		String quartoFamily = this.quartoController.disponibilizarQuartoFamily("ADM1", 502, 20.0, 80.0, pedidos, 10);
		assertEquals(quartoSingle, quartoSingle.toString());
		assertEquals(quartoDouble, quartoDouble.toString());
		assertEquals(quartoFamily, quartoFamily.toString());
		
	}
	
	
	
}

