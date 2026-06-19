package aczg.desafio.service;

import aczg.desafio.util.RegexPatterns;
import aczg.desafio.util.TextoNormalizer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextoParserServiceImpl implements TextoParserService {

    @Override
    public List<String> getPalavrasComCedilhaOuTilde(String texto) {
        return extrairPalavrasUnicasOrdenadas(texto, RegexPatterns.CEDILHA_OU_TILDE);
    }

    @Override
    public Map<String, Long> getFrasesRepetidas(String texto) {
        List<String> linhas = TextoNormalizer.quebrarEmLinhas(texto);

        return linhas.stream()
                     .collect(Collectors.groupingBy(
                         TextoNormalizer::normalizarChave,
                         Collectors.counting()
                     ))
                     .entrySet().stream()
                     .filter(e -> e.getValue() > 1)
                     .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                     .collect(Collectors.toMap(
                         Map.Entry::getKey,
                         Map.Entry::getValue,
                         (a, b) -> a,
                         LinkedHashMap::new
                     ));
    }

    @Override
    public List<String> getPalavrasComDitongo(String texto) {
        return extrairPalavrasUnicasOrdenadas(texto, RegexPatterns.DITONGO);
    }

    @Override
    public List<String> getPalavrasComTritongo(String texto) {
        return extrairPalavrasUnicasOrdenadas(texto, RegexPatterns.TRITONGO);
    }

    @Override
    public List<String> getPalavrasComHiato(String texto) {
        return extrairPalavrasUnicasOrdenadas(texto, RegexPatterns.HIATO);
    }

    @Override
    public List<String> getFrasesComQuatroPalavras(String texto) {
        return TextoNormalizer.quebrarEmLinhas(texto).stream()
               .map(TextoNormalizer::limpar)
               .filter(linha -> RegexPatterns.FRASE_QUATRO_PALAVRAS.matcher(linha).matches())
               .distinct()
               .sorted()
               .collect(Collectors.toList());
    }


    @Override
    public List<String> getPalavrasProparoxitonas(String texto) {
        return extrairPalavrasUnicasOrdenadas(texto, RegexPatterns.PROPAROXITONA);
    }

    @Override
    public List<String> getTextoSemPlural(String texto) {
        if (texto == null || texto.isBlank()) return List.of();
        
        String textoLimpo = TextoNormalizer.limpar(texto);
        String semEs = RegexPatterns.PLURAL_ES.matcher(textoLimpo).replaceAll(mr -> mr.group(1));
        String semS = RegexPatterns.PLURAL_S.matcher(semEs).replaceAll(mr -> mr.group(1));

        return Arrays.stream(semS.split("\\s+"))
                     .filter(p -> !p.isBlank())
                     .map(String::toLowerCase)
                     .collect(Collectors.toList());
    }

    private List<String> extrairPalavrasUnicasOrdenadas(String texto, Pattern padrao) {
        if (texto == null || texto.isBlank()) return List.of();
        
        Matcher matcher = padrao.matcher(texto);

        Set<String> encontradas = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        while (matcher.find()) {
            encontradas.add(matcher.group().toLowerCase());
        }
        return List.copyOf(encontradas);
    }
}
