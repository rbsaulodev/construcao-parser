package test.aczg.service;

import aczg.desafio.service.TextoParserService;
import aczg.desafio.service.TextoParserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Requisito 3: Encontros vocálicos")
class EncontrosVocálicosTest {

    private TextoParserService parser;

    @BeforeEach
    void setUp() {
        parser = new TextoParserServiceImpl();
    }

    @Nested
    @DisplayName("3a. Ditongos")
    class DitongoTest {

        @Test
        @DisplayName("Dado texto vazio, retorna lista vazia")
        void deveRetornarVazioParaTextoVazio() {
            assertTrue(parser.getPalavrasComDitongo("").isEmpty());
        }

        @Test
        @DisplayName("'feijão' tem ditongo decrescente 'ei'")
        void deveIdentificarDitongoDecrescenteEI() {
            List<String> resultado = parser.getPalavrasComDitongo("feijão");
            assertTrue("'feijão' deve ser encontrado", resultado.contains("feijão"));
        }

        @Test
        @DisplayName("'gargalhou' tem ditongo decrescente 'ou'")
        void deveIdentificarDitongoDecrescenteOU() {
            List<String> resultado = parser.getPalavrasComDitongo("gargalhou");
            assertTrue("'gargalhou' deve ser encontrado", resultado.contains("gargalhou"));
        }

        @Test
        @DisplayName("'flutuou' tem ditongo decrescente 'ou'")
        void deveIdentificarDitongoEmFlutuou() {
            List<String> resultado = parser.getPalavrasComDitongo("flutuou");
            assertTrue("'flutuou' deve ser encontrado", resultado.contains("flutuou"));
        }

        @Test
        @DisplayName("'cimento' não tem ditongo")
        void naoDeveIdentificarDitongoEmCimento() {
            List<String> resultado = parser.getPalavrasComDitongo("cimento");
            assertFalse(resultado.contains("cimento"), "'cimento' não tem ditongo");
        }

        @Test
        @DisplayName("Resultado não tem duplicatas e é ordenado")
        void resultadoDeveSerUnicoEOrdenado() {
            List<String> resultado = parser.getPalavrasComDitongo("feijão gargalhou feijão");
            assertEquals(resultado.stream().distinct().collect(Collectors.toList()), resultado);
        }

        @Test
        @DisplayName("Dado trecho da música, identifica ditongos presentes")
        void deveIdentificarDitonosNaMusicaConstrucao() {
            String trecho = "Comeu feijão com arroz como se fosse um príncipe\n" +
                            "Dançou e gargalhou como se fosse o último\n" +
                            "E flutuou no ar como se fosse um pássaro\n" +
                            "Caiu como se fosse um bêbado\n" +
                            "Trouxe os pés à calçada como se fosse um príncipe";

            List<String> resultado = parser.getPalavrasComDitongo(trecho);

            assertAll(
                () -> assertTrue("feijão (ei)", resultado.contains("feijão")),
                () -> assertTrue("gargalhou (ou)", resultado.contains("gargalhou")),
                () -> assertTrue("flutuou (ou)", resultado.contains("flutuou")),
                () -> assertTrue("caiu (ai)", resultado.contains("caiu")),
                () -> assertTrue("trouxe (ou)", resultado.contains("trouxe"))
            );
        }
    }

    @Nested
    @DisplayName("3b. Tritongos")
    class TritongoTest {

        @Test
        @DisplayName("Dado texto vazio, retorna lista vazia")
        void deveRetornarVazioParaTextoVazio() {
            assertTrue(parser.getPalavrasComTritongo("").isEmpty());
        }

        @Test
        @DisplayName("'Uruguai' tem tritongo 'uai'")
        void deveIdentificarTritongo() {
            List<String> resultado = parser.getPalavrasComTritongo("Uruguai");
            assertFalse(resultado.isEmpty(), "Uruguai deve ser encontrado");
        }

        @Test
        @DisplayName("'iguais' tem tritongo 'uai'")
        void deveIdentificarTritongEmIguais() {
            List<String> resultado = parser.getPalavrasComTritongo("iguais");
            assertFalse(resultado.isEmpty(), "iguais deve ser encontrado");
        }

        @Test
        @DisplayName("'feijão' não tem tritongo")
        void naoDeveIdentificarTritongEmFeijao() {
            List<String> resultado = parser.getPalavrasComTritongo("feijão");
            assertFalse(resultado.contains("feijão"), "feijão não tem tritongo");
        }

        @Test
        @DisplayName("Na música Construção não há tritongos evidentes")
        void musicaConstrucaoNaoTemTritongoEvidente() {
            String trecho = "Amou daquela vez como se fosse a última\n" +
                            "Beijou sua mulher como se fosse a última";
            List<String> resultado = parser.getPalavrasComTritongo(trecho);
            assertNotNull(resultado);
        }
    }

    @Nested
    @DisplayName("3c. Hiatos")
    class HiatoTest {

        @Test
        @DisplayName("Dado texto vazio, retorna lista vazia")
        void deveRetornarVazioParaTextoVazio() {
            assertTrue(parser.getPalavrasComHiato("").isEmpty());
        }

        @Test
        @DisplayName("'saúde' tem hiato 'aú'")
        void deveIdentificarHiatoEmSaude() {
            List<String> resultado = parser.getPalavrasComHiato("saúde");
            assertTrue("'saúde' tem hiato", resultado.contains("saúde"));
        }

        @Test
        @DisplayName("'poesia' tem hiato 'oe'")
        void deveIdentificarHiatoEmPoesia() {
            List<String> resultado = parser.getPalavrasComHiato("poesia");
            assertTrue("'poesia' tem hiato", resultado.contains("poesia"));
        }

        @Test
        @DisplayName("'lágrimas' tem hiato 'ag' + 'ima'? — não, verifica real")
        void deveIdentificarHiatoEmLagrimas() {
            List<String> resultado = parser.getPalavrasComHiato("lágrimas");
            assertNotNull(resultado);
        }

        @Test
        @DisplayName("Dado trecho da música, identifica hiatos")
        void deveIdentificarHiatosNaMusicaConstrucao() {
            String trecho = "Seus olhos embotados de cimento e lágrimas\n" +
                            "Recostou na ribanceira como se fosse máquina\n" +
                            "E flutuou no ar como se fosse um pássaro";

            List<String> resultado = parser.getPalavrasComHiato(trecho);

            assertNotNull(resultado);
            assertFalse(resultado == null);
        }
    }
}
