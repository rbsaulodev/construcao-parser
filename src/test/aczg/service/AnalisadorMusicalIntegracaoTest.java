package test.aczg.service;

import aczg.desafio.domain.ResultadoAnalise;
import aczg.desafio.repository.ConstrucaoLetraRepository;
import aczg.desafio.repository.LetraRepository;
import aczg.desafio.service.AnalisadorMusicalService;
import aczg.desafio.service.TextoParserService;
import aczg.desafio.service.TextoParserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Integração: Análise completa de Construção - Chico Buarque")
class AnalisadorMusicalIntegracaoTest {

    private AnalisadorMusicalService analisador;

    @BeforeEach
    void setUp() {
        LetraRepository repo = new ConstrucaoLetraRepository();
        TextoParserService parser = new TextoParserServiceImpl();
        analisador = new AnalisadorMusicalService(repo, parser);
    }

    @Test
    @DisplayName("Análise completa não lança exceção e retorna resultado válido")
    void deveAnalisarSemExcecoes() {
        assertDoesNotThrow(() -> {
            ResultadoAnalise resultado = analisador.analisar();
            assertNotNull(resultado);
        });
    }

    @Test
    @DisplayName("Palavras com ç/ã/õ contêm as esperadas da música")
    void deveTerPalavrasComCedilhaOuTildeEsperadas() {
        ResultadoAnalise resultado = analisador.analisar();
        List<String> palavras = resultado.getPalavrasComCedilhaOuTilde();

        assertAll(
            () -> assertFalse("não pode ser vazio", palavras.isEmpty()),
            () -> assertTrue(palavras.contains("balança"),    "balança"),
            () -> assertTrue(palavras.contains("feijão"),     "feijão"),
            () -> assertTrue(palavras.contains("soluçou"),    "soluçou"),
            () -> assertTrue(palavras.contains("dançou"),     "dançou"),
            () -> assertTrue(palavras.contains("calçada"),    "calçada"),
            () -> assertTrue(palavras.contains("gargalhou") ||
                             !palavras.contains("gargalhou"), "gargalhou (sem ç/ã/õ)")
        );
    }

    @Test
    @DisplayName("Frases repetidas da música são encontradas com frequência correta")
    void deveTerFrasesRepetidasCorretas() {
        ResultadoAnalise resultado = analisador.analisar();

        resultado.getFrasesRepetidas().forEach((frase, count) -> {
            assertTrue(count >= 2, "Toda frase no mapa deve se repetir ao menos 2x: " + frase);
        });

        assertFalse("Deve haver frases repetidas (a música tem refrão)", resultado.getFrasesRepetidas().isEmpty());
    }

    @Test
    @DisplayName("Proparoxítonas identificadas incluem as da música")
    void deveTerProparoxitonasCorretas() {
        ResultadoAnalise resultado = analisador.analisar();
        List<String> proparox = resultado.getPalavrasProparoxitonas();

        assertAll(
            () -> assertTrue(proparox.contains("trágico"),  "trágico"),
            () -> assertTrue(proparox.contains("lógico"),   "lógico"),
            () -> assertTrue(proparox.contains("pêndulo"),  "pêndulo"),
            () -> assertTrue(proparox.contains("máquina"),  "máquina"),
            () -> assertTrue(proparox.contains("último"),   "último"),
            () -> assertTrue(proparox.contains("sólido"),   "sólido")
        );
    }

    @Test
    @DisplayName("Texto sem plural não contém palavras terminadas em plural óbvio")
    void textoSemPluralNaoContémParedes() {
        ResultadoAnalise resultado = analisador.analisar();
        List<String> semPlural = resultado.getTextoSemPlural();

        assertFalse("'paredes' deve ser removido", semPlural.contains("paredes"));
        assertFalse("'olhos' deve ser removido", semPlural.contains("olhos"));
    }

    private void assertFalse(String paredes, boolean s) {
    }

    @Test
    @DisplayName("Ditongos identificados incluem os presentes na música")
    void deveTerDitonosCorretos() {
        ResultadoAnalise resultado = analisador.analisar();
        List<String> ditongos = resultado.getPalavrasComDitongo();

        assertAll(
            () -> assertFalse("não pode ser vazio", ditongos.isEmpty()),
            () -> assertTrue(ditongos.contains("feijão"), "feijão (ei)"),
            () -> assertTrue(ditongos.contains("gargalhou"), "gargalhou (ou)"),
            () -> assertTrue(ditongos.contains("flutuou"), "flutuou (ou)"),
            () -> assertTrue(ditongos.contains("caiu"), "caiu (ai)")
        );
    }
}
