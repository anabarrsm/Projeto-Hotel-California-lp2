package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Usuario;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;

public class UsuarioControllerTest {

	UsuarioController controller;

	@BeforeEach
	void setUp() {
		this.controller = new UsuarioController();
	}

	@Test
	public void testCadastrarUsuarioComSucesso() { 
		String resultado = controller.cadastrarUsuario("ADM1", "Ana Laura", "ADM", 145667); // [ADM2] Ana Laura
		assertEquals("USUÁRIO CADASTRADO COM SUCESSO!", resultado);
	}

	@Test
	public void testCadastrarUsuarioComTipoInvalido() {
		String usuario = controller.cadastrarUsuario("GER1", "Lucas Santos", "lalala", 987765);
		assertEquals("TIPO INVÁLIDO!", usuario);
	}

	@Test
	public void testCadastrarUsuarioComIdExistente() {
		controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", 123456); // [ADM2] Ana Laura
		assertEquals(controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", 123456), "USUÁRIO JÁ CADASTRADO!");
	}

	@Test

	public void testCadastrarGerenteDuplicado() {
		controller.cadastrarUsuario("ADM2", "André", "GER", 8831298);
		assertEquals("JÁ EXISTE UM GERENTE CADASTRADO!", controller.cadastrarUsuario("ADM2", "Lucas", "GER", 789012));
	}

	@Test
	public void testTentarCadastrarClientePorCliente() {
		assertEquals("CLIENTE NÃO PODE CADASTRAR USUÁRIO!",
				controller.cadastrarUsuario("CLI1", "Heitor Barros", "CLI", 678987));
	}

	@Test
	public void testTentarCadastrarFuncionarioPorFuncionario() {
		assertEquals("FUNCIONÁRIO SÓ PODE SER CADASTRADO POR ADMINISTRADOR OU GERENTE",
				controller.cadastrarUsuario("FUN3", "Carlos Lima", "FUN", 123443246));
	}

	@Test
	public void testTentarCadastrarFuncionarioPorCliente() {
		assertEquals("FUNCIONÁRIO SÓ PODE SER CADASTRADO POR ADMINISTRADOR OU GERENTE",
				controller.cadastrarUsuario("FUN1", "Carlos Lima", "FUN", 777777));
	}

	@Test
	public void testTentarCadastrarGerentePorFuncionario() {
		assertEquals("GERENTE SÓ PODE SER CADASTRADO POR ADMINISTRADOR",
				controller.cadastrarUsuario("GER1", "Carlos Lima", "GER", 123456888));
	}

	@Test
	public void testTentarCadastrarGerentePorCliente() {
		assertEquals("GERENTE SÓ PODE SER CADASTRADO POR ADMINISTRADOR",
				controller.cadastrarUsuario("GER1", "Carlos Lima", "GER", 888888));
	}

	@Test
	public void testTentarCadastrarAdministradorPorGerente() {
		assertEquals("ADMINISTRADOR SÓ PODE SER CADASTRADO POR OUTRO ADMINISTRADOR",
				controller.cadastrarUsuario("GER2", "Carlos Lima", "ADM", 9999999));

	}

	@Test
	public void testTentarCadastrarAdministradorPorFuncionario() {
		assertEquals("ADMINISTRADOR SÓ PODE SER CADASTRADO POR OUTRO ADMINISTRADOR",
				controller.cadastrarUsuario("FUN2", "Carlos Lima", "ADM", 1243242));

	}

	@Test
	public void testTentarCadastrarAdministradorPorCliente() {
		assertEquals("CLIENTE NÃO PODE CADASTRAR USUÁRIO!",
				controller.cadastrarUsuario("CLI2", "Carlos Lima", "ADM", 1243242));

	}

	@Test
	public void testAtualizarUsuarioComSucesso() {
		controller.cadastrarUsuario("ADM2", "Gabriel oliveira", "ADM", 789012); // [ADM2] Gabriel Oliveira
		controller.cadastrarUsuario("ADM3", "João pedro", "ADM", 284928);// [ADM3] Joao Pedro
		controller.cadastrarUsuario("ADM2", "Clara Gerente", "GER", 456); // [GER4] Clara Gerente
		String usuario = controller.atualizarUsuario("ADM1", "GER4", "FUN");
		assertEquals("USUÁRIO ATUALIZADO!", usuario);
	}
	
	@Test
	public void testAtualizarUsuario() {
		controller.cadastrarUsuario("ADM1", "Novo Funcionário", "FUN", 123456L); //[FUN2]
		String resultado = controller.atualizarUsuario("ADM1", "FUN2", "CLI");
		assertEquals("CLI", resultado);
		assertEquals("[FUN2] Novo Funcionário ", controller.exibirUsuario("CLI2"));
	}


	@Test
	public void testAtualizarUsuarioTipoInvalido() {
		controller.cadastrarUsuario("ADM2", "Ana Laura", "ADM", 789012);
		controller.cadastrarUsuario("ADM3", "Ana Oliveira", "ADM", 284929);
		try {
			String resultado = controller.atualizarUsuario("ADM2", "ADM3", "NOVO TIPO");
			fail("DEVERIA LANÇAR EXCEÇÃO");
		} catch (IllegalArgumentException e) {
			assertEquals("TIPO INVÁLIDO!", e.getMessage());
		}
	}

	@Test
	public void testExibirUsuario() {
		controller.cadastrarUsuario("ADM1", "João Costa", "ADM", 123456);
		assertEquals("[ADM1] João Costa (No. Doc. 123456)", controller.exibirUsuario("ADM1"));
	}

	@Test
	void testAtualizarUsuarioTipoInvalidoInexistente() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> this.controller.atualizarUsuario("ADM1", "CLI2", "MAR"));
		assertTrue(thrown.getMessage().contains("TIPO INVÁLIDO"));
	}

	@Test
	void testAtualizarUsuarioClienteTentaMudar() {
		controller.cadastrarUsuario("GER1", "GERENTE", "CLI", 78910);
		assertEquals("APENAS UM ADMINISTRADOR PODE ATUALIZAR OS USUÁRIOS.",
				this.controller.atualizarUsuario("GER1", "ADM1", "CLI"));
	}

	@Test
	void testAtualizarUsuarioDeuCerto() {
		controller.cadastrarUsuario("CLI2", "CLIENTE", "FUN", 45678);
		assertEquals("USUÁRIO ATUALIZADO!", this.controller.atualizarUsuario("ADM1", "CLI2", "ADM"));
	}

	@Test 
	public void testListarUsuarios() {
		controller.cadastrarUsuario("ADM1", "Maria Administradora", "ADM", 7896); // ADM2
		controller.cadastrarUsuario("ADM2", "Ana Gerente", "GER", 12345666); // GER3
		controller.cadastrarUsuario("GER1", "Lucas Funcionário", "FUN", 4569); // FUN 4
		controller.cadastrarUsuario("ADM2", "Helena Funcionária", "FUN", 5697); // FUN5

		String saida = "[[ADM1] João Costa (No. Doc. 123456), [ADM2] Maria Administradora (No. Doc. 7896), [GER3] Ana Gerente (No. Doc. 12345666), [FUN4] Lucas Funcionário (No. Doc. 4569), [FUN5] Helena Funcionária (No. Doc. 5697)]";

		assertEquals(Arrays.toString(controller.listarUsuarios()), saida);
	}

}
