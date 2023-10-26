package Testes.TestesHotelCalifornia;
import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.Refeicao;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestaurante;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.ReservaRestauranteController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRestauranteTest {
    ReservaRestaurante restaurante1;
    private LocalTime horaInicio = LocalTime.of(12, 30);
    private LocalTime horaFinal = LocalTime.of(12, 30);
    private Refeicao refeicao1 = new Refeicao("Almoço", "Arroz", horaInicio, horaFinal,30, true );
    private LocalDate datainicial = LocalDate.of(2023, 5, 13);
    private LocalDate dataFinal = LocalDate.of(2023, 5, 14);

    @BeforeEach
//    void setUp() {
//        restaurante1 = new ReservaRestaurante("Lucas", datainicial, dataFinal, 50, refeicao1);
//    }

    @Test
    public void testaToString(){
        String retorno = restaurante1.toString();
        assertEquals(retorno, "[<id>]  Reserva de RESTAURANTE em favor de: Lucas \n Detalhes da reserva: \n - Periodo: "  + datainicial + " ate " + dataFinal + "\n - Qtde. de Convidados: 50 pessoa(s) \n Refeicao incluida: " + refeicao1 + "\nVALOR TOTAL DA RESERVA: R$ " + restaurante1.getValorReservaRestaurante() + "x" + datainicial.until(dataFinal).getDays() + " (diarias)  => R$ " + restaurante1.calculaPreco() + "\n +  SITUACAO DO PAGAMENTO: " + restaurante1.situacaoPagamento());
    }
}
 