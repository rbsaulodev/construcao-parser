package test.aczg.desafio.service;

import aczg.desafio.service.TextoParserService;
import aczg.desafio.service.TextoParserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Extras: Proparoxítonas e Remoção de Plural")
class ExtrasTest {

    private TextoParserService parser;

    @BeforeEach
    void setUp() {
        parser = new TextoParserServiceImpl();
    }

    @Nested
    @DisplayName("Extra 1: Proparoxítonas")
    class ProparoxitonaTest {

        @Test
        @DisplayName("Dado texto vazio, retorna lista vazia")
        void deveRetornarVazioParaTextoVazio() {
            assertTrue(parser.getPalavrasProparoxitonas("").isEmpty());
        }

        @Test
        @DisplayName("'lógico' é proparoxítona")
        void deveIdentificarLogico() {
            List<String> resultado = parser.getPalavrasProparoxitonas("lógico");
            assertTrue("'lógico' é proparoxítona", resultado.contains("lógico"));
        }

        @Test
        @DisplayName("'último' é proparoxítona")
        void deveIdentificarUltimo() {
            List<String> resultado = parser.getPalavrasProparoxitonas("último");
            assertTrue("'último' é proparoxítona", resultado.contains("último"));
        }

        @Test
        @DisplayName("'trágico' é proparoxítona")
        void deveIdentificarTragico() {
            List<String> resultado = parser.getPalavrasProparoxitonas("trágico");
            assertTrue("'trágico' é proparoxítona", resultado.contains("trágico"));
        }

        @Test
        @DisplayName("'máquina' é proparoxítona")
        void deveIdentificarMaquina() {
            List<String> resultado = parser.getPalavrasProparoxitonas("máquina");
            assertTrue("'máquina' é proparoxítona", resultado.contains("máquina"));
        }

        @Test
        @DisplayName("'pêndulo' é proparoxítona")
        void deveIdentificarPendulo() {
            List<String> resultado = parser.getPalavrasProparoxitonas("pêndulo");
            assertTrue("'pêndulo' é proparoxítona", resultado.contains("pêndulo"));
        }

        @Test
        @DisplayName("'sólido' é proparoxítona")
        void deveIdentificarSolido() {
            List<String> resultado = parser.getPalavrasProparoxitonas("sólido");
            assertTrue("'sólido' é proparoxítona", resultado.contains("sólido"));
        }

        @Test
        @DisplayName("'sólidas' é proparoxítona (feminino plural)")
        void deveIdentificarSolidas() {
            List<String> resultado = parser.getPalavrasProparoxitonas("sólidas");
            assertTrue("'sólidas' é proparoxítona", resultado.contains("sólidas"));
        }

        @Test
        @DisplayName("'amor' não é proparoxítona (oxítona)")
        void naoDeveIdentificarAmor() {
            List<String> resultado = parser.getPalavrasProparoxitonas("amor");
            assertFalse(resultado.contains("amor"), "'amor' não é proparoxítona");
        }

        @Test
        @DisplayName("'cadeira' não é proparoxítona (paroxítona)")
        void naoDeveIdentificarCadeira() {
            List<String> resultado = parser.getPalavrasProparoxitonas("cadeira");
            assertFalse(resultado.contains("cadeira"), "'cadeira' não é proparoxítona");
        }

        @Test
        @DisplayName("Dado trecho da música, identifica proparoxítonas corretamente")
        void deveIdentificarProparoxitonasNaMusicaConstrucao() {
            String trecho =
                "E guardou o seu sorriso com carinho trágico\n" +
                "Tijolo com tijolo num desenho lógico\n" +
                "Conduziu sua balança como se fosse um pêndulo\n" +
                "Recostou na ribanceira como se fosse máquina\n" +
                "E ergueu no patamar quatro paredes sólidas\n" +
                "Dançou e gargalhou como se fosse o último\n" +
                "Caiu como se fosse um sólido";

            List<String> resultado = parser.getPalavrasProparoxitonas(trecho);

            assertAll(
                () -> assertTrue("trágico", resultado.contains("trágico")),
                () -> assertTrue("lógico", resultado.contains("lógico")),
                () -> assertTrue("pêndulo", resultado.contains("pêndulo")),
                () -> assertTrue("máquina", resultado.contains("máquina")),
                () -> assertTrue("sólidas", resultado.contains("sólidas")),
                () -> assertTrue("último", resultado.contains("último")),
                () -> assertTrue("sólido", resultado.contains("sólido"))
            );
        }
    }

    @Nested
    @DisplayName("Extra 2: Texto sem palavras no plural")
    class RemocaoPluralTest {

        @Test
        @DisplayName("Dado texto vazio, retorna lista vazia")
        void deveRetornarListaVaziaParaTextoVazio() {
            assertTrue(parser.getTextoSemPlural("").isEmpty());
        }


        @Test
        @DisplayName("'pés' deve ser tratado (plural de pé)")
        void deveRemoverPluralEmPes() {
            List<String> resultado = parser.getTextoSemPlural("Trouxe os pés à calçada");
            assertTrue("pés deve ser tratado",
                    resultado.contains("pé") || resultado.contains("pés"));
        }

        @Test
        @DisplayName("Palavras no singular devem ser mantidas")
        void deveManterPalavrasNoSingular() {
            List<String> resultado = parser.getTextoSemPlural("amor vida paz");
            assertTrue(resultado.contains("amor"));
            assertTrue(resultado.contains("vida"));
            assertTrue(resultado.contains("paz"));
        }

        @Test
        @DisplayName("'olhos' (plural de olho) deve ser tratado")
        void deveRemoverPluralDeOlhos() {
            List<String> resultado = parser.getTextoSemPlural("seus olhos embotados");
            assertFalse(resultado.contains("olhos"), "'olhos' deve ser removido/substituído");
        }

        @Test
        @DisplayName("Dado trecho da música, retorna lista sem plurais em s/es")
        void deveRemoverPluraisNaMusicaConstrucao() {
            String trecho = "Seus olhos embotados de cimento e lágrimas\n" +
                            "E ergueu no patamar quatro paredes sólidas\n" +
                            "Trouxe os pés à calçada";

            List<String> resultado = parser.getTextoSemPlural(trecho);

            assertFalse(resultado.contains("olhos"),   "'olhos' deve ser removido");
            assertFalse(resultado.contains("paredes"), "'paredes' deve ser removido");
        }
    }
}
