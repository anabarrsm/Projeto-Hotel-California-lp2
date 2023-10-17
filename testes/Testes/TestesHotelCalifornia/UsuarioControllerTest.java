package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class UsuarioControllerTest {

	UsuarioController controller;
	
	@BeforeEach
	void setUp() {
		this.controller = new UsuarioController();
	}
	
	@Test
	public void testCadastrarUsuarioComSucesso() {
		String resultado = controller.cadastrarUsuario("ADM1", "Ana Laura", "ADM", "145667");
		assertEquals("USUÁRIO CADASTRADO COM SUCESSO!", resultado);
	}
	
	@Test
	public void testCadastrarUsuarioComTipoInvalido() {
		String usuario = controller.cadastrarUsuario("GER1", "Lucas Santos", "lalala", "987765");
		assertEquals("TIPO INVÁLIDO!", usuario);
	}
	
	@Test
    public void testCadastrarUsuarioComIdExistente() {
        controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "123456");
		String resultado = controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "123456");
        resultado = controller.cadastrarUsuario("ADM2", "Maria", "ADM", "789012");
		//System.out.println(resultado);
		//System.out.println(controller.cadastrarUsuario("ADM2", "Maria", "ADM", "789012"));
        assertEquals("ID INVÁLIDO!", resultado);
    }
	@Test
	
	public void testCadastrarGerenteDuplicado() {
        controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "123456");
        controller.cadastrarUsuario("ADM2", "Lucas", "GER", "789012");
        String resultado = controller.cadastrarUsuario("ADM2", "Lucas", "GER", "789012");
        assertEquals("JÁ EXISTE UM GERENTE CADASTRADO!", resultado);
    }
	
	 @Test
	 public void testTentarCadastrarClientePorCliente() {
		 controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "789012");
	     controller.cadastrarUsuario("GER1", "Lucas Santos", "GER", "987765");
	     controller.cadastrarUsuario("CLI1", "Heitor Barros", "CLI", "678987");
	     String usuario = controller.cadastrarUsuario("CLI2", "Carlos Lima", "CLI", "123456");
	     assertEquals("CLIENTE NÃO PODE CADASTRAR USUÁRIO!", usuario);
	    }
	 
	 @Test
	 public void testTentarCadastrarFuncionarioPorFuncionario() {
		 controller.cadastrarUsuario("FUN1", "Ana Laura", "FUN", "123456");
	     controller.cadastrarUsuario("FUN2", "Lucas Santos", "FUN", "987765");
	     String usuario = controller.cadastrarUsuario("FUN3", "Carlos Lima", "FUN", "123456");
	     assertEquals("FUNCIONÁRIO SÓ PODE SER CADASTRADO POR ADMINISTRADOR OU GERENTE", usuario);
	 }
	 
	 @Test
	 public void testTentarCadastrarFuncionarioPorCliente() {
		 controller.cadastrarUsuario("CLI1", "Ana Laura", "CLI", "123456");
	     controller.cadastrarUsuario("CLI2", "Lucas Santos", "CLI", "987765");
	     String usuario = controller.cadastrarUsuario("FUN1", "Carlos Lima", "FUN", "123456");
	     assertEquals("FUNCIONÁRIO SÓ PODE SER CADASTRADO POR ADMINISTRADOR OU GERENTE", usuario);
	 }
	 
	 @Test
	 public void testTentarCadastrarGerentePorFuncionario() {
		 controller.cadastrarUsuario("FUN1", "Ana Laura", "FUN", "123456");
	     controller.cadastrarUsuario("FUN2", "Lucas Santos", "FUN", "987765");
	     String usuario = controller.cadastrarUsuario("GER1", "Carlos Lima", "GER", "123456");
	     assertEquals("GERENTE SÓ PODE SER CADASTRADO POR ADMINISTRADOR", usuario);
	 }
	 
	 @Test
	 public void testTentarCadastrarGerentePorCliente() {
		 controller.cadastrarUsuario("CLI1", "Ana Laura", "CLI", "123456");
	     controller.cadastrarUsuario("CLI2", "Lucas Santos", "CLI", "987765");
	     String usuario = controller.cadastrarUsuario("GER1", "Carlos Lima", "GER", "123456");
	     assertEquals("GERENTE SÓ PODE SER CADASTRADO POR ADMINISTRADOR", usuario);
	 }
	 
	 @Test
	 public void testTentarCadastrarAdministradorPorGerente() {
		 controller.cadastrarUsuario("GER1", "Ana Laura", "GER", "123456");
	     controller.cadastrarUsuario("GER2", "Lucas Santos", "GER", "987765");
	     String usuario = controller.cadastrarUsuario("GER2", "Carlos Lima", "ADM", "123456");
	     assertEquals("ADMINISTRADOR SÓ PODE SER CADASTRADO POR OUTRO ADMINISTRADOR", usuario);
		 
	 }
	 
	 @Test
	 public void testTentarCadastrarAdministradorPorFuncionario() {
		 controller.cadastrarUsuario("FUN1", "Ana Laura", "FUN", "123456");
	     controller.cadastrarUsuario("FUN2", "Lucas Santos", "FUN", "987765");
	     String usuario = controller.cadastrarUsuario("FUN2", "Carlos Lima", "ADM", "123456");
	     assertEquals("ADMINISTRADOR SÓ PODE SER CADASTRADO POR OUTRO ADMINISTRADOR", usuario);
		 
	 }
	 
	 @Test
	 public void testTentarCadastrarAdministradorPorCliente() {
		 controller.cadastrarUsuario("CLI1", "Ana Laura", "CLI", "123456");
	     controller.cadastrarUsuario("CLI2", "Lucas Santos", "CLI", "987765");
	     String usuario = controller.cadastrarUsuario("CLI2", "Carlos Lima", "ADM", "123456");
	     assertEquals("CLIENTE NÃO PODE CADASTRAR USUÁRIO!", usuario);
		 
	 }
	 
	 @Test
	 public void testAtualizarUsuarioComSucesso() {
		 controller.cadastrarUsuario("ADM2", "Gabriel oliveira", "ADM", "789012");
		 controller.cadastrarUsuario("ADM3", "João pedro", "ADM", "284928");
		 String usuario = controller.atualizarUsuario("ADM2", "ADM3", "FUN");
		 assertEquals("USUÁRIO ATUALIZADO!", usuario);
		 
	 }
	 
	 @Test
	 public void testAtualizarUsuarioTipoInvalido() {
		 controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "789012");
		 controller.cadastrarUsuario("ADM3", "Ana Oliveira", "ADM", "284929");
		 try {
			 String resultado = controller.atualizarUsuario("ADM2", "ADM3", "lalala");
			 fail("DEVERIA LANÇAR EXCEÇÃO");
		 } catch (IllegalArgumentException e) {
			 assertEquals("TIPO INVÁLIDO!", e.getMessage());
		 }
	 }
	 
	 @Test
	 public void testAtualizarUsuarioComIdInexistente() {
		 controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", "789012");
		 
		 try {
			 String resultado = controller.atualizarUsuario("ADM2", "ADM56", "GER");
			 fail("DEVERIA LANÇAR EXCEÇÃO");
		 } catch (IllegalArgumentException e) {
			 assertEquals("ID NÃO EXISTE!", e.getMessage());
		 }
	 }
	 
	 @Test
	 public void testExibirUsuario() {
		 controller.cadastrarUsuario("ADM1", "João Costa", "ADM", "123456");
	     assertEquals("[ADM1] João Costa (No. Doc. 123456)", controller.exibirUsuario("ADM1"));
	 }
	 
	 @Test
	 public void testListarUsuarios() {
	        controller.cadastrarUsuario("ADM1", "João Costa", "ADM", "123456");
	        controller.cadastrarUsuario("GER1", "Maria Silva", "GER", "789012");
	        String[] usuarios = controller.listarUsuarios();
	        assertEquals(2, usuarios.length);
	    }
}
