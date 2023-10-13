package TestesQuarto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoSingle;

class QuartoSingleTest {

	private QuartoSingle quartoSingle;

	@BeforeEach
	void setUp() {
		quartoSingle = new QuartoSingle("ADM1", 301, 50.0, 100.0);
	}
	
	// ver questão da formatação das casas decimais

	@Test
	void testExibirQuarto() {
		assertEquals(quartoSingle.exibirQuarto(),
				"[301] Quarto Single (custo basico: R$100.0; adicional por pessoa: R$50.0 >>> R$150.0 diária)");
	}
	
	@Test
	void testCalcularDiaria() {
		assertEquals(quartoSingle.calcularDiaria(), 150.0);
	}

}
