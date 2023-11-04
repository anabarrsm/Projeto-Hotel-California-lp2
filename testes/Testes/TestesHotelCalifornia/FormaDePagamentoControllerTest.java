package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.FormaDePagamentoController;

/**
 * @author maria helena
 * testes da classe FormaDePagamentoContoller
 */

public class FormaDePagamentoControllerTest {
	private FormaDePagamentoController controller;
	private UsuarioController usuarioController;
	
	@BeforeEach 
	public void setUp() {
		this.usuarioController = new UsuarioController();
		this.controller = new FormaDePagamentoController(usuarioController);
		usuarioController.cadastrarUsuario("ADM1", "SDKIEBF", "CLI", 7899);
		usuarioController.cadastrarUsuario("ADM1", "DJSFD", "GER", 5555);
		usuarioController.cadastrarUsuario("ADM1", "AKSDA", "FUN", 5566);
	}

	 @Test
	 public void testDisponibilizarFormaDePagamento() {
		 String resultado = controller.disponibilizarFormaDePagamento("ADM1",
					"CARTAO_DE_CREDITO", 0.0);

			String resultadoDin = controller.disponibilizarFormaDePagamento("ADM1",
					"DINHEIRO", 0.1);
			
			String resultadoPix = controller.disponibilizarFormaDePagamento("ADM1",
					"PIX", 0.05);
		assertEquals(resultado, "[1] Forma de pagamento: CARTAO DE CREDITO (0% de desconto em pagamentos)");
		assertEquals(resultadoDin, "[2] Forma de pagamento: DINHEIRO (10% de desconto em pagamentos)");
		assertEquals(resultadoPix, "[3] Forma de pagamento: PIX (5% de desconto em pagamentos)");
			
	 }
	 
	 @Test
	 void cadastrarComUsuarioNaoExiste() {
		 HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
				controller.disponibilizarFormaDePagamento("ADM2",
						"DINHEIRO", 0.1);
			});
			assertTrue(hce.getMessage().toUpperCase().contains("USUARIO NAO EXISTE"));
		}

	 @Test
	 void cadastrarPagamentoExistente() {
		 controller.disponibilizarFormaDePagamento("ADM1",
					"PIX", 0.05);
			HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
				controller.disponibilizarFormaDePagamento("ADM1",
						"PIX", 0.05);
			});
			assertTrue(hce.getMessage().toUpperCase().contains("FORMA DE PAGAMENTO JA EXISTE"));
	 }
	 
	 
	 @Test
	 void clienteCadastrando() {
			HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
				controller.disponibilizarFormaDePagamento("CLI2",
						"CARTAO_DE_CREDITO", 0.0);
			});
			assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
			assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UMA FORMA DE PAGAMENTO"));
	 }
	 
	 @Test
	 void gerenteCadastrando() {
			HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
				controller.disponibilizarFormaDePagamento("GER3",
						"PIX", 0.05);
			});
			assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
			assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UMA FORMA DE PAGAMENTO"));
		}
	 
	 
	 @Test
	 void funcionarioCadastrando() {
			HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
				controller.disponibilizarFormaDePagamento("FUN4",
						"DINHEIRO", 0.1);
			});
			assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
			assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UMA FORMA DE PAGAMENTO"));
		}

		 
	 @Test
	 void listar() {
		 controller.disponibilizarFormaDePagamento("ADM1",
					"DINHEIRO", 0.1);
			controller.disponibilizarFormaDePagamento("ADM1",
					"CARTAO_DE_CREDITO", 0.0);
			controller.disponibilizarFormaDePagamento("ADM1",
					"PIX", 0.05);
			String[] resultado = controller.listarFormasDePagamentos();
			assertAll(
					() -> assertEquals(3, resultado.length)
			);
	 }
}
