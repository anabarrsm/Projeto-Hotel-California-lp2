package Testes.TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.p2lp2.hotelcalifornia.controller.AreaComumController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.QuartoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.RefeicaoController;
import br.edu.ufcg.p2lp2.hotelcalifornia.controller.UsuarioController;

class AreaComumControllerTest {
	private UsuarioController usuarioController;
	private AreaComumController areaComumController;

	@BeforeEach
	public void setUp() {
		this.usuarioController = new UsuarioController();
		this.areaComumController = new AreaComumController(usuarioController);

	}

	@Test
		public void disponibilizarAreaComum() {
			assertEquals(this.areaComumController.disponibilizarAreaComum("ADM1", "AUDITORIO", "Auditorio Nilo Pecanha", LocalTime.of(8,0), LocalTime.of(18,0), 0.0, true, 100), "[1] AUDITORIO: Auditorio Nilo Pecanha (08h00 as 18h00). Valor por pessoa: Grátis. Capacidade: 100 pessoa(s). VIGENTE.");
		}
} 