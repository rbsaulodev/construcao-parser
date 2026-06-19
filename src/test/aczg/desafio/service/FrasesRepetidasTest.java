package test.aczg.desafio.service;

import aczg.desafio.service.TextoParserService;
import aczg.desafio.service.TextoParserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

@DisplayName("Requisito 2: Frases que se repetem")
class FrasesRepetidasTest {

    private TextoParserService parser;

    @BeforeEach
    void setUp() {
        parser = new TextoParserServiceImpl();
    }

    @Test
    @DisplayName("Dado texto vazio, retorna mapa vazio")
    void deveRetornarMapaVazioParaTextoVazio() {
        Map<String, Long> resultado = parser.getFrasesRepetidas("");
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Dado texto sem repetições, retorna mapa vazio")
    void deveRetornarMapaVazioSemRepeticoes() {
        String texto = "Primeira linha\nSegunda linha\nTerceira linha";
        Map<String, Long> resultado = parser.getFrasesRepetidas(texto);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Dado frase repetida 2x, retorna count 2")
    void deveContarFraseRepetidaDuasVezes() {
        String texto = "Amou daquela vez\nAmou daquela vez";
        Map<String, Long> resultado = parser.getFrasesRepetidas(texto);
        assertEquals(1, resultado.size());
        assertTrue(resultado.containsValue(2L));
    }


    @Test
    @DisplayName("Frase única não deve aparecer no resultado")
    void fraseUnicaNaoDeveAparecerNoResultado() {
        String texto = "Linha única aqui\nOutra linha";
        Map<String, Long> resultado = parser.getFrasesRepetidas(texto);
        assertFalse(resultado.containsKey("linha única aqui"));
    }
}
