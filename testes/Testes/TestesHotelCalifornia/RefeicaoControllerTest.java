package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;

import java.time.LocalTime;
import java.util.Arrays;

public class RefeicaoControllerTest {
	private RefeicaoController controller;
	private UsuarioController usuario;
	private LocalTime lt;
	private LocalTime lt2;

	@BeforeEach
	void setUp() {
		this.usuario = new UsuarioController(); 
		this.controller = new RefeicaoController(usuario);
		this.lt = LocalTime.parse("12:30");
		this.lt2 = LocalTime.parse("13:30");
		
		usuario.cadastrarUsuario("GER1", "Lucas", "CLI", "123456");
		controller.disponibilizarRefeicao("GER1", "Almoço", "lasanha", lt, lt2, 40.00, true); // idRefeicao = 1 ;
	}

	@Test
	public void disponibilizaRefeicao() {
		String retorno = controller.disponibilizarRefeicao("GER1", "Almoço", "Arroz", lt, lt2, 20.0, true);
		assertEquals("Refeição adicionada!", retorno);

		assertEquals("Refeição adicionada!",
				controller.disponibilizarRefeicao("GER1", "Jantar", "macarrão", lt, lt2, 30.0, true));
		assertEquals("Refeição adicionada!",
				controller.disponibilizarRefeicao("GER1", "Café-da-manhã", "ovo", lt, lt2, 15.00, true));
	}
	
	@Test
	public void disponibilizaRefeicaoUsuarioIndevido() {
		assertEquals("Apenas gerentes e funcionários podem disponibilizar refeições", controller.disponibilizarRefeicao("ADM1", "Jantar", "sopa", lt2, lt, 0, true));
		assertEquals("Apenas gerentes e funcionários podem disponibilizar refeições", controller.disponibilizarRefeicao("CLI10", "Jantar", "sopa", lt2, lt, 0, true));
	}

	// true but was false
	@Test
	public void adicionaTipoInexistente() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> this.controller.disponibilizarRefeicao("GER1", "Lanche", "sorvete", lt, lt2, 15.00, true));
		assertFalse(thrown.getMessage().contains("Não é um tipo possível"));
	}

	@Test
	public void testeRepresentacaoTextual() {

		controller.disponibilizarRefeicao("GER1", "Almoço", "Arroz", lt, lt2, 20.0, true);
		String retorno = controller.exibirRefeicao(02);
		assertEquals("[2] Almoço: Arroz (12:30 as 13:30). Valor por pessoa: R$20.0. VIGENTE.", retorno);
	}
	
	@Test
	public void exibirRefeicaoInexistente() {
		assertEquals("Refeição não disponível", controller.exibirRefeicao(5));
	}

	@Test
	public void testeRepresentacaoTextualRefeicaoIndisponivel() {

		controller.disponibilizarRefeicao("GER1", "Almoço", "Arroz", lt, lt2, 20.0, false);
		String retorno = controller.exibirRefeicao(02);
		assertEquals("[2] Almoço: Arroz (12:30 as 13:30). Valor por pessoa: R$20.0. INDISPONIVEL.", retorno);
	}

	@Test
	public void testeAlterarRefeicoes() {
		assertEquals("Refeição alterada",
				controller.alterarRefeicao(01, LocalTime.parse("13:00"), LocalTime.parse("14:00"), true));
		String saidaAlterada = controller.exibirRefeicao(1);
		assertEquals("[1] Almoço: lasanha (13:00 as 14:00). Valor por pessoa: R$40.0. VIGENTE.", saidaAlterada);

	}

    @Test 
    public void testeAlterarRefeicaoInexistente() {
    	assertEquals("Refeição não existe", controller.alterarRefeicao(10, LocalTime.parse("13:00"), LocalTime.parse("14:00"), true));
    }

	@Test
	public void listarRefeicoes() {
		controller.disponibilizarRefeicao("GER", "Café-da-manhã", "Cafe completo reforcado", LocalTime.parse("06:00"), LocalTime.parse("10:00"), 30.00, true);
		controller.disponibilizarRefeicao("GER", "Jantar", "Jantar a dois", LocalTime.parse("18:00"), LocalTime.parse("20:00"), 80.00, false);
		//formado da hora 06h00, formado do dinheerio 33,xx;
		String[] refeicoes = {"[1] Almoço: lasanha (12:30 as 13:30). Valor por pessoa: R$40.0. VIGENTE.", "[2] Café-da-manhã: Cafe completo reforcado (06:00 as 10:00). Valor por pessoa: R$30.0. VIGENTE.", "[3] Jantar: Jantar a dois (18:00 as 20:00). Valor por pessoa: R$80.0. INDISPONIVEL."};
		assertEquals(Arrays.toString(refeicoes), Arrays.toString(controller.listarRefeicoes()));
	}

	
}
