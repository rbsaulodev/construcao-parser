package test.aczg.desafio.service;

import aczg.desafio.service.TextoParserService;
import aczg.desafio.service.TextoParserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Requisito 4: Frases de exatamente 4 palavras")
class FrasesQuatroPalavrasTest {

    private TextoParserService parser;

    @BeforeEach
    void setUp() {
        parser = new TextoParserServiceImpl();
    }

    @Test
    @DisplayName("Dado texto vazio, retorna lista vazia")
    void deveRetornarListaVaziaParaTextoVazio() {
        assertTrue(parser.getFrasesComQuatroPalavras("").isEmpty());
    }

    @Test
    @DisplayName("'E a Joana que gritou' tem 5 palavras — não deve aparecer")
    void naoDeveRetornarFraseDe5Palavras() {
        List<String> resultado = parser.getFrasesComQuatroPalavras("E a Joana que gritou");
        assertFalse(resultado.contains("E a Joana que gritou"));
    }

    @Test
    @DisplayName("'E a Joana gritou' tem 4 palavras — deve aparecer")
    void deveRetornarFraseDe4Palavras() {
        List<String> resultado = parser.getFrasesComQuatroPalavras("E a Joana gritou");
        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("'amor' tem 1 palavra — não deve aparecer")
    void naoDeveRetornarPalavraUnica() {
        assertTrue(parser.getFrasesComQuatroPalavras("amor").isEmpty());
    }

    @Test
    @DisplayName("Dado trecho da música, identifica frases de exatamente 4 palavras")
    void deveIdentificarFrasesExatasNaMusicaConstrucao() {
        String trecho =
            "E o João que passou um susto\n" +  // 7 palavras
            "E a Joana que gritou\n" +           // 5 palavras
            "E o Paulo que havia uns cinco\n" +  // 7 palavras
            "E a Maria que sorriu\n" +            // 5 palavras
            "Tijolo com tijolo num desenho lógico\n" + // 5 palavras
            "Caiu como se fosse\n" +              // 4 palavras ← deve aparecer
            "Pôs o pé firme";                    // 4 palavras ← deve aparecer

        List<String> resultado = parser.getFrasesComQuatroPalavras(trecho);

       assertTrue("Deve conter 'Caiu como se fosse'", resultado.stream().anyMatch(f -> f.equals("Caiu como se fosse")));
       assertTrue("Deve conter 'Pôs o pé firme'", resultado.stream().anyMatch(f -> f.equals("Pôs o pé firme")));
       assertTrue("Não deve conter frases com João (7 palavras)", resultado.stream().noneMatch(f -> f.contains("João")));
    }

    @Test
    @DisplayName("Resultado não deve ter duplicatas")
    void resultadoNaoDeveTerDuplicatas() {
        String texto = "Caiu como se fosse\nCaiu como se fosse\nCaiu como se fosse";
        List<String> resultado = parser.getFrasesComQuatroPalavras(texto);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("'Ergueu-se da cadeira' é considerado como palavra com hífen (3 tokens)")
    void deveConsiderarHifenComoUmToken() {
        // "Ergueu-se da cadeira última" = 4 tokens (Ergueu-se | da | cadeira | última)
        List<String> resultado = parser.getFrasesComQuatroPalavras("Ergueu-se da cadeira última");
        assertFalse(resultado.isEmpty(), "Palavra hifenizada deve ser 1 token");
    }
}
