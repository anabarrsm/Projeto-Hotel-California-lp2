package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.ReservasSessionController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;


/**
 * @author maria helena
 * testes unitários referentes as reservas de Quarto
 */

class ReservaQuartoTest {
	

	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private ReservasSessionController reservaController;
	private String[] pedidos = { " 01 (uma) cama infantil", "01 (uma) roupa de cama adicional" };
	private String[] refeicoes = {
			"[12] Cafe-da-manha: Cafe completo reforcado (06h00 as 10h00). Valor por pessoa: R$30,00. VIGENTE." };

	@BeforeEach
	void setUp() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController(usuarioController);
		this.reservaController = new ReservasSessionController(usuarioController, quartoController, null);

		// cadastrando Usuarios
		this.usuarioController.cadastrarUsuario("ADM1", "Lucas", "ADM", 00111); // [ADM2] Lucas
		this.usuarioController.cadastrarUsuario("ADM1", "Helena", "GER", 4566); // [GER3] Helena
		this.usuarioController.cadastrarUsuario("ADM2", "Ana Laura", "CLI", 88888); // [CLI4] Ana Laura
		this.usuarioController.cadastrarUsuario("ADM2", "Maria", "FUN", 66333); // [FUN5] Maria


		// disponibilizando Quartos
		this.quartoController.disponibilizarQuartoSingle("ADM1", 601, 50.0, 100.0);
		this.quartoController.disponibilizarQuartoSingle("ADM1", 555, 50.0, 100.0);
		this.quartoController.disponibilizarQuartoSingle("ADM2", 234, 50.0, 100.0);
		this.quartoController.disponibilizarQuartoDouble("ADM2", 155, 50.0, 100.0, pedidos);
		this.quartoController.disponibilizarQuartoFamily("ADM2", 123, 50.0, 100, pedidos, 10);

	}


	@Test
	void testReservarQuarto() {
		String reservaSingle = this.reservaController.reservarQuartoSingle("GER3","CLI4" , 601 ,LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes);
		String reservaDouble = this.reservaController.reservarQuartoDouble("FUN5", "CLI4", 155, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos);
		String reservaFamily = this.reservaController.reservarQuartoFamily("FUN5", "CLI6", 123, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 12, 16, 14, 0, 0), refeicoes, pedidos, 5);
	
		assertEquals(reservaSingle, reservaSingle.toString());
		assertEquals(reservaDouble, reservaDouble.toString());
		assertEquals(reservaFamily, reservaFamily.toString());
	}
	@Test 
	void testResercarQuartoUsuarioEClienteNaoCadastrados() {
		assertEquals(this.reservaController.reservarQuartoSingle("FUN6", "CLI4",601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "USUÁRIO NÃO CADASTRADO NO SISTEMA");
		assertEquals(this.reservaController.reservarQuartoSingle("FUN5", "CLI5",601, LocalDateTime.of(2023, 10, 12, 14 ,0, 0), LocalDateTime.of(2023, 10, 14, 12, 0, 0), refeicoes), "USUÁRIO NÃO CADASTRADO NO SISTEMA");
		
	}
	@Test
	void testReservarAcimaLimitePessoas() {
		LocalDateTime dataInicio = LocalDateTime.of(2024, Month.JANUARY, 6, 14, 0);
		LocalDateTime dataFim = LocalDateTime.of(2024, Month.JANUARY, 8, 12, 0);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			reservaController.reservarQuartoFamily("GER3", "CLI4", 123,
					dataInicio, dataFim, refeicoes, new String[]{"frigoba"}, 11);
		});
		assertTrue(hce.getMessage().contains("CAPACIDADE EXCEDIDA"));
	}
	
	@Test 
	void testReservaIndevida() {
		LocalDateTime dataInicio = LocalDateTime.of(2024, Month.JANUARY, 6, 14, 0);
		LocalDateTime dataFim = LocalDateTime.of(2024, Month.JANUARY, 8, 12, 0);
		HotelCaliforniaException hce = assertThrows(HotelCaliforniaException.class, () -> {
			reservaController.reservarQuartoDouble("ADM1", "CLI4", 155,
					dataInicio, dataFim, refeicoes, new String[]{"frigoba"});
		});
		assertTrue(hce.getMessage().toUpperCase().contains("NAO E POSSIVEL PARA USUARIO"));
		assertTrue(hce.getMessage().toUpperCase().contains("CADASTRAR UMA RESERVA"));
	
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


}