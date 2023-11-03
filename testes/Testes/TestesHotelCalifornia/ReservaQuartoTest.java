package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservaQuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;

class ReservaQuartoTest {

	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservaQuartoController reservaController;
	private String[] pedidos = { " 01 (uma) cama infantil", "01 (uma) roupa de cama adicional" };
	private String[] refeicoes = {
			"[12] Cafe-da-manha: Cafe completo reforcado (06h00 as 10h00). Valor por pessoa: R$30,00. VIGENTE." };

	@BeforeEach
	void setUp() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController(usuarioController);
		this.reservaController = new ReservaQuartoController(usuarioController, quartoController);

		// cadastrando Usuarios
		this.usuarioController.cadastrarUsuario("ADM1", "Lucas", "ADM", 00111); // [ADM2] Lucas
		this.usuarioController.cadastrarUsuario("ADM1", "Helena", "GER", 4566); // [GER3] Helena
		this.usuarioController.cadastrarUsuario("ADM2", "Ana Laura", "CLI", 88888); // [CLI4] Ana Laura
		this.usuarioController.cadastrarUsuario("ADM2", "Maria", "FUN", 66333); // [FUN5] Maria
		this.usuarioController.cadastrarUsuario("AM1", "José", "CLI", 3333); // [CLI6] José

		// disponibilizando Quartos
		this.quartoController.disponibilizarQuartoSingle("ADM1", 601, 50.0, 100.0);
		this.quartoController.disponibilizarQuartoSingle("ADM1", 555, 50.0, 100.0);
		this.quartoController.disponibilizarQuartoSingle("ADM2", 234, 50.0, 100.0);
		this.quartoController.disponibilizarQuartoDouble("ADM2", 155, 50.0, 100.0, pedidos);
		this.quartoController.disponibilizarQuartoFamily("ADM2", 123, 50.0, 100, pedidos, 10);

	}

	@Test
	void verificandoQuartosCadastrados() {
		assertEquals(this.reservaController.quartosCadastrados(), 5);

	}
//	
//	@Test
//	void verficaSaidaQuartoCadastrado() {
//		assertEquals(this.reservaController.exibirQuartosCadastrados(), "N");
//	}

	@Test
	void testReservarQuarto() {
		assertEquals(this.reservaController.reservarQuartoSingle("GER3","CLI4" , 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "RESERVA QUARTO SINGLE REALIZADA");
		assertEquals(this.reservaController.reservarQuartoDouble("FUN5", "CLI4", 155, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos), "RESERVA QUARTO DOUBLE REALIZADA");
		assertEquals(this.reservaController.reservarQuartoFamily("FUN5", "CLI6", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos, 5), "RESERVA QUARTO FAMILY REALIZADA");
}
	@Test 
	void testResercarQuartoUsuarioEClienteNaoCadastrados() {
		assertEquals(this.reservaController.reservarQuartoSingle("FUN6", "CLI4",601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "USUÁRIO NÃO CADASTRADO NO SISTEMA");
		assertEquals(this.reservaController.reservarQuartoSingle("FUN5", "CLI5",601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "USUÁRIO NÃO CADASTRADO NO SISTEMA");
		
	}
	@Test
	void testReservarAcimaLimitePessoas() {
		assertEquals(this.reservaController.reservarQuartoFamily("FUN5", "CLI6", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos, 12), "O NUMERO DE PESSOAS SUPERA A QUANTIDADE MÁXIMA DE PESSOAS PERMITIDA");
		
	}
	
	@Test 
	void testReservaIndevida() {
	assertEquals(this.reservaController.reservarQuartoSingle("ADM1","CLI4", 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR RESERVAS DE QUARTO");
	assertEquals(this.reservaController.reservarQuartoDouble("CLI6", "CLI4", 155, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes, pedidos), "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR RESERVAS DE QUARTO");
	assertEquals(this.reservaController.reservarQuartoFamily("CLI6", "CLI4", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes, pedidos, 2), "APENAS GERENTES E FUNCIONÁRIOS PODEM EFETUAR RESERVAS DE QUARTO");
	}
	 
	@Test
	void testReservaMenos24horas() {
		assertEquals(this.reservaController.reservarQuartoSingle("FUN5", "CLI4", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 12, 17, 0, 0), refeicoes), "O PERÍODO MÍNIMO DA RESERVA É DE 01 DIÁRIA (24 HORAS)");
	}
	
	@Test  
	void testReservaIndisponivel() {
		this.reservaController.reservarQuartoSingle("GER3", "CLI4", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 13, 14, 0, 0), refeicoes);
	assertEquals(this.reservaController.reservarQuartoSingle("GER3", "CLI4", 601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 13, 14, 0, 0), refeicoes), "O QUARTO JÁ ESTÁ RESERVADO NO PERÍODO DESEJADO!");
	}

//	@Test
//	void testExibirReserva() {
//		this.reservaController.reservarQuartoSingle("GER3", "CLI4", 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes); //idReserva = 1
//		assertEquals(this.reservaController.exibirReserva("CLI4",  1), "NAO SEI");  
//}

	@Test
	void qtdreservada() {
		this.reservaController.reservarQuartoSingle("GER3", "CLI4", 601, LocalDateTime.of(2023, 10, 12, 14, 0, 0),
				LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes); // idReserva = 1
		this.reservaController.reservarQuartoSingle("GER3", "CLI6", 555, LocalDateTime.of(2023, 10, 14, 15, 0, 0),
				LocalDateTime.of(2023, 10, 16, 14, 0, 0), refeicoes);
		this.reservaController.reservarQuartoDouble("GER3", "CLI4", 155, LocalDateTime.of(2023, 10, 25, 14, 0, 0),
				LocalDateTime.of(2023, 10, 30, 12, 0, 0), refeicoes, pedidos);
		
		assertEquals(this.reservaController.qtdReservada(), 3);
	}

	@Test
	void exibirTodasAsReservas() {
		this.reservaController.reservarQuartoSingle("GER3", "CLI6", 555, LocalDateTime.of(2023, 10, 14, 15, 0, 0),
				LocalDateTime.of(2023, 10, 16, 14, 0, 0), refeicoes);
		this.reservaController.reservarQuartoSingle("GER3", "CLI6", 601, LocalDateTime.of(2023, 10, 20, 15, 0, 0),
				LocalDateTime.of(2023, 10, 23, 14, 0, 0), refeicoes);
		this.reservaController.reservarQuartoDouble("GER3", "CLI4", 155, LocalDateTime.of(2023, 10, 25, 14, 0, 0),
				LocalDateTime.of(2023, 10, 30, 12, 0, 0), refeicoes, pedidos);
		assertEquals(this.reservaController.exibeReservas(), "nao sei");
	}
}
