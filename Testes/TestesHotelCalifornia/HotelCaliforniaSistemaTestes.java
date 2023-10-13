package TestesHotelCalifornia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.HotelCaliforniaSistema;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.UsuarioController;

class HotelCaliforniaSistemaTestes {

	UsuarioController usuario = new UsuarioController();
	
	
	@BeforeEach
	void setUp() {
		//this.usuario.cadastrarUsuario("ADM1", "Joao Costa", "ADM", 123456);
		//this.usuario.cadastrarUsuario("ADM2", "Helena Administradora", "ADM", 456789);
		//this.sistema.cadastrarUsuario("GER3", "Clara Gerente", "GER", 0123450);
	}
	
	@Test
	void testCadastrarUsuarioJáExistente() {
		assertEquals("JÁ EXISTE UM USUÁRIO COM O ID ESPECIFICADO", usuario.cadastrarUsuario("ADM1", "Joao Costa", "ADM", 123456));
	}
//	@Test
//	void testCadastrarMaisGerente() {
//		assertEquals("JÁ ESXISTE UM GERENTE CADASTRADO", sistema.cadastrarUsuario("ADM2", "Ana Gerente", "GER", 555));
//	}

	
	@
}


