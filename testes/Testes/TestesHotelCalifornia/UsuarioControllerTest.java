package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;



import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author maria helena
 * testes da Classe UsuarioController
 */

public class UsuarioControllerTest {

	UsuarioController controller;

	@BeforeEach
	void setUp() {
		this.controller = new UsuarioController();
	}

	@Test
	public void testCadastrarUsuarioComSucesso() { 
		String resultado = controller.cadastrarUsuario("ADM1", "Maria Helena", "ADM", 145667); // [ADM2] Maria Helena
		assertEquals("[ADM2] Maria Helena (No. Doc. 145667)", resultado);
	}

	@Test
	public void testCadastrarUsuarioComTipoInvalido() {
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.cadastrarUsuario("ADM2", "Novo Gerente", "GER", 123456L);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("USUARIO NAO EXISTE")); 
;
	}

	@Test

	public void testCadastrarGerenteDuplicado() {
		String resultado = controller.cadastrarUsuario("ADM1", "Novo Gerente", "GER", 123456L);
		assertTrue(resultado.contains("GER"));
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.cadastrarUsuario("ADM1", "Novo Gerente 2", "GER", 123456L);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("SO DEVE HAVER UM GERENTE NO HOTEL"));
	}


	@Test
	public void testTentarCadastrarClientePorCliente() {
		controller.cadastrarUsuario("ADM1", "Jesus", "CLI", 4569);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.cadastrarUsuario("CLI2", "Novo Funcionario", "FUN", 123456L);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
		assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UM NOVO USUARIO DO TIPO"));
		
	}

	@Test
	public void testTentarCadastrarFuncionarioPorFuncionario() {
		controller.cadastrarUsuario("ADM1", "aBC", "FUN", 7896);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.cadastrarUsuario("FUN2", "Novo Gerente", "GER", 123456L);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
		assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UM NOVO USUARIO DO TIPO"));
	}

	@Test
	public void testTentarCadastrarFuncionarioPorCliente() {
		controller.cadastrarUsuario("ADM1", "asdkasd", "CLI", 465);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.cadastrarUsuario("CLI2", "Novo Gerente", "FUN", 123456L);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
		assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UM NOVO USUARIO DO TIPO"));
	}

	@Test
	public void testTentarCadastrarGerentePorFuncionario() {
		controller.cadastrarUsuario("ADM1", "asdkasd", "FUN", 465);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.cadastrarUsuario("FUN2", "Novo Gerente", "GER", 123456L);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
		assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UM NOVO USUARIO DO TIPO"));
	}
	
	
	@Test
	public void testTentarCadastrarGerentePorCliente() {
		controller.cadastrarUsuario("ADM1", "asdkasd", "CLI", 465);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.cadastrarUsuario("CLI2", "Novo Gerente", "GER", 123456L);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
		assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UM NOVO USUARIO DO TIPO"));
	}

	@Test
	public void testTentarCadastrarAdministradorPorGerente() {
		controller.cadastrarUsuario("ADM1", "asdkasd", "GER", 465);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.cadastrarUsuario("GER2", "Novo Gerente", "ADM", 123456L);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
		assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UM NOVO USUARIO DO TIPO"));
	}
	


	@Test
	public void testTentarCadastrarAdministradorPorCliente() {
		controller.cadastrarUsuario("ADM1", "asdkasd", "CLI", 465);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.cadastrarUsuario("CLI2", "Novo Gerente", "ADM", 123456L);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
		assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UM NOVO USUARIO DO TIPO"));
	}

	@Test
	public void testAtualizatTipoInvalido() {
		controller.cadastrarUsuario("ADM1", "Novo Funcionario", "FUN", 123456L);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.atualizarUsuario("FUN2", "ADM1", "CLI");
		});
		assertTrue(hce.getMessage().toUpperCase().contains("APENAS O ADMINISTRADOR PODE ATUALIZAR OS USUARIOS"));

	}

	
	@Test
	public void testAtualizarUsuario() {
		controller.cadastrarUsuario("ADM1", "ASDA", "FUN" , 44696);
		String resultado = controller.atualizarUsuario("ADM1", "FUN2", "CLI");
		assertTrue(resultado.contains("CLI"));

	}
	
	
	@Test
	public void testAtualizarUsuarioTipoInvalido() {
		controller.cadastrarUsuario("ADM1", "Aaaa", "FUN", 789012);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.atualizarUsuario("ADM1", "FUN1", "GER");
		});
		assertTrue(hce.getMessage().toUpperCase().contains("USUARIO NAO EXISTE"));
	}
	


	@Test
	void testAtualizarUsuarioTipoInvalidoInexistente() {
		controller.cadastrarUsuario("ADM1", "Novo Funcionario", "FUN", 123456L);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			controller.atualizarUsuario("FUN2", "ADM1", "CLI");
		});
		assertTrue(hce.getMessage().toUpperCase().contains("APENAS O ADMINISTRADOR PODE ATUALIZAR OS USUARIOS"));

	}



	@Test 
	public void testListarUsuarios() {
		controller.cadastrarUsuario("ADM1", "Maria Administradora", "ADM", 7896); 
		controller.cadastrarUsuario("ADM2", "Ola", "GER", 12345666); 

		String saida = "[[ADM1] João Costa (No. Doc. 123456), [ADM2] Maria Administradora (No. Doc. 7896), [GER3] Ola (No. Doc. 12345666)]";

		assertEquals(Arrays.toString(controller.listarUsuarios()), saida);
	}	
}
