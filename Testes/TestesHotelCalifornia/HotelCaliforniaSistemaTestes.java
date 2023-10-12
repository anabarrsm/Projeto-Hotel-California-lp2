package TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.HotelCaliforniaSistema;

class HotelCaliforniaSistemaTestes {

	HotelCaliforniaSistema sistema = new HotelCaliforniaSistema();
	
	
	@BeforeEach
	void setUp() {
		this.sistema.cadastrarUsuario("ADM1", "Joao Costa", "ADM", 123456);
		this.sistema.cadastrarUsuario("ADM2", "Helena Administradora", "ADM", 456789);
		//this.sistema.cadastrarUsuario("GER3", "Clara Gerente", "GER", 0123450);
	}
	
	@Test
	void testCadastrarUsuarioPadrao() {
		assertEquals("USUÁRIO CADASTRADO COM SUCESSO!", sistema.cadastrarUsuario("ADM3", "Helena", "ADM", 012));
	}
//	@Test
//	void testCadastrarMaisGerente() {
//		assertEquals("JÁ ESXISTE UM GERENTE CADASTRADO", sistema.cadastrarUsuario("ADM2", "Ana Gerente", "GER", 555));
//	}

}
