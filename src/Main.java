
import aczg.desafio.domain.ResultadoAnalise;
import aczg.desafio.repository.*;
import aczg.desafio.service.*;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnalisadorMusicalService analisador = new AnalisadorMusicalService(
                new ConstrucaoLetraRepository(),
                new TextoParserServiceImpl()
        );

        ResultadoAnalise resultado = analisador.analisar();

        imprimirSeparador("ANÁLISE GRAMATICAL — Construção (Chico Buarque)");

        imprimirSecao("1. Palavras com ç, ã ou õ",
                resultado.getPalavrasComCedilhaOuTilde());

        imprimirSecaoMapa("2. Frases repetidas (frase → ocorrências)",
                resultado.getFrasesRepetidas());

        imprimirSecao("3a. Palavras com Ditongo",
                resultado.getPalavrasComDitongo());

        imprimirSecao("3b. Palavras com Tritongo",
                resultado.getPalavrasComTritongo());

        imprimirSecao("3c. Palavras com Hiato",
                resultado.getPalavrasComHiato());

        imprimirSecao("4. Frases de exatamente 4 palavras",
                resultado.getFrasesComQuatroPalavras());

        imprimirSecao("EXTRA 1 (+100pts) — Proparoxítonas",
                resultado.getPalavrasProparoxitonas());
        System.out.println("EXTRA 2 (+100pts) — Primeiras 30 palavras sem plural:");
        List<String> semPlural = resultado.getTextoSemPlural();
        int limite = Math.min(30, semPlural.size());
        System.out.println(String.join(" ", semPlural.subList(0, limite)) + " ...");
        System.out.println("Total de tokens: " + semPlural.size());
    }

    private static void imprimirSeparador(String titulo) {
        System.out.println("  " + titulo);
    }

    private static void imprimirSecao(String titulo, List<String> itens) {
        System.out.println("\n " + titulo);
        if (itens.isEmpty()) {
            System.out.println("  (nenhum encontrado)");
        } else {
            itens.forEach(i -> System.out.println("  • " + i));
            System.out.println("  Total: " + itens.size());
        }
    }

    private static void imprimirSecaoMapa(String titulo, Map<String, Long> mapa) {
        System.out.println("\n── " + titulo);
        if (mapa.isEmpty()) {
            System.out.println("  (nenhuma encontrada)");
        } else {
            mapa.forEach((frase, count) ->
                    System.out.printf("  [%2dx] %s%n", count, frase)
            );
            System.out.println("  Total de frases repetidas: " + mapa.size());
        }
    }
}