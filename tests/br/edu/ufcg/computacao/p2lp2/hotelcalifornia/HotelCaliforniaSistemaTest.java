package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelCaliforniaSistemaTest {
	HotelCaliforniaSistema sistema = new HotelCaliforniaSistema();
	
}

	@BeforeEach
	void setUp() {
		this.sistema.cadastrarUsuario("ADM2", "Helena Administradora", "ADM", "456789");
		
	}

//
		
//		@BeforeAll
//		static void setUpBeforeClass() throws Exception {
//		}
//
//		@AfterAll
//		static void tearDownAfterClass() throws Exception {
//		}
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//
//}
