package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.FormaDePagamentoController;

public class FormaDePagamentoControllerTest {
	private FormaDePagamentoController controller;
	private UsuarioController usuarioController;
	
	@BeforeEach
	public void setUp() {
		this.controller = new FormaDePagamentoController();
		this.usuarioController = new UsuarioController();
		this.usuarioController.cadastrarUsuario("ADM1", "Aninha", "ADM", 9876);
		this.usuarioController.cadastrarUsuario("ADM1", "Isaac", "FUN", 9123);
	}

	 @Test
	 public void testDisponibilizarFormaDePagamentoAdmin() {
		 String resultado = controller.disponibilizarFormaDePagamento("ADM1", "Cartão", 5.0);
	     assertEquals("Forma de pagamento disponibilizada com sucesso!", resultado);
	 }
	 
	 @Test
	 public void testDisponibilizarFormaDePagamentoNaoAdmin() {
		 String resultado = controller.disponibilizarFormaDePagamento("FUN6", "PIX", 2.5);
		 assertEquals("Apenas ADM pode disponibilizar formas de pagamento!", resultado);
	 }
	 
	 @Test
	 public void testAlterarFormaDePagamentoAdmin() {
		 controller.disponibilizarFormaDePagamento("ADM1", "Cartão", 2.5);
		 String resultado = controller.alterarFormaDePagamento("ADM1", 1, "Cartão", 3.0);
		 assertEquals("Forma de pagamento alterada com sucesso!", resultado);
	 }
	 
	 @Test
	 public void testAlterarFormaDePagamentoNaoAdmin() {
		 controller.disponibilizarFormaDePagamento("ADM1", "Dinheiro", 5.8);
		 String resultado = controller.alterarFormaDePagamento("FUN6", 1, "Dinheiro", 3.0);
		 assertEquals("Apenas ADM pode alterar formas de pagamento!", resultado);
	 }

}
