package aczg.desafio.util;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public final class TextoNormalizer {

    private static final Pattern PONTUACAO = Pattern.compile("[^\\p{L}\\p{N}\\s\\-]", Pattern.UNICODE_CHARACTER_CLASS);
    private static final Pattern ESPACOS   = Pattern.compile("\\s+");

    private TextoNormalizer() {

    }

    public static String limpar(String texto) {
        if (texto == null) return "";
        String sem = PONTUACAO.matcher(texto).replaceAll(" ");
        return ESPACOS.matcher(sem).replaceAll(" ").trim();
    }

    public static List<String> quebrarEmLinhas(String texto) {
        return Arrays.stream(texto.split("\\r?\\n"))
                     .map(String::trim)
                     .filter(l -> !l.isEmpty())
                     .collect(toList());
    }

    public static String removerAcentos(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return normalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    public static String normalizarChave(String frase) {
        return limpar(frase).toLowerCase();
    }
}
