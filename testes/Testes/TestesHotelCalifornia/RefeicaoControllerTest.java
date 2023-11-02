package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.RefeicaoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class RefeicaoControllerTest {
	private RefeicaoController refeicaoController;
	private UsuarioController usuarioController;
	private LocalTime lt;
	private LocalTime lt2;

	@BeforeEach
	void setUp() {
		this.usuarioController = new UsuarioController(); 
		this.refeicaoController = new RefeicaoController(usuarioController);
		this.lt = LocalTime.parse("12:30");
		this.lt2 = LocalTime.parse("13:30");
		
		//cadastrando Usuarios
		this.usuarioController.cadastrarUsuario("ADM1", "Lucas", "GER", 11111); //[GER2] Lucas
		this.usuarioController.cadastrarUsuario("GER2", "Jesus", "FUN", 69850); // [FUN3] Jesus
		this.usuarioController.cadastrarUsuario("ADM1", "Maria", "CLI", 4516); // [CLI4] Maria
		
	}

	@Test
	public void disponibilizaRefeicao() {
		assertEquals(this.refeicaoController.disponibilizarRefeicao("GER2", "CAFE-DA-MANHA", "Cafe completo reforcado", lt, lt2, 30.0, true), "REFEIÇÃO DISPONIBILIZADA COM SUCESSO");
	//	assertEquals(this.refeicaoController.disponibilizarRefeicao("GER2", "Almoço", "Lasanha", lt, lt2, 45.50, true), "REFEIÇÃO DISPONIBILIZADA COM SUCESSO");
	//assertEquals(this.refeicaoController.disponibilizarRefeicao("FUN3", "Jantar", "Sopa", lt, lt2, 20.0, true), "REFEIÇÃO DISPONIBILIZADA COM SUCESSO");
	}
	
//	@Test
//	public void disponibilizaRefeicaoUsuarioIndevido() {
//		assertEquals("APENAS GERENTES E FUNCIONÁRIOS PODEM DISPONIBILIZAR REFEIÇÕES", refeicaoController.disponibilizarRefeicao("ADM1", "Jantar", "sopa", lt, lt2, 20.0, true));
//		assertEquals("APENAS GERENTES E FUNCIONÁRIOS PODEM DISPONIBILIZAR REFEIÇÕES", refeicaoController.disponibilizarRefeicao("CLI4", "Jantar", "sopa", lt, lt2, 20.0, true));
//	}

//	@Test
//	public void adicionaTipoRefeicaoInexistente() {
//		assertEquals("TIPO DE REFEIÇÃO INVÁLIDO", this.refeicaoController.disponibilizarRefeicao("GER2", "Lanhce", "Sorvete", lt, lt2, 5.00, true));
//	}
//
//	@Test
//	public void testeRepresentacaoTextual() {
//		refeicaoController.disponibilizarRefeicao("GER2", "Almoço", "Arroz", lt, lt2, 20.0, true); // idRefeicao = 1
//		assertEquals("[1] Almoço: Arroz (12:30 as 13:30). Valor por pessoa: R$20.0. VIGENTE.", refeicaoController.exibirRefeicaoPorId(1));
//	}
//	
//	@Test
//	public void exibirRefeicaoInexistente() {
//		assertEquals("REFEIÇÃO NÃO DISPONÍVEL", refeicaoController.exibirRefeicaoPorId(1));
//	}
//
//	@Test
//	public void testeRepresentacaoTextualRefeicaoIndisponivel() {
//		refeicaoController.disponibilizarRefeicao("GER2", "Almoço", "Arroz", lt, lt2, 20.0, false);
//		assertEquals("[1] Almoço: Arroz (12:30 as 13:30). Valor por pessoa: R$20.0. INDISPONIVEL.", refeicaoController.exibirRefeicaoPorId(1));
//	}
//
//@Test
//	public void testeAlterarRefeicoes() {
//		refeicaoController.disponibilizarRefeicao("GER2", "Almoço", "Lasanha", lt, lt2, 45.50, true); //idRefeicao = 1
//		LocalTime horaInicio = LocalTime.parse("13:50");
//		LocalTime horaFinal = LocalTime.parse("14:20");
//		assertEquals("REFEIÇÃO ALTERADA!", refeicaoController.alterarRefeicao(1, horaInicio, horaFinal, 50.0, true));
//		assertEquals("[1] Almoço: Lasanha (13:50 as 14:20). Valor por pessoa: R$50.0. VIGENTE.", refeicaoController.exibirRefeicaoPorId(1));
//
//	}
//
//  @Test 
//   public void testeAlterarRefeicaoInexistente() {
//	  
//    	assertEquals("REFEIÇÃO NÃO ENCONTRADA", refeicaoController.alterarRefeicao(2, lt, lt2, 30.0, true));
//    }
//  
//  @Test
//  public void testeHorarioErrado() {
//	  assertEquals("O HORÁRIO DE FIM DEVE SER POSTERIOR AO HORÁRIO DE INÍCIO", refeicaoController.disponibilizarRefeicao("FUN3", "Almoço", "Carne", lt2, lt, 80.0, true));
//  }

	@Test
	public void listarRefeicoes() {
		LocalTime horaCafe = LocalTime.parse("08:00");
		LocalTime horaFinalCafe = LocalTime.parse("09:20");
		LocalTime horaJanta = LocalTime.parse("18:00");
		LocalTime horaFinalJanta = LocalTime.parse("20:00");
		assertEquals(this.refeicaoController.disponibilizarRefeicao("GER2", "Café-da-manhã", "Café completo reforcado", horaCafe, horaFinalCafe, 30.0, true), "REFEIÇÃO DISPONIBILIZADA COM SUCESSO"); //idRefeicao =1
		assertEquals(this.refeicaoController.disponibilizarRefeicao("GER2", "Almoço", "Lasanha", lt, lt2, 45.50, true), "REFEIÇÃO DISPONIBILIZADA COM SUCESSO"); // idRefeicao = 2
		assertEquals(this.refeicaoController.disponibilizarRefeicao("FUN3", "Jantar", "Sopa", horaJanta, horaFinalJanta, 20.0, false), "REFEIÇÃO DISPONIBILIZADA COM SUCESSO"); // idRefeicao = 3
		//formato da hora 06h00, formado do dinheerio 33,xx;
		String[] refeicoes = {"[1] Café-da-manhã: Café completo reforcado (08:00 as 09:20). Valor por pessoa: R$30.0. VIGENTE.", "[2] Almoço: Lasanha (12:30 as 13:30). Valor por pessoa: R$45.5. VIGENTE.", "[3] Jantar: Sopa (18:00 as 20:00). Valor por pessoa: R$20.0. INDISPONIVEL."};
		assertEquals(Arrays.toString(refeicoes), Arrays.toString(refeicaoController.listarRefeicoes()));
	}

	
}
