package Testes.TestesQuarto;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoSingle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuartoDoubleTest {
	private QuartoDouble quartoDouble;
	@BeforeEach
	void setUp() {
		String pedidos [] = new String[0];
		quartoDouble = new QuartoDouble("ADM1", 507, 50.0, 200.0, pedidos);
	}

	@Test
	void testExibirQuarto() {
		assertEquals(quartoDouble.exibirQuarto(),
				"[507]Quarto Double (custo basico: R$200.0; adicional por pessoa: R$50.0 >>> R$300.0 diária). Pedidos: (nenhum)");
	}

	@Test
	void testCalcularDiaria() {
		assertEquals(quartoDouble.calcularDiaria(), 300.0);
	}

}

