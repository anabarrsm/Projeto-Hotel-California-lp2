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
	public void testCadastrarUsuarioComSucesso() {
		String saida = controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "123456");
		assertEquals("USUÁRIO CADASTRADO COM SUCESSO!", saida);
	}
	
	@Test
	public void testCadastrarUsuarioComTipoInvalido() {
		String usuario = controller.cadastrarUsuario("GER1", "Lucas Santos", "lalala", "987765");
		assertEquals("TIPO INVÁLIDO!", usuario);
	}
	
	@Test
    public void testCadastrarUsuarioComIdExistente() {
        controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "123456");
        String resultado = controller.cadastrarUsuario("ADM2", "Maria", "ADM", "789012");
        assertEquals("ID INVÁLIDO!", resultado);
    }
	
	@Test
    public void testCadastrarGerenteDuplicado() {
        controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "123456");
        String resultado = controller.cadastrarUsuario("GER1", "Lucas", "GER", "789012");
        assertEquals("JÁ EXISTE UM GERENTE CADASTRADO!", resultado);
    }
	
	 @Test
	 public void testTentarCadastrarClientePorCliente() {
		 controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "789012");
	     controller.cadastrarUsuario("GER1", "Lucas Santos", "GER", "987765");
	     controller.cadastrarUsuario("CLI1", "Heitor Barros", "CLI", "678987");
	     String usuario = controller.cadastrarUsuario("CLI2", "Carlos Lima", "CLI", "123456");
	     assertEquals("CLIENTES NÃO PODEM CADASTRAR OUTROS CLIENTES.", usuario);
	    }
	 
}
