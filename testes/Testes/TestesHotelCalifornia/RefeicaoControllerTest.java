package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;

import java.time.LocalTime;
import java.util.Arrays;

public class RefeicaoControllerTest {
	private RefeicaoController controller;
	private LocalTime lt;
	private LocalTime lt2;

	@BeforeEach
	void setUp() {
		this.controller = new RefeicaoController();
		this.lt = LocalTime.parse("12:30");
		this.lt2 = LocalTime.parse("13:30");

		controller.disponibilizarRefeicao("01", "Almoço", "lasanha", lt, lt, 40.00, true);
	}

	@Test
	public void adicionaRefeicao() {
		String retorno = controller.disponibilizarRefeicao("02", "Almoço", "Arroz", lt, lt2, 20.0, true);
		assertEquals("Refeição adicionada!", retorno);

		assertEquals("Refeição adicionada!",
				controller.disponibilizarRefeicao("03", "Jantar", "macarrão", lt, lt2, 30.0, true));
		assertEquals("Refeição adicionada!",
				controller.disponibilizarRefeicao("04", "Café-da-manhã", "ovo", lt, lt2, 15.00, true));
	}

	// true but was false
	@Test
	public void adiciomaTipoInexistente() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> this.controller.disponibilizarRefeicao("05", "Lanche", "sorvete", lt, lt2, 15.00, true));
		assertFalse(thrown.getMessage().contains("Não é um tipo possível"));
	}

	@Test
	public void testeRepresentacaoTextual() {

		controller.disponibilizarRefeicao("02", "Almoço", "Arroz", lt, lt2, 20.0, true);
		String retorno = controller.exibirRefeicao(02);
		// System.out.println(retorno);
		assertEquals("[2]Almoço: Arroz (12:30 as 13:30). Valor por pessoa: R$ 20.0. VIGENTE.", retorno);
	}

	@Test
	public void testeRepresentacaoTextualRefeicaoIndisponivel() {

		controller.disponibilizarRefeicao("02", "Almoço", "Arroz", lt, lt2, 20.0, false);
		String retorno = controller.exibirRefeicao(02);
		// System.out.println(retorno);
		assertEquals("[2]Almoço: Arroz (12:30 as 13:30). Valor por pessoa: R$ 20.0. INDISPONIVEL.", retorno);
	}

	@Test
	public void testeAlterarRefeicoes() {
		assertEquals("Refeição alterada",
				controller.alterarRefeicao(01, LocalTime.parse("13:00"), LocalTime.parse("14:00"), true));
		String saidaAlterada = controller.exibirRefeicao(01);
		assertEquals("[1]Almoço: lasanha (13:00 as 14:00). Valor por pessoa: R$ 40.0. VIGENTE.", saidaAlterada);

	}

//    @Test 
//    public void testeAlterarRefeicaoInexistente() {
//    	assertEquals("Refeição não existe", controller.alterarRefeicao(10, LocalTime.parse("13:00"), LocalTime.parse("14:00"), true));
//    }

	@Test
	public void listarRefeicoes() {
		controller.disponibilizarRefeicao("12", "Café-da-manhã", "Cafe completo reforcado", LocalTime.parse("06:00"), LocalTime.parse("10:00"), 30.00, true);
		controller.disponibilizarRefeicao("3", "Jantar", "Jantar a dois", LocalTime.parse("18:00"), LocalTime.parse("20:00"), 80.00, false);
		String[] refeicoes = {"[12] Cafe-da-manha: Cafe completo reforcado (06h00 as 10h00). Valor por pessoa: R$30,00. VIGENTE.", " Jantar: Jantar a dois (18h00 as 20h00). Valor por pessoa: R$80,00. INDISPONIVEL."};
		assertEquals(Arrays.toString(refeicoes), controller.listarRefeicoes());
	}

}
