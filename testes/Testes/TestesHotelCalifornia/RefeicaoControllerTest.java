package Testes.TestesHotelCalifornia;
import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.RefeicaoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.QuartoController;

import java.time.LocalTime;

public class RefeicaoControllerTest {
    RefeicaoController controller;

    @BeforeEach
    void setUp(){
        this.controller = new RefeicaoController();
    }

    @Test
    public void adicionaRefeicao(){
        LocalTime lt = LocalTime.parse("12:30");
        LocalTime lt2 = LocalTime.parse("13:30");
        String retorno = controller.disponibilizarRefeicao("02", "Almoço", "Arroz", lt, lt2, 20.0, true);
        assertEquals("Refeição adicionada!",retorno );
    }

    @Test
    public void testeRepresentacaoTextual(){
        LocalTime lt = LocalTime.parse("12:30");
        LocalTime lt2 = LocalTime.parse("13:30");
        controller.disponibilizarRefeicao("02", "Almoço", "Arroz", lt, lt2, 20.0, true);
        String  retorno = controller.exibirRefeicao(02);
        //System.out.println(retorno);
        assertEquals("[2]Almoço: Arroz (12:30 as 13:30). Valor por pessoa: R$ 20.0. VIGENTE.", retorno);
    }

    @Test
    public void testeRepresentacaoTextualRefeicaoIndisponivel(){
        LocalTime lt = LocalTime.parse("12:30");
        LocalTime lt2 = LocalTime.parse("13:30");
        controller.disponibilizarRefeicao("02", "Almoço", "Arroz", lt, lt2, 20.0, false);
        String  retorno = controller.exibirRefeicao(02);
        //System.out.println(retorno);
        assertEquals("[2]Almoço: Arroz (12:30 as 13:30). Valor por pessoa: R$ 20.0. INDISPONIVEL.", retorno);
    }

}
