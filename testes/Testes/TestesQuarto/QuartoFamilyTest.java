package Testes.TestesQuarto;

import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoFamily;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuartoFamilyTest {
	private QuartoFamily quartoFamily;
	@BeforeEach
	void setUp() {
		String pedidos [] = new String[0];
		quartoFamily = new QuartoFamily("ADM1", 405, 50.0, 50.0, pedidos, 8);
	}

	@Test
	void testExibirQuarto() {
		assertEquals(quartoFamily.exibirQuarto(),
				"[405]Quarto Family (custo basico: R$50.0; adicional por pessoa: R$50.0 >>> R$450.0 diária). Capacidade: 8 pessoa(s). Pedidos: (nenhum)");
	}

	@Test
	void testCalcularDiaria() {
		assertEquals(quartoFamily.calcularDiaria(), 450.0);
	}

}
