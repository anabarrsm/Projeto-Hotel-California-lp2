package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.RefeicaoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author maria helena
 * testes unitários da classe RefeicaoController
 */

public class RefeicaoControllerTest {
	private RefeicaoController refeicaoController;
	private UsuarioController usuarioController;
	private LocalTime lt;
	private LocalTime lt2;

	@BeforeEach
	void setUp() {
		this.usuarioController = new UsuarioController(); 
		this.refeicaoController = new RefeicaoController(usuarioController);
	
}
	
	@Test
	void testCadastrarRefeicao() {
		usuarioController.cadastrarUsuario("ADM1", "alala", "GER" , 7856);
		String resultado = refeicaoController.disponibilizarRefeicao("GER2", "CAFE_DA_MANHA",
				"Cafe Matinal Completo", LocalTime.of(6, 0), LocalTime.of(10, 0),
				20.0, true);
		assertAll(
				()-> assertTrue(resultado.contains("Cafe-da-manha: Cafe Matinal Completo (06h00 as 10h00)")),
				()-> assertTrue(resultado.contains("Valor por pessoa: R$20,00")),
				()-> assertTrue(resultado.contains("VIGENTE"))
		);
		
		String cadastroAlmoco = refeicaoController.disponibilizarRefeicao("GER2", "ALMOCO",
				"Almoco de Comida Regional", LocalTime.of(11, 0), LocalTime.of(14, 0),
				80.0, false);
	
		assertAll(
				()-> assertTrue(cadastroAlmoco.contains("Almoco: Almoco de Comida Regional (11h00 as 14h00)")),
				()-> assertTrue(cadastroAlmoco.contains("Valor por pessoa: R$80,00")),
				()-> assertTrue(cadastroAlmoco.contains("INDISPONIVEL"))
		);
		
		
		String cadastroJanta = refeicaoController.disponibilizarRefeicao("GER2", "JANTAR",
				"Comida Italiana", LocalTime.of(18, 0), LocalTime.of(22, 30),
				60.0, true);
		assertAll(
				()-> assertTrue(cadastroJanta.contains("Jantar: Comida Italiana (18h00 as 22h30)")),
				()-> assertTrue(cadastroJanta.contains("Valor por pessoa: R$60,00")),
				()-> assertTrue(cadastroJanta.contains("VIGENTE"))
		);
		
	}


	@Test
	void cadatrarRefeicaoComUsuarioInexistente() {
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			refeicaoController.disponibilizarRefeicao("ADM2", "ALMOCO",
					"Almoco de Comida Regional", LocalTime.of(11, 0), LocalTime.of(14, 0),
					80.0, false);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("USUARIO NAO EXISTE"));
	}
	
	
	@Test
	void refeicaoJahCadastrada() {
		usuarioController.cadastrarUsuario("ADM1", "alala", "GER" , 7856);
		usuarioController.cadastrarUsuario("ADM1", "ALSDSLA", "FUN", 6546);
		refeicaoController.disponibilizarRefeicao("GER2", "JANTAR",
				"Comida Italiana", LocalTime.of(18, 0), LocalTime.of(22, 30),
				60.0, true);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			refeicaoController.disponibilizarRefeicao("FUN3", "JANTAR",
					"Comida Italiana", LocalTime.of(18, 0), LocalTime.of(22, 30),
					60.0, true);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("REFEICAO JA EXISTE"));
	}

	@Test
	void alterarRefeicaoNaoExiste() {
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			refeicaoController.alterarRefeicao(123456789L, LocalTime.of(10,0),
					LocalTime.of(12, 0), 0.0, false);
		});
		assertTrue(hce.getMessage().toUpperCase().contains("REFEICAO NAO EXISTE"));
	}
	
		@Test
		void clienteCadastraRefeicao() {
			usuarioController.cadastrarUsuario("ADM1", "alala", "CLI" , 7856);
			HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
				refeicaoController.disponibilizarRefeicao("CLI2", "JANTAR",
						"Comida Italiana", LocalTime.of(18, 0), LocalTime.of(22, 30),
						60.0, true);
			});
			assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
			assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UMA REFEICAO"));
		}
		
		@Test
		void admCadastraRefeicao() {
			HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
				refeicaoController.disponibilizarRefeicao("ADM1", "JANTAR",
						"Comida Italiana", LocalTime.of(18, 0), LocalTime.of(22, 30),
						60.0, true);
			});
			assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
			assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UMA REFEICAO"));
		}
		
		
		@Test
		void horaFimDepoisHoraInicio() {
			usuarioController.cadastrarUsuario("ADM1", "alala", "FUN" , 7856);
			HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
				refeicaoController.disponibilizarRefeicao("FUN2", "JANTAR",
						"Comida Italiana", LocalTime.of(22, 30), LocalTime.of(18, 0),
						60.0, true);
			});
			assertTrue(hce.getMessage().toUpperCase().contains("HORARIO DE FIM DEVE SER POSTERIOR AO HORARIO DE INICIO"));
		}
		
		
		@Test
		void listarRefeicoes() {
			usuarioController.cadastrarUsuario("ADM1", "alala", "GER" , 7856);
			usuarioController.cadastrarUsuario("ADM1", "alala", "FUN" , 7856);
			refeicaoController.disponibilizarRefeicao("GER2", "JANTAR",
					"Comida Italiana", LocalTime.of(18, 0), LocalTime.of(22, 30),
					60.0, true);
			refeicaoController.disponibilizarRefeicao("FUN3", "ALMOCO",
					"Comida Brasileira", LocalTime.of(11, 0), LocalTime.of(15, 0),
					100.0, true);
			String[] resultado = refeicaoController.listarRefeicoes();
			assertAll(
					() -> assertEquals(2, resultado.length)
			);
		}
		
		
		}
		


