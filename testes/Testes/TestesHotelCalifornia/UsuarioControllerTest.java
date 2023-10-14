package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;

public class UsuarioControllerTest {

	UsuarioController controller;
	
	@BeforeEach
	void setUp() {
		this.controller = new UsuarioController();
	}
	
	@Test
	public void testCadastrarAdmPorAdm() {
		String usuario = controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", 789012);
		assertEquals("USUÁRIO CADASTRADO COM SUCESSO!", usuario);
	}
	
	@Test
	public void testCadastrarAdmPorNaoAdm() {
		String usuario = controller.cadastrarUsuario("GER1", "Lucas Santos", "ADM", 987765);
		assertEquals("APENAS UM ADMINISTRADOR PODE CADASTRAR UM ADMINISTRADOR.", usuario);
	}
	
	@Test
	public void testCadastrarGerentePorGerente() {
		controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", 789012);
		String usuario = controller.cadastrarUsuario("GER1", "Lucas Santos", "GER", 987765);
		assertEquals("APENAS UM ADMINISTRADOR PODE CADASTRAR UM GERENTE.", usuario);
	}
	
	@Test
	public void testCadastrarClientePorGerente() {
		controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", 789012);
		controller.cadastrarUsuario("GER1", "Lucas Santos", "GER", 987765);
		String usuario = controller.cadastrarUsuario("CLI1", "Heitor Barros", "CLI", 678987);
		assertEquals("USUÁRIO CADASTRADO COM SUCESSO!", usuario);
	}
	
	 @Test
	 public void testTentarCadastrarClientePorCliente() {
		 controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", 789012);
	     controller.cadastrarUsuario("GER1", "Lucas Santos", "GER", 987765);
	     controller.cadastrarUsuario("CLI1", "Heitor Barros", "CLI", 678987);
	     String usuario = controller.cadastrarUsuario("CLI2", "Carlos Lima", "CLI", 123456);
	     assertEquals("CLIENTES NÃO PODEM CADASTRAR OUTROS CLIENTES.", usuario);
	    }
	
//	@Test
//	void testCadastrarUsuarioJaExistente() {
//		controller.cadastrarUsuario("ADM")
//	}
//	@Test
//	void testCadastrarMaisGerente() {
//		assertEquals("JÁ ESXISTE UM GERENTE CADASTRADO", sistema.cadastrarUsuario("ADM2", "Ana Gerente", "GER", 555));
//	}
}

