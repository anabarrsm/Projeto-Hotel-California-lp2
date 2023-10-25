package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;

class ReservaQuartoTest {
	
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservaController reservaController;
	private String[] pedidos = {" 01 (uma) cama infantil", "01 (uma) roupa de cama adicional"};
	private String[] refeicoes = {"[12] Cafe-da-manha: Cafe completo reforcado (06h00 as 10h00). Valor por pessoa: R$30,00. VIGENTE."};
	
	@BeforeEach
	void setUp() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController(usuarioController);
		this.reservaController = new ReservaController(usuarioController, quartoController);
		
		//cadastrando Usuarios
		this.usuarioController.cadastrarUsuario("ADM1", "Lucas", "ADM", 00111); // [ADM2] Lucas
		this.usuarioController.cadastrarUsuario("ADM1", "Helena", "GER", 4566); // [GER3] Helena
		this.usuarioController.cadastrarUsuario("ADM2", "Ana Laura", "CLI", 88888); // [CLI4] Ana Laura
		this.usuarioController.cadastrarUsuario("ADM2", "Maria", "FUN", 66333); // [FUN5] Maria
		this.usuarioController.cadastrarUsuario("AM1", "José", "CLI", 3333); //[CLI6] José
		
		
		//disponibilizando Quartos
		this.quartoController.disponibilizarQuartoSingle("ADM1", 601, 50.0, 100.0);
		this.quartoController.disponibilizarQuartoDouble("ADM2", 155, 50.0, 100.0, pedidos);
		this.quartoController.disponibilizarQuartoFamily("ADM2", 123, 50.0, 100, pedidos, 10);
		
		
	}

	
	@Test
	void testReservarQuarto() {
		assertEquals(this.reservaController.reservarQuartoSingle("GER3","CLI4" , 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "RESERVA QUARTO SINGLE REALIZADA");
		assertEquals(this.reservaController.reservarQuartoDouble("FUN5", "CLI4", 155, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos), "RESERVA QUARTO DOUBLE REALIZADA");
		assertEquals(this.reservaController.reservarQuartoFamily("FUN5", "CLI6", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos, 5), "RESERVA QUARTO FAMILY REALIZADA");
}
	@Test 
	void testResercarQuartoUsuarioEClienteNaoCadastrados() {
		assertEquals(this.reservaController.reservarQuartoSingle("FUN6", "CLI4",601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "USUÁRIO NÃO CADASTRADO NO SISTEMA!");
		assertEquals(this.reservaController.reservarQuartoSingle("FUN5", "CLI5",601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "USUÁRIO NÃO CADASTRADO NO SISTEMA!");
		
	}
	@Test
	void testReservarAcimaLimitePessoas() {
		assertEquals(this.reservaController.reservarQuartoFamily("FUN5", "CLI6", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos, 12), "O NUMERO DE PESSOAS SUPERA A QUANTIDADE MÁXIMA DE PESSOAS DESSE QUARTO");
		
	}
//	
	@Test 
	void testReservaIndevida() {
	assertEquals(this.reservaController.reservarQuartoSingle("ADM1","CLI4", 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO");
	assertEquals(this.reservaController.reservarQuartoDouble("CLI6", "CLI4", 155, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes, pedidos), "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO");
	assertEquals(this.reservaController.reservarQuartoFamily("CLI6", "CLI4", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes, pedidos, 2), "APENAS GERENTES E FUNCIONÁRIOS PODEM RESERVAR UM QUARTO");
	}
	 
	@Test
	void testReservaMenos24horas() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> this.reservaController.reservarQuartoSingle("FUN1", "CLI19", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 12, 17, 0, 0), refeicoes));
		assertTrue(thrown.getMessage().contains("O período mínimo da reserva é de uma diária (24 horas)."));
	}
	
	@Test  
	void testReservaIndisponivel() {
		this.reservaController.reservarQuartoSingle("GER3", "CLI4", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 13, 14, 0, 0), refeicoes);
		assertEquals(this.reservaController.reservarQuartoSingle("GER3", "CLI4", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 13, 14, 0, 0), refeicoes), "O QUARTO JÁ ESTÁ RESERVADO NO PERÍODO DESEJADO!");
	}

	@Test
	void testExibirReserva() {
		this.reservaController.reservarQuartoSingle("GER3", "CLI4", 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes); //idReserva = 1
		assertEquals(this.reservaController.exibirReserva("GER3",  0), "NAO SEI"); 
}

	}

