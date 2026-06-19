package test.aczg.service;

import aczg.desafio.service.TextoParserService;
import aczg.desafio.service.TextoParserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Requisito 1: Palavras com ç, ã ou õ")
class CedilhaOuTildeTest {

    private TextoParserService parser;

    @BeforeEach
    void setUp() {
        parser = new TextoParserServiceImpl();
    }

    @Test
    @DisplayName("Dado texto vazio, retorna lista vazia")
    void deveRetornarListaVaziaParaTextoVazio() {
        List<String> resultado = parser.getPalavrasComCedilhaOuTilde("");
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Dado texto sem ç/ã/õ, retorna lista vazia")
    void deveRetornarListaVaziaQuandoNaoHaCaracteresEspeciais() {
        List<String> resultado = parser.getPalavrasComCedilhaOuTilde("amor belo forte");
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Dado 'calçada', retorna ['calçada']")
    void deveIdentificarPalavraComCedilha() {
        List<String> resultado = parser.getPalavrasComCedilhaOuTilde("calçada");
        assertEquals(List.of("calçada"), resultado);
    }

    @Test
    @DisplayName("Dado 'balança soluçou', retorna ambas")
    void deveIdentificarMultiplasPalavrasComCedilha() {
        List<String> resultado = parser.getPalavrasComCedilhaOuTilde("balança soluçou");
        assertTrue(resultado.contains("balança"));
        assertTrue(resultado.contains("soluçou"));
    }

    @Test
    @DisplayName("Dado 'feijão', retorna ['feijão']")
    void deveIdentificarPalavraComTildeAo() {
        List<String> resultado = parser.getPalavrasComCedilhaOuTilde("feijão");
        assertEquals(List.of("feijão"), resultado);
    }

    @Test
    @DisplayName("Dado 'pôs', não deve incluir (ô não é ã/õ/ç)")
    void naoDeveIncluirPalavraComCircunflexo() {
        List<String> resultado = parser.getPalavrasComCedilhaOuTilde("pôs");
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Não deve retornar duplicatas")
    void naoDeveRetornarDuplicatas() {
        List<String> resultado = parser.getPalavrasComCedilhaOuTilde("calçada calçada calçada");
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Resultado deve ser ordenado alfabeticamente")
    void resultadoDeveSerOrdenado() {
        List<String> resultado = parser.getPalavrasComCedilhaOuTilde("soluçou balança feijão");
        assertEquals(List.of("balança", "feijão", "soluçou"), resultado);
    }

    @Test
    @DisplayName("Dado trecho da música, identifica palavras corretas")
    void deveIdentificarPalavrasNaMusicaConstrucao() {
        String trecho = "Conduziu sua balança como se fosse um pêndulo\n" +
                        "Comeu feijão com arroz como se fosse um príncipe\n" +
                        "Bebeu e soluçou como se fosse um pêndulo\n" +
                        "Dançou e gargalhou como se fosse o último\n" +
                        "E ergueu no patamar quatro paredes sólidas\n" +
                        "Trouxe os pés à calçada como se fosse um príncipe";

        List<String> resultado = parser.getPalavrasComCedilhaOuTilde(trecho);

        assertTrue(resultado.contains("balança"),  "deve conter 'balança'");
        assertTrue(resultado.contains("feijão"),   "deve conter 'feijão'");
        assertTrue(resultado.contains("soluçou"),  "deve conter 'soluçou'");
        assertTrue(resultado.contains("dançou"),   "deve conter 'dançou'");
        assertTrue(resultado.contains("calçada"),  "deve conter 'calçada'");
    }
}
